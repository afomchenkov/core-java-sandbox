package DailyCodingProblems;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class EasyCousinsBTree {
    public int depth(TreeNode root, int n, int depth) {
        if (root == null) {
            return -1;
        }

        if (root.val == n) {
            return depth;
        }

        int l = -1;
        if (root.left != null) {
            l = depth(root.left, n, depth + 1);
        }

        int r = -1;
        if (root.right != null) {
            r = depth(root.right, n, depth + 1);
        }

        return l != -1 ? l : r;
    }

    public boolean hasSameParent(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        boolean l = false;
        if (root.left != null) {
            if (root.left.val == x) {
                return root.right != null ? root.right.val == y : false;
            }
            if (root.left.val == y) {
                return root.right != null ? root.right.val == x : false;
            }
            
            l = hasSameParent(root.left, x, y);
        }

        boolean r = false;
        if (root.right != null) {
            if (root.right.val == x) {
                return root.left != null ? root.left.val == y : false;
            }
            if (root.right.val == y) {
                return root.left != null ? root.left.val == x : false;
            }
            
            r = hasSameParent(root.right, x, y);
        }

        return l || r;
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        int leftDepth = depth(root, x, 0);
        int rightDepth = depth(root, y, 0);

        if (leftDepth != rightDepth) {
            return false;
        }

        return !hasSameParent(root, x, y);
    }
}

class EasyCousinsBTreeHashExample {
    // To save the depth of the first node.
    int recordedDepth = -1;
    boolean isCousin = false;

    // Approach 1: Depth First Search with Branch Pruning
    public boolean dfs(TreeNode node, int depth, int x, int y) {
        if (node == null) {
            return false;
        }

        // Don't go beyond the depth restricted by the first node found.
        if (this.recordedDepth != -1 && depth > this.recordedDepth) {
            return false;
        }

        if (node.val == x || node.val == y) {
            if (this.recordedDepth == -1) {
                // Save depth for the first node found.
                this.recordedDepth = depth;
            }
            // Return true, if the second node is found at the same depth.
            return this.recordedDepth == depth;
        }

        boolean left = dfs(node.left, depth + 1, x, y);
        boolean right = dfs(node.right, depth + 1, x, y);

        // this.recordedDepth != depth + 1 would ensure node x and y are not
        // immediate child nodes, otherwise they would become siblings.
        if (left && right && this.recordedDepth != depth + 1) {
            this.isCousin = true;
        }

        return left || right;
    }

    // Approach 2: Breadth First Search with Early Stopping
    public boolean bfsSearchEarlyStopping(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            boolean siblings = false;
            boolean cousins = false;
            int nodesAtDepth = queue.size();

            for (int i = 0; i < nodesAtDepth; i++) {
                TreeNode node = queue.remove();

                // Encountered the marker.
                // Siblings should be set to false as we are crossing the levels boundary.
                if (node == null) {
                    siblings = false;
                    continue;
                }
                
                if (node.val == x || node.val == y) {
                    // Set both the siblings and cousins flag to true
                    // for a potential first sibling/cousin found.
                    if (!cousins) {
                        siblings = cousins = true;
                    } else {
                        // If the siblings flag is still true this means we are still
                        // within the siblings boundary and hence the nodes are not cousins.
                        return !siblings;
                    }
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
                // Adding the null marker for the siblings
                queue.add(null);
            }
            // After the end of a level if `cousins` is set to true
            // This means we found only one node at this level
            if (cousins) {
                return false;
            }
        }

        return false;
    }

    public int traverse(
        TreeNode root,
        int n,
        Map<Integer, TreeNode> hash,
        int depth
    ) {
        if (root == null) {
            return -1;
        }

        int l = -1;
        if (root.left != null) {
            if (root.left.val == n) {
                hash.put(n, root);
                return depth + 1;
            }
            l = traverse(root.left, n, hash, depth + 1);
        }

        int r = -1;
        if (root.right != null) {
            if (root.right.val == n) {
                hash.put(n, root);
                return depth + 1;
            }
            r = traverse(root.right, n, hash, depth + 1);
        }

        return r != -1 ? r : l;
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer, TreeNode> hash = new HashMap<>();
        
        var left = traverse(root, x, hash, 0);
        var right = traverse(root, y, hash, 0);

        var parentX = hash.get(x);
        var parentY = hash.get(y);

        if (left != right) {
            return false;
        }

        return parentX.val != parentY.val;
    }
}