package javathings.trees.abstracTree;


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
            appendAux(Integer.parseInt(tmp[1]));
        } else{
            this.current = null;
            this.root = null;
        }
    }

    protected abstract void appendAux(int key);

    protected abstract void setCurrent(TreeNode current);

    protected abstract String getCurrent();
}
