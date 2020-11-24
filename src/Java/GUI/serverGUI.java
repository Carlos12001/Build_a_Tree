package java.GUI;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame {

    private JLabel portLabel;

    public ServerGUI(int port){
        // Create variables for an easy change
        short Fwidth = 300;
        short Fheight = 200;

        // This method set the size of the frame
        setSize(Fwidth, Fheight);
        setLocation(20, 20);

        // This method don't allow to change frame dimensions
        setResizable(false);

        // This method shows the frame
        setTitle("Servidor");

        portLabel = new JLabel();
        portLabel.setForeground(Color.BLACK);
        portLabel.setText("    El puerto del servidor:   " + String.valueOf(port));
        add(portLabel);
//        // Create and add a panel which contains button and labels
//        PanelClient containerClient = new PanelClient();
//        containerClient.add(textArea);
//        containerClient.add(portLabel);
//        add(containerClient);

        setVisible(true);

        // Create a thread and starts it
//
//        clientThread = new Thread(this);
//        clientThread.start();
    }
}

//class panelServer extends JPanel{
//}
