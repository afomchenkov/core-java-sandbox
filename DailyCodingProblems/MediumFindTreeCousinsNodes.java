package DailyCodingProblems;

import java.util.*;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Two nodes in a binary tree can be called cousins if they are on the same level of the 
 * tree but have different parents. For example, in the following diagram 4 and 6 are cousins.
 * 
 *     1
 *    / \
 *   2   3
 *  / \   \
 * 4   5   6
 * 
 * Given a binary tree and a particular node, find all cousins of that node.
 * 
 */
public class MediumFindTreeCousinsNodes {

    public static List<TreeNode> getCousins(TreeNode root) {
        

        return new LinkedList<TreeNode>();
    }
    
    public static void main(String[] args) {
        
        TreeNode root = new TreeNode(
            1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3, null, new TreeNode(6))
        );

        var cousins = getCousins(root);
        for (var node : cousins) {
            System.out.println(node.val);
        }
    }
}
