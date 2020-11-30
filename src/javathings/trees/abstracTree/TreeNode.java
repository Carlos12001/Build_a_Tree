package javathings.trees.abstracTree;

/**
 *
 */
public class TreeNode {
    /**
     *
     */
    private int token;
    /**
     *
     */
    private TreeNode left;
    /**
     *
     */
    private TreeNode right;

    /**
     * @param token
     */
    public TreeNode(int token){
        this.token = token;
        this.left = null;
        this.right = null;
    }

    /**
     * @param token
     * @param left
     * @param right
     */
    public TreeNode(int token, TreeNode left, TreeNode right){
        this.token = token;
        this.left = left;
        this.right = right;
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
