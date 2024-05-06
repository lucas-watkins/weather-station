package com.github.lucasw.wsclient


import javax.swing.JOptionPane
import kotlin.system.exitProcess
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.ConnectException

class GetWeather {
    companion object {
        val Weather: String
            get(): String{
                if (GetIp.Ip.contains("[a-z]")){
                    JOptionPane.showMessageDialog(null, "Invalid IP Address!", "WS Client",
                        JOptionPane.ERROR_MESSAGE)
                        exitProcess(0)
                }

                if (GetIp.Ip.isBlank()){
                    GetIp.IpEntry()
                }

                var foundWeather = ""
                runBlocking {
                    launch {
                        try {
                            foundWeather = HttpClient().get(GetIp.Url).body()
                        } catch (e: ConnectException){
                            foundWeather = "Check your internet connection!"
                        }
                    }.join()
                }

                return foundWeather
            }

    }

}