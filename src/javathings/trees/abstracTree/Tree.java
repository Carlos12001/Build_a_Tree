package javathings.trees.abstracTree;


/**
 *
 */
public abstract class Tree {

    /**
     *
     */
    protected TreeNode root;
    /**
     *
     */
    private String treeID;

    /**
     *
     */
    protected String current;

    /**
     *
     */
    protected int[] currentArray;

    /**
     *
     */
    public Tree() {
        this.root = null;
        this.current = null;
    }

    /**
     * @return
     */
    public boolean isEmpty(){
        return this.root == null;
    }

    /**
     * @param nodeID
     */
    public void append(String nodeID){
        String[] tmp = nodeID.split("@");

        if(tmp[0].equals(this.treeID)){
            setCurrent();
            appendAux(Integer.parseInt(tmp[1]));
        } else{
            defaultTree();
        }
    }

    /**
     *
     */
    public void defaultTree(){
        this.root = null;
        this.current = getTreeID() + "@" + getCurrentArray()[0];
    }

    /**
     * @param key
     */
    protected abstract void appendAux(int key);

    /**
     *
     */
    public void setCurrent(){
        int[] tmp = getCurrentArray();
        for (int i = 0; i > tmp.length; i ++){
            int tmp2 = Integer.parseInt(this.current.split("@")[1]);
            if (tmp2 == -1){
                this.current = getTreeID() + "@" + -1;
                break;
            } else if (tmp2 == tmp[i]){
                this.current = getTreeID() + "@" + tmp[i + 1];
                break;
            }
        }
    }

    /**
     * Sets new treeID.
     *
     * @param treeID New value of treeID.
     */
    public void setTreeID(String treeID) {
        this.treeID = treeID;
    }

    /**
     * Gets treeID.
     *
     * @return Value of treeID.
     */
    public String getTreeID() {
        return treeID;
    }

    /**
     * Sets new currentArray.
     *
     * @param currentArray New value of currentArray.
     */
    public void setCurrentArray(int[] currentArray) {
        this.currentArray = currentArray;
    }

    /**
     * Gets currentArray.
     *
     * @return Value of currentArray.
     */
    public int[] getCurrentArray() {
        return currentArray;
    }

    /**
     * Gets current.
     *
     * @return Value of current.
     */
    public String getCurrent() {
        return current;
    }

}
