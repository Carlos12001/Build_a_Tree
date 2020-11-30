package javathings.trees.AVL;

import javathings.trees.abstracTree.Tree;
import javathings.trees.abstracTree.TreeNode;

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
        }
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
                tree = rotateLeftChild(tree);
            else
                tree = doubleLeftChild(tree);
        } else {
            if (height((NodeAVL) tree.getRight()) - height((NodeAVL) tree.getLeft()) > 1) {
                if (height((NodeAVL) tree.getRight().getRight()) >= height((NodeAVL) tree.getRight().getLeft()))
                    tree = rotateRightChild(tree);
            } else {
                    tree = doubleRightChild(tree);
                }
            }
        tree.setHeight(Math.max(height((NodeAVL) tree.getLeft()), height((NodeAVL) tree.getRight())) + 1);
        return tree;
    }

    /**
     * @param child2
     * @return
     */
    private NodeAVL rotateLeftChild(NodeAVL child2) {
        NodeAVL child1 = (NodeAVL) child2.getLeft();
        child2.setLeft(child1.getRight());
        child1.setRight(child2);
        child2.setHeight(Math.max(height((NodeAVL) child2.getLeft()), height((NodeAVL) child2.getRight())) + 1);
        child1.setHeight(Math.max(height((NodeAVL) child1.getLeft()), child2.getHeight()) + 1);
        return child1;
    }

    /**
     * @param child2
     * @return
     */
    private NodeAVL rotateRightChild(NodeAVL child2) {
        NodeAVL child1 = (NodeAVL) child2.getLeft();
        child2.setLeft(child1.getRight());
        child1.setRight(child2);
        child2.setHeight(Math.max(height((NodeAVL) child2.getLeft()), height((NodeAVL) child2.getRight())) + 1);
        child1.setHeight(Math.max(height((NodeAVL) child1.getLeft()), child2.getHeight()) + 1);
        return child1;
    }

    /**
     * @param child
     * @return
     */
    private NodeAVL doubleLeftChild(NodeAVL child) {
        child.setLeft(rotateRightChild((NodeAVL) child.getLeft()));
        return rotateLeftChild(child);
    }

    /**
     * @param child
     * @return
     */
    private NodeAVL doubleRightChild(NodeAVL child) {
        child.setLeft(rotateRightChild((NodeAVL) child.getLeft()));
        return rotateLeftChild(child);
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

    }

    /**
     * @param current 
     */
    @Override
    protected void setCurrent (TreeNode current){

    }

    @Override
    protected String getCurrent() {
        return null;
    }
}
