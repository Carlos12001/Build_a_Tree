package javathings.main;

import javathings.Time.TimeJava;
import javathings.conection.UpdateInfo;

/**
 *
 */
public class Server {

    /**
     * @param args
     */
    
    public static void main(String[] args){
        javathings.conection.ServerConnection server = javathings.conection.ServerConnection.getInstance();
        server.listenSocket();
        new javathings.gui.ServerGUI(server.getPort());
        //server.writeSocket("soy el server de java");
    }

}
