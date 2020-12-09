package javas.trees.Abstract;

/**
 * This class creates a basic node for the trees
 */
public class TreeNode {
    /**
     * This is going to be the value that's gonna be assigned to a node
     */
    private int token;
    /**
     * This is the left child of a node
     */
    private TreeNode left;
    /**
     * THis is the right child of a node
     */
    private TreeNode right;

    /**
     * @param token value assign to the root node
     */
    public TreeNode(int token){
        this.token = token;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructor
     * @param token value for the token
     * @param left inserts a treeNode left
     * @param right inserts a treeNode right
     */
    public TreeNode(int token, TreeNode left, TreeNode right){
        this.token = token;
        this.left = left;
        this.right = right;
    }

    public TreeNode() {
    }

    /**
     * Sets new left.
     *
     * @param left New value of left.
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Gets left.
     *
     * @return Value of left.
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Gets token.
     *
     * @return Value of token.
     */
    public int getToken() {
        return token;
    }

    /**
     * Sets new token.
     *
     * @param token New value of token.
     */
    public void setToken(int token) {
        this.token = token;
    }

    /**
     * Sets new right.
     *
     * @param right New value of right.
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * Gets right.
     *
     * @return Value of right.
     */
    public TreeNode getRight() {
        return right;
    }
}
