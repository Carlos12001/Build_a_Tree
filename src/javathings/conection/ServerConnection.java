package javathings.conection;

import java.io.*;
import java.net.*;

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
    private Integer clientsPort;

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
     * @return serverConnection new server
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
                    String message = in.readLine();

                    System.out.println("mensaje del cliente: " + message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void open() throws IOException
    {  serverInD = new DataInputStream(new BufferedInputStream(socketClient.getInputStream()));
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

    public void SendingSocket(String ip, int clientsPort, String message) {
        System.out.println("Establishing connection. Please wait ...");
        try //InetAddress serverName//client = new Sockets.ChatClient(InetAddress.getLocalHost(), i, message);
        {
            if (ip == "") {
                InetAddress serverName = InetAddress.getLocalHost();
                socketClient = new Socket(serverName, clientsPort);
                System.out.println("Connected: " + socketClient);
                //start();
            } else {
                socketClient = new Socket(ip, clientsPort);
                System.out.println("Connected: " + socketClient);
                //start();
            }
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
        try {
            serverOutD.writeUTF(message);
            serverOutD.flush();
        } catch (IOException ioe) {
            System.out.println("Sending error: " + ioe.getMessage());
        }

        System.out.println("message sent...connection finished");
        //stop();
    }


//    /**
//     *
//     */
//    public void readSocket(){
//        Thread thread = new Thread(()-> {
//            try {
//                String message = this.serverInD.readUTF();
//                System.out.println(message);
//
//                this.socketClient.setKeepAlive(true);
//            } catch (SocketException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
//    }

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