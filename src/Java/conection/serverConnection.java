package Java.conection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 */
public class serverConnection {

    /**
     *
     */
    private ServerSocket server;

    /**
     *
     */
    private static serverConnection instance;

    /**
     *
     */
    private int port = 1024;

    /**
     * This is the socket that is trying to connect to the server
     */
    private Socket socketClient;

    /**
     * This lets the aplication read primitive Java data types.
     */
    private DataInputStream serverInD;
    /**
     * This lets tje application write primitive Java data type.
     */
    private DataOutputStream serverOutD;

    /**
     *
     */
    private serverConnection(){
        boolean alive = true;

        while (alive){

            try {
                server = new ServerSocket(getPort());
                System.out.println(getPort());
                alive = false;
            } catch (IOException e) {
                setPort(++this.port);
                e.printStackTrace();
            }
        }
    }

    /**
     * @return serverConnecton new server
     */
    public static serverConnection getInstance(){
        serverConnection newInstance = instance;
        if (newInstance == null){
            newInstance = new serverConnection();
        }
        return newInstance;
    }

    /**
     * @param jacksonStr
     */
    public void writeSocket(String jacksonStr){
        try {
            serverOutD.writeUTF(jacksonStr);
            this.socketClient.setKeepAlive(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void readSocket(){
        Thread thread = new Thread(()-> {
            try {
                System.out.println("leooooo");
                String message = this.serverInD.readUTF();
                System.out.println(message);

                this.socketClient.setKeepAlive(true);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     *
     * @return int port number
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port int port number
     */
    public void setPort(int port) {
        this.port = port;
    }
}