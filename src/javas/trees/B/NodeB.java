package javas.trees.B;


import javas.trees.Abstract.TreeNode;

/**
 * This class manages all the operations of the NodeB tree Node
 */
class NodeB extends TreeNode {
    /**
     * Saved keys on a list
     */
    private int[] keys;
    /**
     *Minimum degree (defines the range for number of keys)
     */
    private final int t;
    /**
     *An array of child pointers
     */
    private NodeB[] childNodes;
    /**
     *Current number of keys
     */
    private int countKeys;
    /**
     *It defines if a node has children or it is a leaf node
     */
    private boolean leaf;

    /**
     * Constructor sets all the atributes of the class
     * @param t number of keys
     * @param leaf if the node is a leaf or not
     */
    public NodeB(int t, boolean leaf) {
        super(0);
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.childNodes = new NodeB[2 * t];
        this.countKeys = 0;
    }

    /**
     * Performs one of the  insertion types of this tree, its the case whe the leaf is not full
     * @param k key to insert in the tree
     */
    public void insertNonFull(int k)
    {

        int i = this.countKeys-1;

        //  revisa si este es un nodo hoja
        if (this.leaf)
        {

            while (i >= 0 && this.keys[i] > k)
            {
                this.keys[i+1] = this.keys[i];
                i--;
            }

            // Insert the new key at found location
            keys[i+1] = k;
            this.countKeys = this.countKeys+1;
        }
        else // Si no es hoja
        {

            while (i >= 0 && keys[i] > k)
                i--;

            //Reliaza el split si necesario, con la condicion de t/2
            if (this.childNodes[i+1].countKeys == 2*t-1)
            {

                splitChild(i+1, this.childNodes[i+1]);


                if (keys[i+1] < k)
                    i++;
            }
            this.childNodes[i+1].insertNonFull(k);
        }
    }

    /**
     * If the leaf is full, it performs a split and positions the values differently
     * @param i amount of keys
     * @param y Node to insert
     */
    public void splitChild(int i, NodeB y) {
        //Crea el nuevo Nodo Hijo
        NodeB z = new NodeB(y.t, y.leaf);
        z.countKeys = t - 1;

        // Copia el ultimo de y i+t en el nodo i de z
        for (int j = 0; j < t-1; j++)
            z.keys[j] = y.keys[j+t];

        // Copia el ultimo t hijo de y en z
        if (y.leaf == false)
        {
            for (int j = 0; j < t; j++)
                z.childNodes[j] = y.childNodes[j+t];
        }

        // Reduce la cantidad de llaves disponibles
        y.countKeys = t - 1;

        // Realiza el campo necesario para acomodar el nodo hijo,
        // Hace un espacio en un hijo
        for (int j = this.countKeys; j >= i+1; j--)
            this.childNodes[j+1] = this.childNodes[j];

        // Realiza la coneccion con el nuevo nodo
        this.childNodes[i+1] = z;

        // Encuentra a nueva localizacion para el nodo
        for (int j = this.countKeys-1; j >= i; j--)
            keys[j+1] = keys[j];

        // Copy the middle key of y to this node
        keys[i] = y.keys[t-1];

        // Increment count of keys in this node
        this.countKeys = this.countKeys + 1;
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

