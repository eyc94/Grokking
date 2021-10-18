/*
--- PROBLEM CHALLENGE 3 ---
Right View Of A Binary Tree [EASY]

Given a binary tree, return an array containing nodes in its right view. The right view
of a binary tree is the set of nodes visible when the tree is seen from the right side.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \  / \
       4   5 6  7

Output:         [1, 3, 7]

- EX 2 -
Input:     12
          /   \
         7     1
        /     / \
       9     10  5
      /
     3

Output:         [12, 1, 5, 3]
*/

import java.util.*;

public class RightViewOfABinaryTree {

    /*
     * This is easy. Same as before. When we iterate through level, check if the
     * node is the last node of that level (i.e., i == levelSize - 1). Add the node
     * to the result list.
     * 
     * Time Complexity: O(N) where 'N' is the number of nodes in our tree.
     * 
     * Space Complexity: O(N) because we need to store nodes in queue.
     */

    // This is the TreeNode class.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }
    }

    public static List<TreeNode> traverse(TreeNode root) {
        // List of right-end nodes to return.
        List<TreeNode> result = new ArrayList<>();
        // If tree is empty, return null.
        if (root == null) {
            return result;
        }

        // Create a queue to hold nodes.
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        // Add the first node.
        bfsQueue.offer(root);

        // Iterate while queue is not empty.
        while (!bfsQueue.isEmpty()) {
            // Get the level size.
            int levelSize = bfsQueue.size();

            // Iterate through the level size.
            for (int i = 0; i < levelSize; i++) {
                // Grab the first node of our queue.
                TreeNode current = bfsQueue.poll();

                // If the node is the last node of our level, add it to result.
                if (i == levelSize - 1) {
                    result.add(current);
                }

                // If there are children, add to queue.
                if (current.left != null) {
                    bfsQueue.offer(current.left);
                }
                if (current.right != null) {
                    bfsQueue.offer(current.right);
                }
            }
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
        root.left.left.left = new TreeNode(3);
        List<TreeNode> result = traverse(root);

        // Print.
        for (TreeNode node : result) {
            System.out.print(node.val + " ");
        }
        System.out.println();
    }
}
