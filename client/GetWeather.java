package WeatherStationClient;

import com.roxstudio.utils.CUrl;
import javax.swing.*;

import static WeatherStationClient.Window.datFile;
import static WeatherStationClient.Window.weatherBox;
import static WeatherStationClient.Window.updateBtn;

public class GetWeather extends Thread {

   // Getting weather from ip specified in Window.java and writing it to a dictionary named weather in the same file
   // Shows error when unable to get weather
   public void run(){
        try {
             // grey out update button
             updateBtn.setEnabled(false);

             CUrl curl = new CUrl(datFile.getContents());
             String weather = new String(curl.exec());
             weatherBox.removeAll();
             weatherBox.add(new JLabel("Weather:\n"));
             weatherBox.add(new JLabel(weather));

             // re-enable update button
             updateBtn.setEnabled(true);

             // update ui tree
             SwingUtilities.updateComponentTreeUI(WeatherStationClient.Window.frame);

        } catch (Exception e){
             JOptionPane.showMessageDialog(null, "Unable to get weather, Weather Station did not respond", WeatherStationClient.Window.title, JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }
   }
}
