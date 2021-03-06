package javas.conection;

/**
 * Class that holds the info that must be updated and treated by de server
 */
public class UpdateInfo {
    /**
     * Instance of the Update info object
     */
    private static UpdateInfo instance;
    /**
     *List of Strings with the names of the players
     */
    private String[] playersName;
    /**
     *List of booleans with the current state of the players, true alive, false death
     */
    private boolean[] playersGameOver = {true, true, true, true};
    /**
     *Token grabbed by the player with the treeB assigned
     */
    private String treeB;
    /**
     *Token grabbed by the player with the treeBST assigned
     */
    private String treeBST;
    /**
     *Token grabbed by the player with the treeAVL assigned
     */
    private String treeAVL;
    /**
     *Token grabbed by the player with the treeSplay assigned
     */
    private String treeSplay;
    /**
     *String with the time value that the server is going to send to the client
     */
    private String time = "0:0";
    /**
     *Token that will be showed next on the client's interface
     */
    private String tokenSend = "";
    /**
     *List with the challenges settled for each player
     */
    private String[] challenge = {"", "", "", ""};

    private UpdateInfo(){

    }

    /**
     * This constructor works as a Singleton, it creates an instance and if the instance is created, it
     * returns it
     * @return instance
     */
    public static UpdateInfo getUpdateInfo(){
        if (instance == null){
            instance = new UpdateInfo();
        }
        return instance;
    }

    /**
     * This constructor updates the time value when game starts
     * @param time
     */
    public UpdateInfo(String time){
        this.time = time;
    }

    /**
     * This method updates the whole files info.
     * @param newInfo
     */
    public void UpdateFile(UpdateInfo newInfo){
        this.playersName = newInfo.getPlayersName();
        this.playersGameOver = newInfo.getPlayersGameOver();
        this.treeB = newInfo.getTreeB();
        this.treeBST = newInfo.getTreeBST();
        this.treeAVL = newInfo.getTreeAVL();
        this.treeSplay = newInfo.getTreeSplay();
    }

    /**
     * Sets new challenge.
     *
     * @param challenge New value of challenge.
     */
    public void setChallenge(String[] challenge) {
        this.challenge = challenge;
    }

    /**
     * Gets treeAVL.
     *
     * @return Value of treeAVL.
     */
    public String getTreeAVL() {
        return treeAVL;
    }

    /**
     * Gets time.
     *
     * @return Value of time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets new treeAVL.
     *
     * @param treeAVL New value of treeAVL.
     */
    public void setTreeAVL(String treeAVL) {
        this.treeAVL = treeAVL;
    }

    /**
     * Sets new playersName.
     *
     * @param playersName New value of playersName.
     */
    public void setPlayersName(String[] playersName) {
        this.playersName = playersName;
    }

    /**
     * Sets new time.
     *
     * @param time New value of time.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets treeBST.
     *
     * @return Value of treeBST.
     */
    public String getTreeBST() {
        return treeBST;
    }

    /**
     * Gets playersName.
     *
     * @return Value of playersName.
     */
    public String[] getPlayersName() {
        return playersName;
    }

    /**
     * Sets new playersGameOver.
     *
     * @param playersGameOver New value of playersGameOver.
     */
    public void setPlayersGameOver(boolean[] playersGameOver) {
        this.playersGameOver = playersGameOver;
    }

    /**
     * Sets new treeSplay.
     *
     * @param treeSplay New value of treeSplay.
     */
    public void setTreeSplay(String treeSplay) {
        this.treeSplay = treeSplay;
    }

    /**
     * Gets treeB.
     *
     * @return Value of treeB.
     */
    public String getTreeB() {
        return treeB;
    }

    /**
     * Sets new treeB.
     *
     * @param treeB New value of treeB.
     */
    public void setTreeB(String treeB) {
        this.treeB = treeB;
    }

    /**
     * Gets playersGameOver.
     *
     * @return Value of playersGameOver.
     */
    public boolean[] getPlayersGameOver() {
        return playersGameOver;
    }

    /**
     * Sets new tokenSend.
     *
     * @param tokenSend New value of tokenSend.
     */
    public void setTokenSend(String tokenSend) {
        this.tokenSend = tokenSend;
    }

    /**
     * Gets treeSplay.
     *
     * @return Value of treeSplay.
     */
    public String getTreeSplay() {
        return treeSplay;
    }

    /**
     * Gets challenge.
     *
     * @return Value of challenge.
     */
    public String[] getChallenge() {
        return challenge;
    }

    /**
     * Sets new treeBST.
     *
     * @param treeBST New value of treeBST.
     */
    public void setTreeBST(String treeBST) {
        this.treeBST = treeBST;
    }

    /**
     * Gets tokenSend.
     *
     * @return Value of tokenSend.
     */
    public String getTokenSend() {
        return tokenSend;
    }
}
