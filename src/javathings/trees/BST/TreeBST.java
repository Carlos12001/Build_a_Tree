package javathings.trees.BST;

import javathings.trees.abstracTree.Tree;
import javathings.trees.abstracTree.TreeNode;

public class TreeBST extends Tree {

    private NodeBST root;

    public TreeBST(){
       super();
       setTreeID("treeBST");
       this.setCurrentArray(new int[]{300, 8, 232, 78, 1, -1});
    }

//    public void insert(int key){
//        this.root = insertAux(key, this.root);
//    }

    private NodeBST insertAux(int key, NodeBST tree){
        if (tree == null){
            tree = new NodeBST(key);
            return tree;
        } else if (key < tree.getToken()){
            tree.setLeft(insertAux(key, (NodeBST) tree.getLeft()));
        } else if (key > tree.getToken()){
            tree.setRight(insertAux(key, (NodeBST) tree.getRight()));
        }
        return tree;
    }

//    public boolean contains(int element) {
//        return this.contains(element, this.root);
//    }
//
//    private boolean contains(int element, NodeBST node) {
//        if (node == null) {
//            return false;
//        } else {
//            if (element < node.getToken())
//                return contains(element, (NodeBST) node.getLeft());
//            else if (element > node.getToken())
//                return contains(element, (NodeBST) node.getRight());
//            else
//                return true;
//        }
//    }

    @Override
    protected void appendAux(int key) {
        this.root = insertAux(key, this.root);
    }
}
