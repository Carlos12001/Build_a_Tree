package javathings.trees.B;

import javathings.trees.abstracTree.Tree;
import javathings.trees.abstracTree.TreeNode;

// A BTree
public class TreeB extends Tree {
    private NodeB root; // Pointer to root node
    private int t; // Minimum degree
    private String [] nodeComing;

    // Constructor (Initializes tree as empty)
    public TreeB(int t) {
        super();
        this.setCurrentArray(new int[]{47, 38, 22, 59, 75, -1});
        this.t = t;
        setTreeID("treeB");
    }


    public void traverse() {
        if (this.root != null)
            this.root.traverse();
    }


    public NodeB search(int k) {
        if (this.root == null)
            return null;
        else
            return this.root.search(k);
    }

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

//    public static void main(String[] args) {
//        TreeB treeB = new TreeB(3);
//        treeB.appendAux(47);
//        treeB.appendAux(38);
//        treeB.appendAux(22);
//        treeB.appendAux(59);
//        treeB.appendAux(75);
//
//    }
}
