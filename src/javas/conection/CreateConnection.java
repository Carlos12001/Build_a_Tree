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
    private int timeTillChallenge = 25;
    private int timeTillToken = 5;
    private int ChallengeCounter = 0;
    private int ChallengeFinish = 0;
    private boolean firstConnection = true;
    private String lastNodeB = "";
    private String lastNodeAVL = "";
    private String lastNodeBST = "";
    private String lastNodeSplay = "";

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
                this.ChallengeManager();

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


    public String[] ChallengeAssigner(){
        String[] names = serverInfo.getPlayersName();
        boolean[] states = serverInfo.getPlayersGameOver();
        java.util.Random r = new java.util.Random();
        int num = r.nextInt(16);
        String[] challenges = {"","","",""};

        for(int n=0; n < names.length; n++) {
            if (states[n]) {
                challenges[n] = CreateConnection.treeArray[num % 4].getTreeID();
                num++;
            }
            else {
                challenges[n] = "";
            }
        }
        return challenges;

    }

    public String SelectToken(){

        int randomPos = (int)((Math.random() * (5-1) + 1)-1);

        return treeArray[randomPos].getCurrent();
    }

    public void AnalyzeReceivedData(){
        boolean inchallenge = true;

        for (Tree treeCurrent : CreateConnection.treeArray) {
            if (treeCurrent.getCurrent().split("@")[1].equals("-1")) {
                inchallenge = false;
                for (Tree t : CreateConnection.treeArray) {
                    t.defaultTree();
                }
                this.ChallengeCounter = 0;
                this.serverInfo.setChallenge(new java.lang.String[]{"", "", "", ""});
                this.serverInfo.setTokenSend("");
                break;
            }
        }
        this.inChallenge = inchallenge;
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
            if (this.ChallengeFinish == 120){
                this.inChallenge = false;
                this.ChallengeCounter = 0;
                UpdateInfo.getUpdateInfo().setChallenge(new String[]{"","","",""});
                this.ChallengeFinish = 0;
            } else {
                this.ChallengeFinish++;
            }
            if (this.ChallengeCounter == this.timeTillToken){
                this.ChallengeCounter = 0;
                String token =SelectToken();
                serverInfo.setTokenSend(token);
            }
        }
    }

    public void pickChallengeTime(){
        int minSecs = 25;
        int maxSecs = 40;
        this.timeTillChallenge = (int)(Math.random() * (maxSecs - minSecs + 1) + minSecs);
    }

    public void updateTrees(){
        try {

            if (!this.serverInfo.getTreeB().equals("")){
                if (!this.lastNodeB.equals(this.serverInfo.getTreeB())) {
                    CreateConnection.treeArray[0].append(this.serverInfo.getTreeB());
                    this.lastNodeB = this.serverInfo.getTreeB();



                    this.serverInfo.setTreeB("");

                }

            }

            if (!this.serverInfo.getTreeBST().equals("")){
                if (!this.lastNodeBST.equals(this.serverInfo.getTreeBST())){
                    CreateConnection.treeArray[1].append(this.serverInfo.getTreeBST());
                    this.lastNodeBST = this.serverInfo.getTreeBST();

                    this.serverInfo.setTreeBST("");

                }

            }

            if (!this.serverInfo.getTreeAVL().equals("")){
                if (!this.lastNodeAVL.equals(this.serverInfo.getTreeAVL())){
                    CreateConnection.treeArray[2].append(this.serverInfo.getTreeAVL());
                    this.lastNodeAVL = this.serverInfo.getTreeAVL();
                    this.serverInfo.setTreeAVL("");

                }


            }
            if (!this.serverInfo.getTreeSplay().equals("")){
                if (!this.lastNodeSplay.equals(this.serverInfo.getTreeSplay())){
                    CreateConnection.treeArray[3].append(this.serverInfo.getTreeSplay());
                    this.lastNodeSplay = this.serverInfo.getTreeSplay();
                    this.serverInfo.setTreeSplay("");

                }

            }


        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public void startTime(){
        TimeJava newTime = new TimeJava();
        newTime.timeStart(7);
    }

    public static void main(String[] args) {
        // write your code here

        CreateConnection hilito= new CreateConnection();

        TimeJava newTime = new TimeJava();
        newTime.timeStart(1);
        hilito.iniciarEscuchar();

    }
}




