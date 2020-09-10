package com.example.proyectsad.helper.aplication

import android.text.Editable
import android.text.Html
import org.xml.sax.XMLReader


class MyTagHandler : Html.TagHandler {
    internal var first = true
    internal var parent: String? = null
    internal var index = 1

    override fun handleTag(opening: Boolean, tag: String,
                           output: Editable, xmlReader: XMLReader
    ) {

        if (tag == "ul") {
            parent = "ul"
            index = 1
        } else if (tag == "ol") {
            parent = "ol"
            index = 1
        }
        if (tag == "li") {
            var lastChar: Char = 0.toChar()
            if (output.isNotEmpty()) {
                lastChar = output[output.length - 1]
            }
            if (parent == "ul") {
                first = if (first) {
                    if (lastChar == '\n') {
                        output.append("\t•  ")
                    } else {
                        output.append("\n\t•  ")
                    }
                    false
                } else {
                    true
                }
            } else {
                if (first) {
                    if (lastChar == '\n') {
                        output.append("\t$index. ")
                    } else {
                        output.append("\n\t$index. ")
                    }
                    first = false
                    index++
                } else {
                    first = true
                }
            }
        }
    }
}