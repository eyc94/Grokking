/*
--- PROBLEM CHALLENGE 1 ---
Connect All Level Order Siblings [MEDIUM]

Given a binary tree, connect each node with its level order successor. The last node of each
level should point to the first node of the next level.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \  / \
       4   5 6  7

Output:         1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null

- EX 2 -
Input:     12
          /   \
         7     1
        /     / \
       9     10  5

Output:         12 -> 7 -> 1 -> 9 -> 10 -> 5 -> null
*/

import java.util.*;

public class ConnectAllLevelOrderSiblings {

    /*
     * This is the same code as all others. We DO NOT care about levels and its
     * size. Since we are just processing all nodes in the queue and connecting them
     * all, we just want to keep adding children if necessary.
     * 
     * Use the same prev and current pointer tactic like in the previous assignment.
     * This time prev and current are declared outside the while loop because we are
     * going to continuously run our while loop until the queue is empty. We do not
     * want our prev and curr to reset.
     * 
     * Time Complexity: O(N) where N is the number of nodes in our tree.
     * 
     * Space Complexity: O(N) because we need to hold nodes in our queue.
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

    public static void connect(TreeNode root) {
        // Create a queue to hold nodes for processing.
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        // Add the first root node.
        bfsQueue.offer(root);

        // Create a prev and current pointer to keep track of traversal.
        TreeNode prev = null;
        TreeNode current = null;
        // Iterate while queue is NOT empty.
        while (!bfsQueue.isEmpty()) {
            // Grab the first node of the queue.
            current = bfsQueue.poll();

            // If the current node is NOT the first node, connect prev to current.
            if (prev != null) {
                prev.next = current;
            }
            // If it is the first node, just point prev to current.
            // Also, after pointing prev.next to current, we have to update prev for the
            // next iteration.
            prev = current;

            // If there are any children, add them to the queue.
            if (current.left != null) {
                bfsQueue.offer(current.left);
            }
            if (current.right != null) {
                bfsQueue.offer(current.right);
            }
        }
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        connect(root);

        // Print.
        TreeNode current = root;
        System.out.println("Traversal using 'next' pointer: ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
