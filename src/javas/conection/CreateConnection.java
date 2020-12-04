package javas.conection;

import javas.time.TimeJava;
import javas.trees.AVL.TreeAVL;
import javas.trees.B.TreeB;
import javas.trees.BST.TreeBST;
import javas.trees.Splay.TreeSplay;
import javas.trees.Abstract.Tree;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateConnection implements Runnable{

    private UpdateInfo serverInfo;

    public static Tree[] treeArray = {
            new TreeB(3),
            new TreeBST(),
            new TreeAVL(),
            new TreeSplay()};

    private TimeJava newTime = null;
    private Boolean inChallenge = false;
    private Boolean waitingChallenge = false;
    private int timeTillChallenge = 90;
    private int timeTillToken = 7;
    private int ChallengeCounter = 0;
    private boolean firstConnection = true;


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

                serverInfo.UpdateFile(infoToUpdate);

                if (this.firstConnection){
                    if (serverInfo.getPlayersName().length != 0){
                        this.startTime();
                        this.firstConnection = false;
                }

                }
                this.updateTrees();

                this.ChallengeCounter++;
                ChallengeManager();

                String answerJsonStr = new JacksonEncoder().EncodeInfo(this.serverInfo);



                System.out.println();
                System.out.println(answerJsonStr);
                System.out.println();



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

    public String[] ChallengeAssigner(){
        String[] names = serverInfo.getPlayersName();
        boolean[] states = serverInfo.getPlayersGameOver();
        String[] challengeList = {"TreeAVL","TreeSplay","TreeB","TreeBST"};
        String[] challenges = {"","","",""};

        for(int n=0; n < names.length; n++) {
            if (states[n]) {
                challenges[n] = challengeList[n];
            }
            else {
                challenges[n] = "";
            }
        }
        return challenges;

    }

    public String SelectToken(){ // Necesito saber porque retorna Null, los árboles empiezan en null ?

        int randomPos = (int)((Math.random() * (5-1) + 1)-1);
        System.out.println("randomPos: " + randomPos);

        return treeArray[randomPos].getCurrent();
    }

    public void AnalyzeReceivedData(){
        Tree[] treeArrayTmp = CreateConnection.treeArray;
        boolean complete = true;

        for (Tree treeCurrent : treeArrayTmp) {
            if (treeCurrent.getCurrent().split("@")[1].equals("-1")) {
                complete = false;
                break;
            }
        }
        this.inChallenge = complete;
    }



    public void ChallengeManager(){
        if(timeTillChallenge == 0){
            pickChallengeTime();
        }

        else{
            if (!this.inChallenge){
                if (this.ChallengeCounter == this.timeTillChallenge){
                    this.ChallengeCounter = 0;
                    this.inChallenge = true;
                    String[] challengeUpdate = ChallengeAssigner();
                    serverInfo.setChallenge(challengeUpdate);
                }
            }
        }
        if(this.inChallenge){
            AnalyzeReceivedData();

            if (this.ChallengeCounter == this.timeTillToken){
                this.ChallengeCounter = 0;
                String token =SelectToken();
                serverInfo.setTokenSend(token);
            }
        }
    }

    public void pickChallengeTime(){
        int minSecs = 40;
        int maxSecs = 95;
        this.timeTillChallenge = (int)(Math.random() * (maxSecs - minSecs + 1) + minSecs);
        System.out.println("time till ch: " + this.timeTillChallenge);
    }

    public void updateTrees(){
        try {
            if (!this.serverInfo.getTreeB().equals("")){
                CreateConnection.treeArray[0].append(this.serverInfo.getTreeB());
            }

            if (!this.serverInfo.getTreeBST().equals("")){
                CreateConnection.treeArray[1].append(this.serverInfo.getTreeB());
            }

            if (!this.serverInfo.getTreeAVL().equals("")){
                CreateConnection.treeArray[2].append(this.serverInfo.getTreeB());

            }
            if (!this.serverInfo.getTreeSplay().equals("")){
                CreateConnection.treeArray[3].append(this.serverInfo.getTreeB());
            }
        }catch (Exception ex){
            System.out.println("MAME");
        }


    }

    public void startTime(){
        TimeJava newTime = new TimeJava();
        newTime.timeStart(20000);
        System.out.println("--------------Tiempo iniciado---------------");
    }

    public static void main(String[] args) {
        // write your code here

        CreateConnection hilito= new CreateConnection();

        TimeJava newTime = new TimeJava();
        newTime.timeStart(1);
        hilito.iniciarEscuchar();

    }
}




