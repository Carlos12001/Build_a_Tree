package Java.main;

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
        while (true){
            server.listenSocket();
        }

        //server.writeSocket("soy el server de java");
    }

}
