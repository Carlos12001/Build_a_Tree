package javas.trees.BST;


import javas.trees.Abstract.TreeNode;

/**
 * Class that manages the aspects related to the BST Node
 */
public class NodeBST extends TreeNode {

    /**
     * Constructor, sets the param token into super class
     * @param token value for the token
     */
    public NodeBST(int token){
        super(token);
    }

    /**
     * Constructor for the BST node, sets params into super class
     * @param token value for the token
     * @param left left tree for the node
     * @param right right tree for the node
     */
    public NodeBST(int token, NodeBST left, NodeBST right){
        super(token, left, right);
    }
}
