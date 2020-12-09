package javas.trees.AVL;

import javas.trees.Abstract.TreeNode;

/**
 * This class is the specific class for an AVL tree node
 */
public class NodeAVL extends TreeNode {
    /**
     * This represents the total amount of levels that are on the tree
     */
    private int height;

    /**
     * Constructor that uses the parent class
     * @param token value for the token
     */
    public NodeAVL(int token) {
        super(token);
    }

    /**
     * This is a constructor for the node that sets new values
     * @param token value for the token
     * @param left inserts TreeNode left
     * @param right inserts TreeNode right
     */
    public NodeAVL(int token, NodeAVL left, NodeAVL right){
        super(token, left, right);
        this.height = 0;
    }

    /**
     * Sets new height.
     *
     * @param height New value of height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets height.
     *
     * @return Value of height.
     */
    public int getHeight() {
        return height;
    }
}

