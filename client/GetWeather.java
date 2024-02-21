package WeatherStationClient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.roxstudio.utils.CUrl;
import javax.swing.*;
import java.net.URL;
import java.util.Scanner;

import static WeatherStationClient.Window.datFile;
import static WeatherStationClient.Window.weatherBox;

public class GetWeather {


   // Getting weather from ip specified in Window.java and writing it to a dictionary named weather in the same file
   // Shows error when unable to get weather
   public static void getWeather(){
        try {
             CUrl curl = new CUrl(datFile.getContents());
             String weather = new String(curl.exec());
             weatherBox.removeAll();
             weatherBox.add(new JLabel("Weather:\n"));
             weatherBox.add(new JLabel(weather));

             SwingUtilities.updateComponentTreeUI(WeatherStationClient.Window.frame);

        } catch (Exception e){
             JOptionPane.showMessageDialog(null, "Unable to get weather, Weather Station did not respond", WeatherStationClient.Window.title, JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }
   }
}
