/*
--- SIMILAR PROBLEMS 1 ---
Maximum Depth Of A Binary Tree

Given a binary tree, find its maximum depth (or height).

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \
       4   5

Output:         3

- EX 2 -
Input:     12
          /   \
         7     1
              / \
             10  5

Output:         3

- EX 3 -
Input:     12
          /   \
         7     1
          \   / \
           9 10  5
            /
           11

Output:         4
*/

import java.util.*;

public class MaximumDepthOfABinaryTree {

    /*
     * Same as Minimum Depth. Just do not return depth when we encounter a leaf
     * node. Go all the way and finish the while loop. Depth will be held in a
     * variable. So, return that.
     * 
     * Time Complexity: O(N) where N is the number of nodes in the tree.
     * 
     * Space Complexity: O(N) because we need to hold nodes in queue.
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
