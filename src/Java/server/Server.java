package java.server;

/**
 *
 */
public class Server {

    /**
     * @param args
     */
    
    public static void main(String[] args){
        java.conection.ServerConnection server = java.conection.ServerConnection.getInstance();
        server.listenSocket();
        java.GUI.ServerGUI frame = new java.GUI.ServerGUI(server.getPort());

        //server.writeSocket("soy el server de java");
    }

}
