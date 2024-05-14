package com.github.lucasw.wsclient

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import kotlinx.coroutines.*

class Gui() : ActionListener {
    private val updateButton = JButton("Update")
    private val weatherBox = Box.createVerticalBox()
    private val horizontalBox = Box.createHorizontalBox()
    private val fileMenu = JMenu("File")
    private val aboutItem = JMenuItem("About")
    private val setIpItem = JMenuItem("Set IP")
    private val frame = JFrame()

    init {

        frame.title = title

        frame.layout = FlowLayout(FlowLayout.LEFT, 5, 5)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        horizontalBox.add(updateButton)
        horizontalBox.add(weatherBox)
        frame.add(horizontalBox)

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
        val weather = GetWeather.Weather
        weatherBox.removeAll()
        for (i in weather.indices){
            if (i % 50 == 0 || i == weather.lastIndex){
                weatherBox.add(JLabel(weather.substring(if (i == 0 || i == weather.lastIndex) 0 else (i - 50), i)))
            }
        }
        SwingUtilities.updateComponentTreeUI(frame)
        updateButton.isEnabled = true
    }

    private fun aboutHandler() {
        try {
            JOptionPane.showMessageDialog(
                null, "IP:\n${GetIp.Ip}\n\nPort:\n${GetIp.Port}\n\nJRE:" +
                        "\n${System.getProperty("java.vendor.version")}", title,
                JOptionPane.INFORMATION_MESSAGE
            )
        } catch (e: IndexOutOfBoundsException){
            JOptionPane.showMessageDialog(null, "IP: None Entered", title,
                JOptionPane.INFORMATION_MESSAGE)
        }
    }

}