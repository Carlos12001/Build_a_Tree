package test;

import javathings.trees.AVL.TreeAVL;
import javathings.trees.B.TreeB;
import javathings.trees.BST.TreeBST;
import javathings.trees.Splay.TreeSplay;
import javathings.trees.Abstract.Tree;

public class TESTER {
    public static void main(String[] args) {
        Tree t1 = new TreeB(3);
        Tree t2 = new TreeSplay();
        Tree t3 = new TreeAVL();
        Tree t4 = new TreeBST();


        Tree [] t =  {t1, t2, t3, t4};
        int i = 0;
        String []  n = {"47", "57", "5", "300"};
        for (Tree tree: t) {
            System.out.println(tree.getCurrent());
            tree.append(tree.getTreeID() + "@" + n[i]);
            System.out.println(tree.getCurrent());
            i ++;
        }
        t[0].append(t[0].getTreeID() + "@" + n[0]);
        System.out.println();
        System.out.println();
        System.out.println();
        for (Tree tree: t) {
            System.out.println(tree.getCurrent());
            tree.defaultTree();
            System.out.println(tree.getCurrent());
        }
    }
}
