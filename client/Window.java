package WeatherStationClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


// I would like to apologize to the Java community for the following code - Lucas
public class Window implements ActionListener{
    public static final String serverIP = "http://localhost:8080";

    static final String title = "WS Client";

    // Main frame
    public static JFrame frame = new JFrame();

    // Update button
    JButton updateBtn = new JButton("Update Weather");

    // Weather Box
    public static Box weatherBox = Box.createVerticalBox();

    public Window(){
        //Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create Boxes
        Box horizontalBox = Box.createHorizontalBox();

        //Add components to weather box
        weatherBox.add(new JLabel("Weather:\n"));

        //Add components to horizontal box
        horizontalBox.add(updateBtn, BorderLayout.WEST);
        horizontalBox.add(weatherBox, BorderLayout.CENTER);

        // register action listener
        updateBtn.addActionListener(this);

        // set title
        frame.setTitle(Window.title);

        // Add components
        frame.add(horizontalBox, BorderLayout.WEST);
        frame.setVisible(true);
        frame.setSize(500, 250);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(updateBtn)){
            WeatherStationClient.GetWeather.getWeather();
        }
    }


    public static void main(String[] args){
        Window main = new Window();
    }
}
