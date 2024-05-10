package com.github.lucasw.wsclient

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import kotlinx.coroutines.*

class Gui() : ActionListener {
    private val updateButton = JButton("Update")
    private val weatherLabel = JLabel()
    private val fileMenu = JMenu("File")
    private val aboutItem = JMenuItem("About")
    private val setIpItem = JMenuItem("Set IP")

    init {
        val frame = JFrame()
        frame.title = title

        frame.layout = FlowLayout(FlowLayout.LEFT, 5, 5)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(updateButton)
        frame.add(weatherLabel)

        val menuBar = JMenuBar()
        fileMenu.add(aboutItem)
        fileMenu.add(setIpItem)
        menuBar.add(fileMenu)
        frame.jMenuBar = menuBar

        updateButton.addActionListener(this)
        aboutItem.addActionListener(this)
        setIpItem.addActionListener(this)

        frame.size = Dimension(500, 250)
        frame.isVisible = true

        runBlocking {
            launch { updateWeather()}
        }

    }

    override fun actionPerformed(e: ActionEvent) {
        when (e.source){
            updateButton -> updateWeather()
            setIpItem -> GetIp.ipEntry()
            aboutItem -> aboutHandler()
        }
    }

    private fun updateWeather(){
       updateButton.isEnabled = false
       weatherLabel.text = GetWeather.Weather
       updateButton.isEnabled = true
    }

    private fun aboutHandler() {
        try {
            JOptionPane.showMessageDialog(
                null, "IP:\n${GetIp.Ip}\n\nPort:\n${GetIp.Port}", title,
                JOptionPane.INFORMATION_MESSAGE
            )
        } catch (e: IndexOutOfBoundsException){
            JOptionPane.showMessageDialog(null, "IP: None Entered", title,
                JOptionPane.INFORMATION_MESSAGE)
        }
    }
}