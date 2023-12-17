package WeatherStationClient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.net.URL;
import java.util.Scanner;

import static WeatherStationClient.Window.weatherBox;

public class GetWeather {


   // Getting weather from ip specified in Window.java and writing it to a dictionary named weather in the same file
   // Shows error when unable to get weather
   public static boolean getWeather(){
        try {
             URL url = new URL(WeatherStationClient.Window.serverIP);
             Scanner scanner = new Scanner(url.openStream());

             JSONParser parser = new JSONParser();
             Object obj = parser.parse(scanner.next());

             JSONObject weather = (JSONObject) obj;

             weatherBox.removeAll();
             weatherBox.add(new JLabel("Weather:\n"));

             for( int i = 0; i < weather.size(); i++){
                  switch (i) {
                       case 0:
                            weatherBox.add(new JLabel("\nTemperature: " + weather.get("temp") + "°F"));
                            break;
                       case 1:
                            weatherBox.add(new JLabel("\nHumidity: " + weather.get("humidity") + "%"));
                            break;
                       default:
                            JOptionPane.showMessageDialog(null, "Array Out of Range Error, Check for a client update! \nArray Length: " + weather.size(), WeatherStationClient.Window.title, JOptionPane.INFORMATION_MESSAGE);
                  }
             }
             SwingUtilities.updateComponentTreeUI(WeatherStationClient.Window.frame);

        } catch (Exception e){
             JOptionPane.showMessageDialog(null, "Unable to get Weather", WeatherStationClient.Window.title, JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }

        return true;
   }
}
