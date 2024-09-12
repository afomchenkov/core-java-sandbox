package Tasks.Trees;

import java.util.*;

class Node {

    int data;
    Node left = null;
    Node right = null;

    Node(int data) {
        this.data = data;
    }
}

public class AllPathsFromLeafToRoot {

    public static boolean isLeaf(Node n) {
        return n.left == null && n.right == null;
    }

    public static void printPath(Deque<Integer> path) {
        Iterator<Integer> itr = path.descendingIterator();
        while (itr.hasNext()) {
            System.out.print(itr.next());
            if (itr.hasNext()) {
                System.out.print(" —> ");
            }
        }
        System.out.println();
    }

    public static void printLeafToRootPaths(Node node, Deque<Integer> path) {
        if (node == null) {
            return;
        }

        path.addLast(node.data);

        if (isLeaf(node)) {
            printPath(path);
        }

        printLeafToRootPaths(node.left, path);
        printLeafToRootPaths(node.right, path);

        // backtrack: remove the current node after the left, and right subtree are done
        path.removeLast();
    }

    public static void printLeafToRootPaths(Node node) {
        Deque<Integer> path = new ArrayDeque<>();
        printLeafToRootPaths(node, path);
    }

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = new Node(8);
        root.right.left.right = new Node(9);

        printLeafToRootPaths(root);
    }
}
