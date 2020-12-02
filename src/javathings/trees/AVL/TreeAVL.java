package javathings.trees.AVL;

import javathings.trees.BST.NodeBST;
import javathings.trees.abstracTree.Tree;
import org.w3c.dom.Node;

/**
 *
 */
public class TreeAVL extends Tree {

    public static void main(String[] args) {
    TreeAVL arbol = new TreeAVL();
    arbol.insert(3);
    arbol.insert(5);
//    arbol.insert(1);
//    arbol.insert(34);
//    arbol.insert(0);
//    System.out.println(arbol.contains(34));
    }
    /**
     *
     */
    public TreeAVL() {
        super();
        setTreeID("treeAVL");
        this.currentArray = new int[]{5, 8, 23, 76, 90, -1};
    }

    public boolean contains(int element) {
        return this.contains(element, (NodeBST) this.root);
    }

    private boolean contains(int element, NodeBST node) {
        if (node == null) {
            return false;
        } else {
            if (element < node.getToken())
                return contains(element, (NodeBST) node.getLeft());
            else if (element > node.getToken())
                return contains(element, (NodeBST) node.getRight());
            else
                return true;
        }
    }

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

        return balance(tree);
    }

    /**
     * @param tree
     * @return
     */
    private NodeAVL balance(NodeAVL tree) {
        if (tree == null) {
            return tree;
        } else if (height((NodeAVL) tree.getLeft()) - height((NodeAVL) tree.getRight()) > 1) { // 1 es la diferencia permitida
            if (height((NodeAVL) tree.getLeft().getLeft()) >= height((NodeAVL) tree.getLeft().getRight()))
                tree = rotateLeft(tree);
            else
                tree = doubleLeftChild(tree);
        } else {
            if (height((NodeAVL) tree.getRight()) - height((NodeAVL) tree.getLeft()) > 1) {
                if (height((NodeAVL) tree.getRight().getRight()) >= height((NodeAVL) tree.getRight().getLeft()))
                    tree = rotateRight(tree);
            } else {
                    tree = doubleRightChild(tree);
                }
            }
        tree.setHeight(Math.max(height((NodeAVL) tree.getLeft()), height((NodeAVL) tree.getRight())) + 1);
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
     * @param child
     * @return
     */
    private NodeAVL doubleLeftChild(NodeAVL child) {
        child.setLeft(rotateRight((NodeAVL) child.getLeft()));
        return rotateLeft(child);
    }

    /**
     * @param child
     * @return
     */
    private NodeAVL doubleRightChild(NodeAVL child) {
        child.setLeft(rotateRight((NodeAVL) child.getLeft()));
        return rotateLeft(child);
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
        this.insert(key, (NodeAVL) this.root);
    }
}
