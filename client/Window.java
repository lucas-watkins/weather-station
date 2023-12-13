import javax.swing.*;
import java.awt.*;

// I would like to apologize to the Java community for the following code - Lucas
public class Window {
    static {
        JFrame frame = new JFrame();

        //Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Update button
        JButton updateBtn = new JButton("Update Weather");

        //Create Boxes
        Box leftHandBox = Box.createVerticalBox();

        //Add components to left hand box
        leftHandBox.add(updateBtn, BorderLayout.WEST);

        // Add components
        frame.add(leftHandBox, BorderLayout.WEST);
        frame.setVisible(true);
        frame.setSize(500, 250);
    }

    public static void main(String[] args){
        Window main = new Window();
    }
}
