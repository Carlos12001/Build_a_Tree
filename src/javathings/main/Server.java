package javathings.main;

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
        javathings.gui.ServerGUI frame = new javathings.gui.ServerGUI(server.getPort());

        //server.writeSocket("soy el server de java");
    }

}
