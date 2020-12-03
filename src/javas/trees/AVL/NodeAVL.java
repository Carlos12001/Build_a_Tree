package javas.trees.AVL;

import javas.trees.Abstract.TreeNode;

public class NodeAVL extends TreeNode {

    private int height;

    public NodeAVL(int token) {
        super(token);
    }

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

