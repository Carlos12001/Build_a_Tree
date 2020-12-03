package javathings.trees.AVL;

import javathings.trees.Abstract.Tree;


/**
 *
 */
public class TreeAVL extends Tree {


    /**
     *
     */
    public TreeAVL() {
        super();
        setTreeID("treeAVL");
        this.currentArray = new int[]{5, 8, 23, 76, 90, -1};
        this.defaultTree();
    }


    /**
     * @param key
     */
    public void insert(int key){
        this.root = insert(key, (NodeAVL) this.root);
    }

    /**
     * @param key
     * @param tree
     * @return
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
     * @param N
     * @return
     */
    private int getBalance(NodeAVL N) {
        if (N == null)
            return 0;
        return height((NodeAVL) N.getLeft()) - height((NodeAVL) N.getRight());
    }
    /**
     * @param tree
     * @return
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
     * @param root
     * @return
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
     * @param root
     * @return
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
     * @param tree
     * @return
     */
    private int height(NodeAVL tree) {
        return tree == null ? -1 : tree.getHeight();
    }

    /**
     * @param key
     */
    @Override
    protected void appendAux(int key){
        this.root = insert(key, (NodeAVL) this.root);
    }
}
