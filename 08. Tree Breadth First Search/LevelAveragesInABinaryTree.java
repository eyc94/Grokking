/*
Level Averages In A Binary Tree [EASY]

Given a binary tree, populate an array to represent the averages of all of its levels.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \  / \
       4   5 6  7

Output:         [1, 2.5, 5.5]

- EX 2 -
Input:     12
          /   \
         7     1
        / \   / \
       9   2 10  5

Output:         [12.0, 4.0, 6.5]
*/

import java.util.*;

public class LevelAveragesInABinaryTree {

    /*
     * This is the same code as before. Only difference is that we are return a list
     * of doubles for the average of each level.
     * 
     * On each level iteration, we are just accumulating the sum of our level and
     * dividing by the level's size. We then add this average to the list we return.
     * 
     * Time Complexity: O(N) where N is the number of nodes in our tree.
     * 
     * Space Complexity: O(N) because we need to store the averages in the result.
     * We need to store the nodes in our queue as well.
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

    public static List<Double> findLevelAverages(TreeNode root) {
        // List of averages to return.
        List<Double> result = new ArrayList<>();

        // Create a queue to hold nodes for processing.
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        // Add the root node.
        bfsQueue.offer(root);

        // Iterate while the queue is not empty.
        while (!bfsQueue.isEmpty()) {
            // Get the level size by getting the queue size.
            int levelSize = bfsQueue.size();
            // Initialize a sum to accumulate in the interval.
            double sum = 0.0;
            // Iterate through the level size.
            for (int i = 0; i < levelSize; i++) {
                // Get the first node.
                TreeNode currentNode = bfsQueue.poll();
                // Add the node's value to the sum.
                sum += currentNode.val;

                // If the node has children, add children to queue.
                if (currentNode.left != null) {
                    bfsQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    bfsQueue.offer(currentNode.right);
                }
            }
            // Calculate the average of the current level and add to result list.
            double average = sum / levelSize;
            result.add(average);
        }
        return result;
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Double> result = findLevelAverages(root);
        System.out.println("Level averages are: " + result);
    }
}
