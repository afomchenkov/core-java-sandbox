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

    TreeNode() {
    }

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
    public static void printTree(TreeNode root) {
        Queue<TreeNode> items = new LinkedList<>();
        items.add(root);
        int level = 0;

        while (!items.isEmpty()) {
            int len = items.size();

            System.out.println(String.format("------ level %d ------", level));

            for (int i = 0; i < len; ++i) {
                var node = items.remove();

                System.out.println(node.val);

                if (node.left != null) {
                    items.add(node.left);
                }
                if (node.right != null) {
                    items.add(node.right);
                }
            }
            level += 1;
        }

        System.out.println(String.format("------- ------ -------", level));
    }

    public static List<TreeNode> getCousins(TreeNode root, TreeNode node) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isCousinsLevel = false;

        while (!queue.isEmpty()) {
            var childrenLength = queue.size();
            if (isCousinsLevel) {
                break;
            }

            for (int i = 0; i < childrenLength; ++i) {
                var nodeHere = queue.remove();

                if (nodeHere.left != null) {
                    if (nodeHere.left == node) {
                        isCousinsLevel = true;
                        queue.add(nodeHere.left);
                        // skip right sibling
                        continue;
                    }
                    queue.add(nodeHere.left);
                }
                if (nodeHere.right != null) {
                    if (nodeHere.right == node) {
                        isCousinsLevel = true;
                        if (nodeHere.left != null) {
                            // skip left sibling
                            queue.removeLast();
                        }
                    }
                    queue.add(nodeHere.right);
                }
            }
        }

        return new LinkedList<>(queue);
    }

    /**
     * Tree example:
     *       1
     *      / \
     *     2   3
     *    / \    \
     *   4   5    6
     *  / \   \   / \
     * 9  12   8 7  34
     */
    public static void main(String[] args) {
        TreeNode searchNode1 = new TreeNode(3, null, new TreeNode(6, new TreeNode(7), new TreeNode(34)));
        TreeNode searchNode3 = new TreeNode(9);
        TreeNode searchNode4 = new TreeNode(12);
        TreeNode searchNode2 = new TreeNode(4, searchNode3, searchNode4);
        TreeNode searchNode5 = new TreeNode(5, null, new TreeNode(8));
        TreeNode root = new TreeNode(
                1,
                new TreeNode(2, searchNode2, searchNode5),
                searchNode1);

        printTree(root);

        var cousins = getCousins(root, searchNode4);

        for (var n : cousins) {
            System.out.println(n.val);
        }
    }
}
