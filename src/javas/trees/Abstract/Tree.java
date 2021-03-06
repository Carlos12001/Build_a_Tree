package javas.trees.Abstract;


/**
 * This abstract class is the parent base for all the other tree classes
 */
public abstract class Tree {
    /**
     * Root of a tree, node type attribute
     */
    protected TreeNode root;
    /**
     * The tree ID is the type of tree, it works as an ID
     */
    private String treeID;
    /**
     * Current token that is being sent to the client
     */
    protected String current;
    /**
     * Current nodes that the tree has
     */
    protected int[] currentArray;

    /**
     * Constructor sets the root and the current node of the tree
     */
    public Tree() {
        this.root = null;
        this.current = null;
    }

    /**
     * Method that checks if a tree exists or is empty
     * @return boolean that indicates if the tree was already created
     */
    public boolean isEmpty(){
        return this.root == null;
    }

    /**
     * Adds a new token to the tree, divides the id from the actual token
     * @param nodeID String with the tree type to chek which tree andando
     */
    public void append(String nodeID){
        String[] tmp = nodeID.split("@");



        if(tmp[0].equals(this.treeID) ){
            this.setCurrent();
            this.appendAux(Integer.parseInt(tmp[1]));
        } else if (!tmp[0].equals(this.treeID)){
            this.defaultTree();
        }
    }

    /**
     *sets the root value to null and creates a node
     */
    public void defaultTree(){
        this.root = null;
        this.current = getTreeID() + "@" + getCurrentArray()[0];
    }

    /**
     * @param key Abstract method that will add each node to the determined tree type
     */
    protected abstract void appendAux(int key);

    /**
     *This method sets the new current node of the tree
     */
    public void setCurrent(){
        int[] tmp = getCurrentArray();
        for (int i = 0; i < tmp.length; i ++){
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
