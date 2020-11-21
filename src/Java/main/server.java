package Java.main;

import Java.GUI.serverGUI;
import Java.conection.serverConnection;

/**
 *
 */
public class server {

    /**
     * @param args
     */
    
    public static void main(String[] args){
        serverConnection server = serverConnection.getInstance();
        server.listenSocket();
        serverGUI frame = new serverGUI(server.getPort());

        //server.writeSocket("soy el server de java");
    }

}
