package javathings.trees.B;

import javathings.trees.abstracTree.Tree;
import javathings.trees.abstracTree.TreeNode;

// A BTree
public class TreeB extends Tree {
    private NodeB root; // Pointer to root node
    private int t; // Minimum degree
    private String [] nodeComing;

    // Constructor (Initializes tree as empty)
    TreeB(int t) {
        super();
        this.t = t;
    }

    // function to traverse the tree
    public void traverse() {
        if (this.root != null)
            this.root.traverse();
        System.out.println();
    }

    // function to search a key in this tree
    public NodeB search(int k) {
        if (this.root == null)
            return null;
        else
            return this.root.search(k);
    }

    @Override
    protected void appendAux(int key) {

    }

    @Override
    protected void setCurrent(TreeNode current) {

    }

    @Override
    protected String getCurrent() {
        return null;
    }
}
