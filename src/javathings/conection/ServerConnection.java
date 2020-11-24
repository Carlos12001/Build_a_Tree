package javathings.conection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 */
public class ServerConnection {

    /**
     *
     */
    private ServerSocket server;

    /**
     *
     */
    private static ServerConnection instance;

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
    private ServerConnection(){
        boolean alive = true;

        while (alive){

            try {
                server = new ServerSocket(getPort());
                System.out.println("Esperando cliente en: " + getPort());
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
    public static ServerConnection getInstance(){
        ServerConnection newInstance = instance;
        if (newInstance == null){
            newInstance = new ServerConnection();
        }
        return newInstance;
    }

    public void listenSocket(){
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    socketClient = server.accept();
                    System.out.println("Cliente conectado en el puerto 1024");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                    PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);

                    out.write("Saludos desde el servidor");

                    String clientMessage = in.readLine();
                    System.out.println("mensaje del cliente: " + clientMessage);

                    //this.serverInD = new DataInputStream(this.socketClient.getInputStream());
                    //this.serverOutD = new DataOutputStream(this.socketClient.getOutputStream());
                    //readSocket();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
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