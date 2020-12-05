package javas.trees.BST;

import javas.trees.Abstract.Tree;

public class TreeBST extends Tree {

    private NodeBST root;

    public TreeBST(){
       super();
       this.setTreeID("treeBST");
       this.setCurrentArray(new int[]{300, 8, 232, 78, 1, -1});
        this.defaultTree();
    }
    

    private NodeBST insert(int key, NodeBST tree){
        if (tree == null){
            tree = new NodeBST(key);
            return tree;
        } else if (key < tree.getToken()){
            tree.setLeft(insert(key, (NodeBST) tree.getLeft()));
        } else if (key > tree.getToken()){
            tree.setRight(insert(key, (NodeBST) tree.getRight()));
        }
        return tree;
    }




    @Override
    protected void appendAux(int key) {
        this.root = insert(key, this.root);
    }
}
