package javathings.trees.AbstractTree;


/**
 *
 */
public abstract class Tree {

    /**
     *
     */
    private TreeNode root;
    /**
     *
     */
    private String treeID;

    /**
     *
     */
    private TreeNode current;

    public Tree() {
        this.root = null;
        this.current = null;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void append(String nodeID){
        String[] tmp = nodeID.split("@");

        if(tmp[0].equals(this.treeID)){
            appedAux(Integer.parseInt(tmp[1]));
        } else{
            this.current = null;
            this.root = null;
        }
    }

    protected abstract void appedAux(int key);

    protected abstract void setCurrent(TreeNode current);

}
