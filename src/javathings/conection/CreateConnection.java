package javathings.conection;

import javathings.time.TimeJava;
import javathings.trees.AVL.TreeAVL;
import javathings.trees.B.TreeB;
import javathings.trees.BST.TreeBST;
import javathings.trees.Splay.TreeSplay;
import javathings.trees.abstracTree.Tree;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateConnection implements Runnable{

    private UpdateInfo serverInfo;

    public static Tree[] treeArray = { new TreeB(3), new TreeBST(), new TreeAVL(), new TreeSplay()};

    TimeJava newTime = null;
    public int challengeCounter;


    public CreateConnection(){
        serverInfo = UpdateInfo.getUpdateInfo();
    }

    public void iniciarEscuchar(){
        Thread miHilo= new Thread(this);
        miHilo.start();

    }

    public void enviar(String mensaje){
        try {
            Socket misocket = new Socket("127.0.0.1",9998);
            BufferedOutputStream flujo_salida=new BufferedOutputStream(misocket.getOutputStream());
            flujo_salida.write(mensaje.getBytes());
            flujo_salida.close();
            misocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                ServerSocket servidor = new ServerSocket(9999);
                Socket misocket= servidor.accept();
                BufferedReader flujo_entrada=new BufferedReader(new InputStreamReader(misocket.getInputStream()));
                String mensaje_texto= flujo_entrada.readLine();
                /* Aquí procesamos la información */

                UpdateInfo infoToUpdate = new JacksonDecoder(mensaje_texto).Decode();

                System.out.println(this.newTime + "              " + this.serverInfo);
                if (this.newTime==null && this.serverInfo.getPlayersName()[0].equals("")){
                    this.newTime = new TimeJava();
                    newTime.timeStart(2000000000);
                }



                serverInfo.UpdateFile(infoToUpdate);



                String answerJsonStr = new JacksonEncoder().EncodeInfo(this.serverInfo);

                this.enviar(answerJsonStr);

                flujo_entrada.close();
                servidor.close();
                misocket.close();

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void pickChallengeTime(){
        int minSecs = 40;
        int maxSecs = 95;
        this.challengeCounter = (int)(Math.random() * (maxSecs - minSecs + 1) + minSecs);
    }

    public static void main(String[] args) {
        // write your code here

        CreateConnection hilito= new CreateConnection();

        TimeJava newTime = new TimeJava();
        newTime.timeStart(1);
        hilito.iniciarEscuchar();

    }
}




