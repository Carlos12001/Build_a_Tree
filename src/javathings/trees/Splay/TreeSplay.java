package javathings.trees.Splay;

import javathings.trees.abstracTree.Tree;
import javathings.trees.abstracTree.TreeNode;

public class TreeSplay extends Tree {


    private static class NodeSplay extends TreeNode {
        int key;
        NodeSplay left = null, right =null;
        NodeSplay(int key){
            super();
            this.key = key;
        }
    }

    private NodeSplay root;

    TreeSplay() {
        super();
        this.root = null;
        this.setCurrentArray(new int[]{});
    }

    private static NodeSplay rightRotate(NodeSplay x) {
        NodeSplay y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    private static NodeSplay leftRotate(NodeSplay x){
        NodeSplay y = x.left;
        x.right =y.left;
        y.left = x;
        return y;
    }

    private static NodeSplay splay(NodeSplay root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (root.key > key) {
            if (root.left == null)
                return root;

            //zig-zag
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

    private NodeSplay appendAux2(NodeSplay root, int k) {

        NodeSplay z = root;
        NodeSplay p = null;
        while (z != null)
        {
            p = z;
            if (k > z.key)
                z = z.right;
            else
                z = z.left;
        }
        z = new NodeSplay(k);

        if (p == null)
            root = z;
        else if (k > p.key)
            p.right = z;
        else
            p.left = z;
        return root = TreeSplay.splay(root, k);
    }

    /**
     * Sets a new Element in the tree
     *
     * @param key The new Element for insert
     */
    @Override
    protected void appendAux(int key) {
        this.root = this.appendAux2(this.root, key);
        System.out.println(this.root);
    }

    public NodeSplay search(int key){
        return TreeSplay.splay(this.root, key);
    }


}
