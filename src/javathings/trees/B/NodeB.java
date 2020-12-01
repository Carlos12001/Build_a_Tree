package javathings.trees.B;


import javathings.trees.abstracTree.TreeNode;

// A BTree node 
class NodeB extends TreeNode {
    private int[] keys; // An array of keys
    private final int t; // Minimum degree (defines the range for number of keys)
    private NodeB[] childNodes; // An array of child pointers
    private int countKeys; // Current number of keys
    private boolean leaf; //

    // Constructor
    public NodeB(int t, boolean leaf) {
        super(0);
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.childNodes = new NodeB[2 * t];
        this.countKeys = 0;
    }

    public void insertNonFull(int k)
    {
        // Initialize index as index of rightmost element
        int i = this.countKeys-1;

        // If this is a leaf node
        if (this.leaf)
        {
            // The following loop does two things
            // a) Finds the location of new key to be inserted
            // b) Moves all greater keys to one place ahead
            while (i >= 0 && this.keys[i] > k)
            {
                this.keys[i+1] = this.keys[i];
                i--;
            }

            // Insert the new key at found location
            keys[i+1] = k;
            this.countKeys = this.countKeys+1;
        }
        else // If this node is not leaf
        {
            // Find the child which is going to have the new key
            while (i >= 0 && keys[i] > k)
                i--;

            // See if the found child is full
            if (this.childNodes[i+1].countKeys == 2*t-1)
            {
                // If the child is full, then split it
                splitChild(i+1, this.childNodes[i+1]);

                // After split, the middle key of C[i] goes up and
                // C[i] is splitted into two.  See which of the two
                // is going to have the new key
                if (keys[i+1] < k)
                    i++;
            }
            this.childNodes[i+1].insertNonFull(k);
        }
    }


    public void splitChild(int i, NodeB y) {
        //Crea el nuevo Nodo Hijo
        NodeB z = new NodeB(y.t, y.leaf);
        z.countKeys = t - 1;

        // Copy the last (t-1) keys of y to z
        for (int j = 0; j < t-1; j++)
            z.keys[j] = y.keys[j+t];

        // Copy the last t children of y to z
        if (y.leaf == false)
        {
            for (int j = 0; j < t; j++)
                z.childNodes[j] = y.childNodes[j+t];
        }

        // Reduce the number of keys in y
        y.countKeys = t - 1;

        // Since this node is going to have a new child,
        // create space of new child
        for (int j = this.countKeys; j >= i+1; j--)
            this.childNodes[j+1] = this.childNodes[j];

        // Link the new child to this node
        this.childNodes[i+1] = z;

        // A key of y will move to this node. Find the location of
        // new key and move all greater keys one space ahead
        for (int j = this.countKeys-1; j >= i; j--)
            keys[j+1] = keys[j];

        // Copy the middle key of y to this node
        keys[i] = y.keys[t-1];

        // Increment count of keys in this node
        this.countKeys = this.countKeys + 1;
    }

    // A function to traverse all nodes in a subtree rooted with this node
    public void traverse() {

        // There are n keys and n+1 children, travers through n keys
        // and first n children
        int i = 0;
        for (i = 0; i < this.countKeys; i++) {

            // If this is not leaf, then before printing key[i],
            // traverse the subtree rooted with child C[i].
            if (this.leaf == false) {
                this.childNodes[i].traverse();
            }
            System.out.print(keys[i] + " ");
        }

        // Print the subtree rooted with last child
        if (leaf == false)
            this.childNodes[i].traverse();
    }

    // A function to search a key in the subtree rooted with this node.
    public NodeB search(int k) { // returns NULL if k is not present.

        // Find the first key greater than or equal to k
        int i = 0;
        while (i < this.countKeys && k > keys[i])
            i++;

        // If the found key is equal to k, return this node
        if (keys[i] == k)
            return this;

        // If the key is not found here and this is a leaf node
        if (leaf == true)
            return null;

        // Go to the appropriate child
        return this.childNodes[i].search(k);

    }

    /**
     * Gets keys.
     *
     * @return Value of keys.
     */
    public int[] getKeys() {
        return keys;
    }

    /**
     * Gets childNodes.
     *
     * @return Value of childNodes.
     */
    public NodeB[] getChildNodes() {
        return childNodes;
    }

    /**
     * Gets leaf.
     *
     * @return Value of leaf.
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * Gets t.
     *
     * @return Value of t.
     */
    public int getT() {
        return t;
    }

    /**
     * Gets countKeys.
     *
     * @return Value of countKeys.
     */
    public int getCountKeys() {
        return countKeys;
    }

    /**
     * Sets new countKeys.
     *
     * @param countKeys New value of countKeys.
     */
    public void setCountKeys(int countKeys) {
        this.countKeys = countKeys;
    }

    /**
     * Sets new childNodes.
     *
     * @param childNodes New value of childNodes.
     */
    public void setChildNodes(NodeB[] childNodes) {
        this.childNodes = childNodes;
    }
}

