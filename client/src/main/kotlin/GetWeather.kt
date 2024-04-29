package com.github.lucasw.wsclient

import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.client5.http.impl.classic.HttpClients
import javax.swing.JOptionPane
import kotlin.system.exitProcess

class GetWeather {
    companion object {
        val weather: String
            get(){
                if (GetIp.Ip.contains("[a-z]")){
                    JOptionPane.showMessageDialog(null, "Invalid IP Address!", "WS Client",
                        JOptionPane.ERROR_MESSAGE)
                        exitProcess(0)
                }

                if (GetIp.Ip.isBlank()){
                    GetIp.IpEntry()
                }

                val req = HttpGet(GetIp.Ip)
                val client = HttpClients.createDefault();

                val resp = client.execute(req)

                return resp.toString()
            }
    }
}