package com.github.lucasw.wsclient

import java.awt.FlowLayout
import java.io.FileWriter
import javax.swing.*


fun GetIp.Companion.IpEntry(){
    val frame = JFrame()
    val box = Box.createVerticalBox()
    val adrEntry = JTextArea(1, 10)
    val portEntry = JTextArea(1, 10)
    val updateButton = JButton("Submit")

    frame.layout = FlowLayout(FlowLayout.CENTER, 15, 15)

    box.add(JLabel("Address: "))
    box.add(adrEntry)
    box.add(JLabel("Port: "))
    box.add(portEntry)

    frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE

    updateButton.addActionListener {
        when (it.source) {
            updateButton -> {writeToFile(arrayOf(adrEntry.text, portEntry.text));
                frame.dispose(); }
        }
    }

    box.add(updateButton)

    frame.add(box)
    frame.setSize(300, 200)
    frame.isVisible = true
}

private fun writeToFile(list: Array<String>){
    val writer = FileWriter(csvFileName)
    list.forEach {
       if (list.indexOf(it) != list.lastIndex){
           writer.append("${it},")
       }
       else{
           writer.append(it)
       }
    }

    writer.close()
}