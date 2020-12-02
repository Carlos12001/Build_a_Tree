package javathings.conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketClass implements Runnable{
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

    Thread t;


    public ServerSocketClass(String ip, int port) {
        this.ip = ip;
        this.port = port;
        //t = new Thread(this, "Thread");
        //System.out.println("Child thread: " + t);
        //t.start();
    }
    public void ServerListener() throws IOException {

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

    public void run() {
        try {
            System.out.println("Child Thread");
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("The child thread is interrupted.");
        }
    }

    public void ServerSender() throws IOException {
        InetAddress serverName = InetAddress.getLocalHost();
        bind = new Socket(serverName,2050);
        printStream = new PrintStream(bind.getOutputStream());
        System.out.println("Write a message: ");
        input = new Scanner(System.in);
        String strInput = input.nextLine();
        printStream.println(strInput);

        printStream.close();
    }

    public void Yahoo(ServerSocketClass SS) throws IOException {
        while(this.game){
            SS.ServerListener();
            SS.ServerSender();
            //SS.run();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocketClass SS = new ServerSocketClass("127.0.0.1", 8050);
        SS.Yahoo(SS);
        /*
        new ServerSocketClass("127.0.0.1", 8080);
        try {
            System.out.println("Main Thread");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("The Main thread is interrupted");
        }
        System.out.println("Exiting the Main thread");*/
    }

}

