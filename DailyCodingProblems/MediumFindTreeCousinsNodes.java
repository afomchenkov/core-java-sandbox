package DailyCodingProblems;

import java.util.*;

// This pseudo-code recursively traverses the tree and
// records the depth and parent for each node.
// function dfs(node, parentNode = None) {
//     if (node != null) {
//         depth[node.val] = 1 + depth[parentNode.val]
//         parent[node.val] = parentNode
//         dfs(node.left, node)
//         dfs(node.right, node)
//     }
// }
// depth[x] == depth[y] and parent[x] != parent[y]

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

    public static List<TreeNode> getCousins(TreeNode root, TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            var childrenLength = queue.size();
            List<TreeNode> nodesHere = new ArrayList<>();
            boolean isCousinsLevel = false;

            for (int i = 0; i < childrenLength; ++i) {
                var nodeHere = queue.remove();
                
                if (nodeHere.left != null) {
                    if (nodeHere.left == node) {
                        isCousinsLevel = true;
                    }
                    queue.add(nodeHere.left);
                    nodesHere.add(nodeHere.left);
                }
                if (nodeHere.right != null) {
                    if (isCousinsLevel) {
                        // skip current node
                    }
                    if (nodeHere.right == node) {
                        // remove previous if not null
                        // push to result current node
                    }
                    nodesHere.add(nodeHere.right);
                    queue.add(nodeHere.right);
                }
                // add separator between one node siblings
                // queue.add(null);
            }
        }

        return new LinkedList<TreeNode>();
    }
    
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3, null, new TreeNode(6));
        TreeNode root = new TreeNode(
            1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            node
        );

        var cousins = getCousins(root, node);
        for (var nodeHere : cousins) {
            System.out.println(nodeHere.val);
        }
    }
}
