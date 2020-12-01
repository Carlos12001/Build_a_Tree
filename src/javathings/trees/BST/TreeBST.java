package javathings.trees.BST;

import javathings.trees.abstracTree.Tree;
import javathings.trees.abstracTree.TreeNode;

public class TreeBST extends Tree {

    private NodeBST root;

    public TreeBST(){
       super();
       setTreeID("treeBST");
    }

    public void insert(int key, NodeBST tree){
        this.root = insertAux(key, tree);
    }

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

    @Override
    protected void appendAux(int key) {
        this.insert(key, this.root);
    }

    @Override
    public void setCurrent() {

    }
    

    @Override
    protected String getCurrent() {
        return null;
    }


}
