package com.github.lucasw.wsclient

import java.awt.Component
import java.awt.FlowLayout
import java.io.FileWriter
import javax.swing.*


fun GetIp.Companion.ipEntry(){
    val frame = JFrame()
    val box = Box.createVerticalBox()
    val addrEntry = JTextArea(1, 10)
    val portEntry = JTextArea(1, 10)
    val updateButton = JButton("Submit")
    val addrLabel = JLabel("Address:")
    val portLabel = JLabel("Port:")

    frame.layout = FlowLayout(FlowLayout.CENTER, 15, 15)
    addrEntry.lineWrap = true
    portEntry.lineWrap = true

    for(ele in arrayOf(addrLabel, addrEntry, portLabel, portEntry, updateButton)) {
        box.add(ele)
        if (ele !is JLabel) {
            box.add(Box.createVerticalStrut(15))
        }
        ele.alignmentX = Component.LEFT_ALIGNMENT
    }

    frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE

    updateButton.addActionListener {
        when (it.source) {
            updateButton -> {writeToFile(arrayOf(addrEntry.text, portEntry.text))
                frame.dispose()}
        }
    }

    box.add(updateButton)

    frame.add(box)
    frame.setSize(300, 200)
    frame.title = "Set Ip and Port"
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