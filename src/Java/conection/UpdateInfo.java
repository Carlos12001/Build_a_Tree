package Java.conection;

public class UpdateInfo {
    private String playersName[];
    private boolean playersGameOver[];
    private String treeB;
    private String treeBST;
    private String treeAVL;
    private String treeSplay;

    private int time;
    private String tokenSend;
    private String challenge[];

    


    public String[] getPlayersName() {
        return playersName;
    }

    public void setPlayersName(String[] playersName) {
        this.playersName = playersName;
    }

    public boolean[] getPlayersGameOver() {
        return playersGameOver;
    }

    public void setPlayersGameOver(boolean[] playersGameOver) {
        this.playersGameOver = playersGameOver;
    }

    public String getTreeB() {
        return treeB;
    }

    public void setTreeB(String treeB) {
        this.treeB = treeB;
    }

    public String getTreeBST() {
        return treeBST;
    }

    public void setTreeBST(String treeBST) {
        this.treeBST = treeBST;
    }

    public String getTreeAVL() {
        return treeAVL;
    }

    public void setTreeAVL(String treeAVL) {
        this.treeAVL = treeAVL;
    }

    public String getTreeSplay() {
        return treeSplay;
    }

    public void setTreeSplay(String treeSplay) {
        this.treeSplay = treeSplay;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTokenSend() {
        return tokenSend;
    }

    public void setTokenSend(String tokenSend) {
        this.tokenSend = tokenSend;
    }

    public String[] getChallenge() {
        return challenge;
    }

    public void setChallenge(String[] challenge) {
        this.challenge = challenge;
    }
}
