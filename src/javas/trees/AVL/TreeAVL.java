package javas.trees.AVL;

import javas.trees.Abstract.Tree;


/**
 *This class manages the AVL trees, it extends from Tress
 */
public class TreeAVL extends Tree {


    /**
     *This is the constructor and it has a list with the order of the no
     */
    public TreeAVL() {
        super();
        this.setTreeID("treeAVL");
        this.currentArray = new int[]{5, 8, 23, 76, 90, -1};
        this.defaultTree();
    }

    /**
     * This is the method to insert a new node in the tree.
     * @param key This is the root of the tree or sub tree
     * @param tree This would be the right or left side of the tree
     * @return a tree o else balances the tree
     */
    public NodeAVL insert(int key, NodeAVL tree) {
        if (tree == null) {
            return new NodeAVL(key);
        } else if (key < tree.getToken()) {
            tree.setLeft(insert(key, (NodeAVL) tree.getLeft()));
        } else if (key > tree.getToken()) {
            tree.setRight(insert(key, (NodeAVL) tree.getRight()));
        } else {
            return tree;
        }
        tree.setHeight(1 + Math.max(height((NodeAVL) tree.getLeft()), height((NodeAVL) tree.getRight())));

        return balance(tree, key);
    }

    /**
     * Method to get the proper balance needed for the tree
     * @param N Node that will be checked
     * @return checks if it needs to be rebalanced
     */
    private int getBalance(NodeAVL N) {
        if (N == null)
            return 0;
        return height((NodeAVL) N.getLeft()) - height((NodeAVL) N.getRight());
    }
    /**
     * Method to get the tree balanced, this is a requirement of this type of tree
     * @param tree Node to rotate
     * @param key The key that of that node to know ot needs to get balance and make the proper operation
     * @return returns the needed operation or the tree
     */
    private NodeAVL balance(NodeAVL tree, int key) {

        int balance = getBalance(tree);

        if (balance > 1 && key < tree.getLeft().getToken())
            return rotateRight(tree);

        // Right Right
        if (balance < -1 && key > tree.getRight().getToken())
            return rotateLeft(tree);

        // Left Right
        if (balance > 1 && key > tree.getLeft().getToken()) {
            tree.setLeft(rotateLeft((NodeAVL) tree.getLeft()));
            return rotateRight(tree);
        }

        // Right Left
        if (balance < -1 && key < tree.getRight().getToken()) {
            tree.setRight(rotateRight((NodeAVL) tree.getRight()));
            return rotateLeft(tree);
        }

        return tree;
    }

    /**
     * It preforms a left rotation, move necesary for other operations
     * Specific of the AVL tree
     * @param root rotates a node to the left
     * @return a new root
     */
    private NodeAVL rotateLeft(NodeAVL root) {
        NodeAVL x = (NodeAVL) root.getRight();
        NodeAVL T2 = (NodeAVL) x.getLeft();

        x.setLeft(root);
        root.setRight(T2);

        root.setHeight(Math.max(height((NodeAVL) root.getLeft()), height((NodeAVL) root.getRight())) + 1);
        x.setHeight(Math.max(height((NodeAVL) x.getLeft()), height((NodeAVL) x.getRight())) + 1);

        return root;
    }

    /**
     * It preforms a right rotation, move necesary for other operations
     * Specific of the AVL tree
     * @param root  rotates a node to the right
     * @return a new root
     */
    private NodeAVL rotateRight(NodeAVL root) {
        NodeAVL x = (NodeAVL) root.getLeft();
        NodeAVL T2 = (NodeAVL) x.getRight();

        x.setRight(root);
        root.setLeft(T2);

        root.setHeight(Math.max(height((NodeAVL) root.getLeft()), height((NodeAVL) root.getRight())) + 1);
        x.setHeight(Math.max(height((NodeAVL) x.getLeft()), x.getHeight()) + 1);
        return x;
    }

    /**
     * It sets the hight of the tree based ont the amount of levels that it has
     * @param tree height value for the tree stating from a node
     * @return returns the height value
     */
    private int height(NodeAVL tree) {
        return tree == null ? -1 : tree.getHeight();
    }

    /**
     * Sets the root of the tree
     * @param key inserts a node with an specific key
     */
    @Override
    protected void appendAux(int key){
        this.root = insert(key, (NodeAVL) this.root);
    }
}
