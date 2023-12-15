package WeatherStationClient;

import javax.swing.*;
import java.awt.*;

public class GetWeatherWindow {
   {
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Box box = Box.createHorizontalBox();
        Box vertBox = Box.createVerticalBox();

        JButton okayBtn = new JButton("OK");

        //method chain go brr
        Image updateImage = new ImageIcon(this.getClass().getClassLoader().getResource("update.png")).getImage().getScaledInstance(64,64, Image.SCALE_SMOOTH);

        vertBox.add(new JLabel("Updating Weather Information..."));
        vertBox.add(Box.createVerticalStrut(50));
        vertBox.add(okayBtn);

        box.add(Box.createHorizontalStrut(10));
        box.add(new JLabel(new ImageIcon(updateImage)));
        box.add(Box.createHorizontalStrut(10));
        box.add(vertBox);

        contentPane.add(box);
        frame.setSize(350,200);
        frame.setVisible(true);

   }
}
