/*
Reverse Level Order Traversal [EASY]

Given a binary tree, populate an array to represent its level-by-level traversal in
reverse order, i.e., the lowest level comes first. You should populate the values of
all nodes in each level from left to right in separate sub-arrays.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \  / \
       4   5 6  7

Output:         [[4, 5, 6, 7], [2, 3], [1]]

- EX 2 -
Input:     12
          /   \
         7     1
          \   / \
           9 10  5

Output:         [[9, 10, 5], [7, 1], [12]]
*/

import java.util.*;

public class ReverseLevelOrderTraversal {

    /*
     * This is the same thing as the Binary Tree Level Order Traversal code. The
     * only difference is that we implement the queue with a LinkedList as opposed
     * to an ArrayList. This is because we will be inserting level lists in the
     * front and not the back.
     * 
     * When using ArrayLists, this will push all elements back one spot to the
     * right, making time complexity worse. So, we use LinkedList for constant time
     * insertion operations.
     * 
     * We also change the add() method by using another parameter.
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
        // This is the results to return.
        List<List<Integer>> result = new LinkedList<>();
        // If there is no tree, return the empty result.
        if (root == null) {
            return result;
        }

        // Create a queue to add nodes of our current level.
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        // We add the root to the queue.
        bfsQueue.offer(root);

        // Iterate while the queue is not empty.
        while (!bfsQueue.isEmpty()) {
            // Grab the size of the queue. The size in the beginning of the iterations
            // represents the number of nodes in our current level.
            int currentLevelSize = bfsQueue.size();
            // We create a list that holds values of the current level.
            List<Integer> currentLevel = new ArrayList<>();

            // Iterate the queue only to the current level size. We have to iterate to this
            // because our queue will keep increasing in size when we add the children.
            for (int i = 0; i < currentLevelSize; i++) {
                // Remove the first node.
                TreeNode currentNode = bfsQueue.poll();
                // Add this node's value to the current level.
                currentLevel.add(currentNode.val);

                // If this node has left and right children, add them to the queue.
                if (currentNode.left != null) {
                    bfsQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    bfsQueue.offer(currentNode.right);
                }
            }
            // When the for loop is done, we have finished one level.
            // So, add the level list to the list of levels.
            result.add(0, currentLevel);
        }

        // Return results.
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
        List<List<Integer>> result = traverse(root);
        System.out.println("Level order traversal: " + result);
    }
}
