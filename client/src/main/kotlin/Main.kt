package com.github.lucasw.wsclient

import java.io.File

const val csvFileName = "ip.csv"
const val title = "WS Client"

class Main {
    companion object {
        private fun checkIpFile(){
            if (!File(csvFileName).exists()){
                File(csvFileName).createNewFile()
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            checkIpFile()
            Gui()
        }
    }
}