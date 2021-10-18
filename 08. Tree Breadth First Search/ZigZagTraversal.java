/*
ZigZag Traversal [MEDIUM]

Given a binary tree, populate an array to represent its zigzag level order traversal.
You should populate the values of all nodes of the first level from left to right, then
right to left for the next level and keep alternating in the same manner for the
following levels.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \  / \
       4   5 6  7

Output:         [[1], [3, 2], [4, 5, 6, 7]]

- EX 2 -
Input:     12
          /   \
         7     1
          \   / \
           9 10  5
            / \
           20 17

Output:         [[12], [1, 7], [9, 10, 5], [17, 20]]
*/

import java.util.*;

public class ZigZagTraversal {

    /*
     * This is the same code as before. We just keep a flag that tells us whether we
     * are zigzagging in our iteration or not.
     * 
     * Time Complexity: O(N) where N is the number of nodes in our tree.
     * 
     * Space Complexity: O(N) because we store nodes in our list to return and in
     * queue.
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

    public static List<List<Integer>> traverse(TreeNode root) {
        // List to of levels with their values.
        List<List<Integer>> result = new ArrayList<>();

        // Queue that holds next node for processing.
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        // Add the root first.
        bfsQueue.offer(root);
        // Flag that tells us whether we go left-to-right or right-to-left.
        boolean zigzag = false;

        // Iterate while queue is not empty.
        while (!bfsQueue.isEmpty()) {
            // Get the level size.
            int levelSize = bfsQueue.size();
            // Create a current level list.
            // Implement via LinkedList because we are going to add to the front.
            List<Integer> currentLevel = new LinkedList<>();

            // Iterate through the queue size.
            for (int i = 0; i < levelSize; i++) {
                // Remove the first node in the queue.
                TreeNode currentNode = bfsQueue.poll();
                // If we are on zigzagging iteration, add values to front.
                if (zigzag) {
                    currentLevel.add(0, currentNode.val);
                } else { // If we are not zigzagging, add values to the back.
                    currentLevel.add(currentNode.val);
                }

                // If the current node has children, add them to queue.
                if (currentNode.left != null) {
                    bfsQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    bfsQueue.offer(currentNode.right);
                }
            }
            // Change zigzag flag after every level.
            zigzag = !zigzag;
            // Add the level to the list to return.
            result.add(currentLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        List<List<Integer>> result = traverse(root);
        System.out.println("Zigzag traversal: " + result);
    }
}
