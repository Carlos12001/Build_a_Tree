package javas.trees.AVL;

import javas.trees.Abstract.TreeNode;

/**
 * This class is the specific class for an AVL tree node
 */
public class NodeAVL extends TreeNode {

    private int height;

    public NodeAVL(int token) {
        super(token);
    }

    /**
     * This is a constructor for the node that sets new values
     * @param token
     * @param left
     * @param right
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

