package javas.trees.B;

import javas.trees.Abstract.Tree;

/**
 *This class manages the B trees, it extends from Trees
 */
public class TreeB extends Tree {
    /**
     * Pointer to root node
     */
    private NodeB root;
    /**
     * Minimum degree
     */
    private int t;
    /**
     * list of the new nodes
     */
    private String [] nodeComing;

    /**
     * Tree B constructor, it sets the initial values for the attributes
     * @param t minimum degree for the tree
     */
    public TreeB(int t) {
        super();
        this.setCurrentArray(new int[]{47, 38, 22, 59, 75, -1});
        this.t = t;
        this.setTreeID("treeB");
        this.defaultTree();
    }

    /**
     * Preforms the logic to append a new node into the tree
     * @param k inserts a new key
     */
    @Override
    protected void appendAux(int k) {


        if (this.root == null)
        {

            this.root = new NodeB(this.t, true);
            this.root.getKeys()[0] = k;
            this.root.setCountKeys(1);
        }
        //Nodo lleno
        else if (this.root.getCountKeys() == 2*t-1) {
                //Crea el nuevo nodo
                NodeB s = new NodeB(this.t, false);
                NodeB [] tmp =  s.getChildNodes();

                //Asigna los hijos del nodo
                tmp[0]= this.root;
                s.setChildNodes(tmp);
                s.splitChild(0, this.root);
                int i = 0;
                if (s.getKeys()[0] < k)
                    i++;
                s.getChildNodes()[i].insertNonFull(k);
                root = s;
            } else //Nodo con espacio
                root.insertNonFull(k);
    }


}
