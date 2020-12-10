package javas.trees.Splay;

import javas.trees.Abstract.Tree;
import javas.trees.Abstract.TreeNode;

/**
 *This class manages the Splay trees, it extends from Trees
 */
public class TreeSplay extends Tree {

    /**
     * class for the Splay Three Node
     * It extends from Tree node
     * It sets the left and right spaces for a node
     */
    private static class NodeSplay extends TreeNode {
        int key;
        NodeSplay left = null, right =null;
        NodeSplay(int key){
            super();
            this.key = key;
        }
    }

    /**
     * Node Splay type root
     */
    private NodeSplay root;

    /**
     * Constructor, sets the initial values for the tree
     */
    public TreeSplay() {
        super();
        this.root = null;
        setTreeID("treeSplay");
        this.setCurrentArray(new int[]{57, 34, 4, 90, 13, -1});
        this.defaultTree();
    }

    /**
     * This method performs a right rotation.
     * @param x rotate the Node to the right
     * @return returns the new node on the position
     */
    private static NodeSplay rightRotate(NodeSplay x) {
        NodeSplay y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    /**
     * This method performs a Left rotation.
     * @param x rotate the Node to the right
     * @return returns the new node on the position
     */
    private static NodeSplay leftRotate(NodeSplay x){
        NodeSplay y = x.right;
        x.right =y.left;
        y.left = x;
        return y;
    }

    /**
     * Main logic for the Splay tree, this makes all the tests to chek if the node can be added to the tree, it reboots when the client fails
     * @param root Node that will be checked
     * @param key the key that will be inserted
     * @return returns the root of the tree or sub tree
     */
    private static NodeSplay splay(NodeSplay root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (root.key > key) {
            if (root.left == null)
                return root;

            //zig-zig
            if (root.left.key > key) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            } else if (root.left.key < key) { //zig-zag
                root.left.right = splay(root.left.right, key);

                //Reviso si todo bien, para hacer la rotación
                if (root.left.right != null) {
                    root.left = leftRotate(root.left);
                }
            }
            //Retorna si es nulo, si no realiza una rotacion
            return (root.left == null) ? root : rightRotate(root);
        } else {
            if (root.right == null)
                return root;

            //zag-zig
            if (root.right.key > key) {
                root.right.left = splay(root.right.left, key);

                //Reviso si todo bien, para hacer la rotación
                if (root.right.left != null)
                    root.right = rightRotate(root.right);

            } else if (root.right.key < key) { // zag-zag
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }
            return (root.right==null) ? root: leftRotate(root);
        }
    }

    /**
     * Sets a new Element in the tree
     * @param root Instance to create a new NodeSplay root
     * @param k The new Element for insert
     * @return returns the node
     */
    private NodeSplay appendAux2(NodeSplay root, int k) {


        if (root == null) return new NodeSplay(k);


        root = splay(root, k);


        if (root.key == k) return root;


        NodeSplay newnode =  new NodeSplay(k);

        if (root.key > k)
        {
            newnode.right = root;
            newnode.left = root.left;
            root.left = null;
        }

        else
        {
            newnode.left = root;
            newnode.right = root.right;
            root.right = null;
        }

        return newnode;
    }

    /**
     * Sets a new Element in the tree
     * @param key The new Element for insert
     */
    @Override
    protected void appendAux(int key) {
        this.root = this.appendAux2(this.root, key);
    }


}
