package javathings.trees.B;


import javathings.trees.abstracTree.TreeNode;

// A BTree node 
class NodeB extends TreeNode {
    private int[] keys; // An array of keys
    private int t; // Minimum degree (defines the range for number of keys)
    private NodeB[] childNodes; // An array of child pointers
    private int countKeys; // Current number of keys
    private boolean leaf; //

    // Constructor
    public NodeB(int maxCount, boolean leaf) {
        super(0);
        this.t = maxCount;
        this.leaf = leaf;
        this.keys = new int[2 * maxCount - 1];
        this.childNodes = new NodeB[2 * maxCount];
        this.countKeys = 0;
    }

    // A function to traverse all nodes in a subtree rooted with this node
    public void traverse() {

        // There are n keys and n+1 children, travers through n keys
        // and first n children
        int i = 0;
        for (i = 0; i < this.countKeys; i++) {
            // If this is not leaf, then before printing key[i],
            // traverse the subtree rooted with child C[i].
            if (!this.leaf) {
                childNodes[i].traverse();
            }
            System.out.print(keys[i] + " ");
        }

        // Print the subtree rooted with last child
        if (!leaf)
            childNodes[i].traverse();
    }

    // A function to search a key in the subtree rooted with this node.
    public NodeB search(int k) { // returns NULL if k is not present.

        // Find the first key greater than or equal to k
        int i = 0;
        while (i < countKeys && k > keys[i])
            i++;

        // If the found key is equal to k, return this node
        if (keys[i] == k)
            return this;

        // If the key is not found here and this is a leaf node
        if (leaf)
            return null;

        // Go to the appropriate child
        return childNodes[i].search(k);

    }
}

