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

/**
 * This class is in charge of the connection, of creating the server that will manage the information sent
 * by the customer, update it and send an aswer back
 */
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

    /**
     * Constructor
     * Creates an Update Info Instance
     */
    public CreateConnection(){
        serverInfo = UpdateInfo.getUpdateInfo();
    }

    /**
     * Runs the listening Thread of the server
     */
    public void iniciarEscuchar(){
        Thread miHilo= new Thread(this);
        miHilo.start();

    }

    /**
     * Send a String mensaje to the Client, using the clients info and listening port
     * @param mensaje
     */
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


    /**
     * Main server method preforms the following operations:
     * It's a thread that is always listening
     * It receives the message (string) and turns it into an UpdateInfo Object
     * It checks if it it should start sending the time counter to the Client
     * It checks if calls the ChallengeManager method
     * It updates the UpdateInfo Object and calls the encoding method
     * It calls the sending method and cleans all the variables to listen again for a new message
     */
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

    /**
     * This method Assigns a challenge to each live player on the game, it checks which player is alive
     * on the same method. Then it returns a String array with the challenges
     * @return String Array
     */
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

    /**
     * Selects a random tree from the treeArray Variable of the class, and it gets the current token to send.
     * Then return a String with the token info.
     * @return String
     */
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


    /**
     * This method manages the logic of the challenges.
     * It picks a time until the next challenge
     * If the challenge hasn't started, checks if the counter has reached the time to start the challenge
     * If the challenge starts, it calls the challenge assigner and puts he returned info in the UI Object
     * If the challenge is in progress, it sends a random token of a random challenge, on a period of time
     */
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

    /**
     * This method picks a random number to set it as the time till next challenge.
     * It chooses a random number between a range
     */
    public void pickChallengeTime(){
        int minSecs = 40;
        int maxSecs = 95;
        this.timeTillChallenge = (int)(Math.random() * (maxSecs - minSecs + 1) + minSecs);
        System.out.println("time till ch: " + this.timeTillChallenge);
    }

    /**
     * This method updates the trees each time a token is collected and set by the customer
     */
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

    /**
     * Starts the time when game is started
     */
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




