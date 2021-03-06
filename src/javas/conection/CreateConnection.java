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

    /**
     * Instance of Update info (Singleton) that has all the info that comes from the client
     * and the updated info that's gonna be sent to the client
     */
    private UpdateInfo serverInfo;

    /**
     * Tree array that has an instance of each type of tree
     */
    public static Tree[] treeArray = {
            new TreeB(3),
            new TreeBST(),
            new TreeAVL(),
            new TreeSplay()};

    /**
     * Saves a TimeJava data thats used for the challenges
     */
    private TimeJava newTime = null;
    /**
     * Boolean that indicates if there's a challenge currently in progress
     */
    private Boolean inChallenge = false;
    /**
     * Boolean that indicates if the first challenge already passed or not
     */
    private Boolean waitingChallenge = false;
    /**
     * Time Until the next challenge starts
     */
    private int timeTillChallenge = 25;
    /**
     * If a challenge is in progress, time until the next token comes
     */
    private int timeTillToken = 5;
    /**
     * This is the counter of the server it acts as a timer
     */
    private int ChallengeCounter = 0;
    /**
     * Indicates if the challenge finished or is still in progress
     */
    private int ChallengeFinish = 0;
    /**
     * It indicates if is the first connection form the client to activate a certain protocol
     */
    private boolean firstConnection = true;
    /**
     * String that saves the last B tree token sent to the client
     */
    private String lastNodeB = "";
    /**
     * String that saves the last AVL tree token sent to the client
     */
    private String lastNodeAVL = "";
    /**
     * String that saves the last BST tree token sent to the client
     */
    private String lastNodeBST = "";
    /**
     * String that saves the last Splay tree token sent to the client
     */
    private String lastNodeSplay = "";

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
     * @param mensaje Messaje that is going to be sent to the server
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

    /**
     * This method Assigns a challenge to each live player on the game, it checks which player is alive
     * on the same method. Then it returns a String array with the challenges
     * @return String Array With the challenges assigned to each player
     */
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

    /**
     * Selects a random tree from the treeArray Variable of the class, and it gets the current token to send.
     * Then return a String with the token info.
     * @return String the token that is going to be sent to the client
     */
    public String SelectToken(){ // Necesito saber porque retorna Null, los árboles empiezan en null ?

        int randomPos = (int)((Math.random() * (5-1) + 1)-1);

        return treeArray[randomPos].getCurrent();
    }

    /**
     * This method analyzes the data that the server receives and if and checks if some player has completed
     * a tree, if it is the case, it shows that the player won a challenge
     */
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

    /**
     * This method picks a random number to set it as the time till next challenge.
     * It chooses a random number between a range
     */
    public void pickChallengeTime(){
        int minSecs = 25;
        int maxSecs = 40;
        this.timeTillChallenge = (int)(Math.random() * (maxSecs - minSecs + 1) + minSecs);
    }

    /**
     * This method updates the trees each time a token is collected and set by the customer
     */
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

    /**
     * Starts the time when game is started
     */
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




