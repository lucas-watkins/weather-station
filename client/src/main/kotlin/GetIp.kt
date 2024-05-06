package com.github.lucasw.wsclient

import java.io.FileReader

class GetIp {
    companion object {
        val Ip: String
            get() {
                val text = FileReader(csvFileName).readText()
                return text.substring(0, text.indexOf(','))
            }


        val Port: Int
            get(){
                var text = FileReader(csvFileName).readText()
                text = text.substring(text.indexOf(',') + 1, text.length)
                return text.toInt()
            }

        val Url: String
            get(){
                return "http://${Ip}:${Port}"
            }
    }
}