package com.github.lucasw.wsclient

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

class Gui() : ActionListener {
    private val updateButton = JButton("update")
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

    }

    override fun actionPerformed(e: ActionEvent) {
        when (e.source){
            updateButton -> updateWeather()
            setIpItem -> GetIp.IpEntry()
            aboutItem -> aboutHandler()
        }
    }

    fun updateWeather(){
       weatherLabel.text = GetWeather.Weather
    }

    fun aboutHandler() {
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