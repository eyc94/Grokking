/*
Binary Tree Level Order Traversal [EASY]

Given a binary tree, populate an array to represent its level-by-level traversal. You
should populate the values of all nodes of each level from left to right in separate
sub-arrays.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \  / \
       4   5 6  7

Output:         [[1], [2, 3], [4, 5, 6, 7]]

- EX 2 -
Input:     12
          /   \
         7     1
        / \   / \
           9 10  5

Output;         [[12], [7, 1], [9, 10, 5]]
*/

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

    /*
     * This shows a generic BFS traversal on a Binary Tree.
     * 
     * We use a queue to hold nodes of a certain level. Since BFS is a level-order
     * traversal, a queue is perfect for this because we process nodes in the order
     * they were put in.
     * 
     * We first put in the root node. Run the while loop until this queue is empty.
     * We get the size of the queue. This represents the number of nodes in our
     * level we are processing right now.
     * 
     * Create a levels list to hold the level's nodes' values. We then iterate
     * through the size of the queue, and remove a node each time. We take the
     * node's value and place it in the list of values for the current level. Check
     * to see if the node has any children. Add the left and right children, if
     * exists.
     * 
     * Once the for loop is done, we add this current level list to the results
     * list. So long as there's children to process, this queue will not be empty.
     * So, when we encounter a leaf, we stop.
     * 
     * Time Complexity: O(N) where N is the number of nodes in our tree. This is
     * because we process each node once.
     * 
     * Space Complexity: O(N). We return a list containing all nodes. We have a
     * queue that holds at most N/2 nodes for the last level in our tree.
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
        List<List<Integer>> result = new ArrayList<>();
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
            result.add(currentLevel);
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