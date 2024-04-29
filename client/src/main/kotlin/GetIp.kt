package com.github.lucasw.wsclient

import java.io.FileReader

class GetIp {
    companion object {
        val Ip: String
            get() {
                val text = FileReader(csvFileName).readText()
                val sb = StringBuilder()
                var hitComma = false
                for (char in text){
                    if (!hitComma) {
                        when (char) {
                            ',' -> {hitComma = true}
                            else -> sb.append(char)
                        }
                    }
                }

                return sb.toString()
            }


        val Port: Int
            get(){
                var text = FileReader(csvFileName).readText()
                text = text.substring(text.indexOf(',') + 1, text.length)
                return text.toInt()
            }
    }
}