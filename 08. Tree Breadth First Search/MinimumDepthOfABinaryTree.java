/*
Minimum Depth Of A Binary Tree [EASY]

Find the minimum depth of a binary tree. The minimum depth is the number of nodes
along the shortest path from the root node to the nearest leaf node.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \
       4   5

Output:         2

- EX 2 -
Input:     12
          /   \
         7     1
              / \
             10  5

Output:         2

- EX 3 -
Input:     12
          /   \
         7     1
          \   / \
           9 10  5
            /
           11

Output:         3
*/

import java.util.*;

public class MinimumDepthOfABinaryTree {

    /*
     * We basically use the same BFS code. The only thing is is that we keep track
     * of the depth at every iteration. Every iteration of the while loop is a level
     * of the tree. We iterate our depth at the beginning of each while loop.
     * 
     * When we encounter a leaf node (left and right pointers are null), we
     * immediately return the depth.
     * 
     * Otherwise, we add the children to the queue and continue iteration.
     * 
     * Time Complexity: O(N) where N is the number of nodes in the tree.
     * 
     * Space Complexity: O(N) because we need to store nodes in queue.
     */

    // This is the TreeNode class.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int findDepth(TreeNode root) {
        // If there is no tree, return 0.
        if (root == null) {
            return 0;
        }

        // Create a queue to hold the nodes to process.
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        // Add the root node.
        bfsQueue.offer(root);
        // Initialize depth to 0. Keep track of depth.
        int depth = 0;

        // Iterate while queue is not empty.
        while (!bfsQueue.isEmpty()) {
            // For every iteration, we encounter a new level, so increment depth.
            depth++;
            // Get the level size.
            int levelSize = bfsQueue.size();
            // Iterate through the level size.
            for (int i = 0; i < levelSize; i++) {
                // Get the first node in the queue.
                TreeNode currentNode = bfsQueue.poll();
                // If the node is a leaf node, return the depth immediately.
                if (currentNode.left == null && currentNode.right == null) {
                    return depth;
                }

                // If the node has children, add to the queue.
                if (currentNode.left != null) {
                    bfsQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    bfsQueue.offer(currentNode.right);
                }
            }
        }

        // Return depth.
        return depth;
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree Minimum Depth: " + findDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println("Tree Minimum Depth: " + findDepth(root));
    }
}
