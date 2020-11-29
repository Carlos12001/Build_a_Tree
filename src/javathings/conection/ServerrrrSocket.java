package javathings.conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerrrrSocket {
    ServerSocket serverInstance;
    Socket incomingClient;
    Socket bind;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    PrintStream printStream ;
    Scanner input;
    String ip = "";
    int port = 0;
    boolean game = true;

    public ServerrrrSocket(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    public void run() throws IOException {

        this.serverInstance = new ServerSocket(this.port);
        System.out.println("Server is listening");
        this.incomingClient = serverInstance.accept();

        this.inputStreamReader = new InputStreamReader(incomingClient.getInputStream());
        this.bufferedReader = new BufferedReader(inputStreamReader);

        String message = bufferedReader.readLine();
        System.out.println("Client says: " + message);

        if(message.equals("bye")){
            this.game = false;
            System.out.println("Closing Server");
        }

        serverInstance.close();
        incomingClient.close();
        inputStreamReader.close();
        bufferedReader.close();

        //this.printStream = new PrintStream(incomingClient.getOutputStream());
        //printStream.println("Message received");
    }

    public void ServerSender() throws IOException {
        InetAddress serverName = InetAddress.getLocalHost();
        bind = new Socket(serverName,2030);
        printStream = new PrintStream(bind.getOutputStream());
        System.out.println("Write a message: ");
        input = new Scanner(System.in);
        String strInput = input.nextLine();
        printStream.println(strInput);

        printStream.close();
    }

    public void Yahoo(ServerrrrSocket SS) throws IOException {
        while(this.game){
            SS.run();
            SS.ServerSender();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerrrrSocket SS = new ServerrrrSocket("127.0.0.1", 8080);
        SS.Yahoo(SS);
    }

}

