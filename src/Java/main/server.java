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
        server.readSocket();
        //server.writeSocket("soy el server de java");
    }

}
