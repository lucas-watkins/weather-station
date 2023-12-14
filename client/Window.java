package WeatherStationClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import WeatherStationClient.GetWeatherWindow;

// I would like to apologize to the Java community for the following code - Lucas
public class Window implements ActionListener{

    static String title = "WS Client";
    // Main frame
    JFrame frame = new JFrame();

    // Update button
    JButton updateBtn = new JButton("Update Weather");

    public Window(){
        //Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create Boxes
        Box leftHandBox = Box.createVerticalBox();

        //Add components to left hand box
        leftHandBox.add(updateBtn, BorderLayout.WEST);

        // register action listener
        updateBtn.addActionListener(this);

        // set title
        frame.setTitle(Window.title);

        // Add components
        frame.add(leftHandBox, BorderLayout.WEST);
        frame.setVisible(true);
        frame.setSize(500, 250);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(updateBtn)){
            GetWeatherWindow window = new GetWeatherWindow();
        }
    }


    public static void main(String[] args){
        Window main = new Window();
    }
}
