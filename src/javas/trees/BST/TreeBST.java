package javas.trees.BST;

import javas.trees.Abstract.Tree;

/**
 *This class manages the BST trees, it extends from Tress
 */
public class TreeBST extends Tree {
    /**
     * BST node type root
     */
    private NodeBST root;

    /**
     * BST Tree consturctor, sets some initial info
     */
    public TreeBST(){
       super();
       this.setTreeID("treeBST");
       this.setCurrentArray(new int[]{300, 8, 232, 78, 1, -1});
        this.defaultTree();
    }

    /**
     * Method to insert a new node into the BST tree
     * @param key key that will be inserted on the ree
     * @param tree instance to create a BST tree instance
     * @return returns the tree
     */
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

    /**
     * It sets the param as the new root
     * @param key Key that will be inserted on the tree
     */
    @Override
    protected void appendAux(int key) {
        this.root = insert(key, this.root);
    }
}
