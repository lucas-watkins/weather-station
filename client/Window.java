package WeatherStationClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;
import WeatherStationClient.GetWeather;


// I would like to apologize to the Java community for the following code - Lucas
public class Window implements ActionListener{
    public static WeatherStationClient.DatLoader datFile = new WeatherStationClient.DatLoader("serverip.dat");
    static final String title = "WS Client";

    // Main frame
    public static JFrame frame = new JFrame();

    // Update button
    JButton updateBtn = new JButton("Update Weather");

    // Weather Box
    public static Box weatherBox = Box.createVerticalBox();

    // Controls box
    Box controlsBox = Box.createHorizontalBox();
    Box buttonBox = Box.createVerticalBox();

    // Menu Bar components
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem about = new JMenuItem("About");
    JMenuItem updateServerIP = new JMenuItem("Update Weather Station IP");

    public Window(){

        //Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame layout
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        //Add components to weather box and set layout
        weatherBox.add(new JLabel("Weather:\n"), BorderLayout.NORTH);

        //add components to controls box
        buttonBox.add(updateBtn);
        controlsBox.add(buttonBox);


        // register action listener
        updateBtn.addActionListener(this);

        // set title
        frame.setTitle(Window.title);

        //Load Menu Bar
        menuBar.add(file);
        about.addActionListener(this);
        file.add(about);
        updateServerIP.addActionListener(this);
        file.add(updateServerIP);

        // Add components
        frame.add(controlsBox);
        frame.add(weatherBox);
        frame.setJMenuBar(menuBar);

        // Configure frame
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 250);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(updateBtn)){
            WeatherStationClient.GetWeather.getWeather();
        }
        if (e.getSource().equals(about)){
            int option = JOptionPane.showConfirmDialog(null, "Weather Station Client vVersionHere\nWeather Station IP: "+datFile.getContents()+"\nVisit Github Repository?", title, JOptionPane.YES_NO_OPTION);
            if (option == 0){
                try{
                    Desktop.getDesktop().browse(new URI("https://github.com/lucas-watkins/weather-station"));
                } catch (Exception f)
                {
                    f.printStackTrace();
                }
            }
        }
        if (e.getSource().equals(updateServerIP)){
            String newIP = JOptionPane.showInputDialog(null, "Enter Weather Station IP:");
            if (newIP != null && !newIP.isBlank()) {
                datFile.writeContents("http://"+newIP);
            }
        }
    }


    public static void main(String[] args){
        Window main = new Window();
    }
}
