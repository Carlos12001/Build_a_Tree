package javathings.main;

import javathings.Time.TimeJava;
import javathings.conection.ServerSocketClass;
import javathings.conection.UpdateInfo;

/**
 *
 */
public class Server {

    /**
     * @param args
     */
    
    public static void main(String[] args){
        System.out.println("Hola desde Servidor");

        ServerSocketClass mainThread= new ServerSocketClass();

        TimeJava newTime = new TimeJava();
        newTime.timeStart(1);
        mainThread.iniciarEscuchar();

//        javathings.conection.ServerConnection server = javathings.conection.ServerConnection.getInstance();
//        server.listenSocket();
//        new javathings.gui.ServerGUI(server.getPort());
//        //server.writeSocket("soy el server de java");
    }

}
