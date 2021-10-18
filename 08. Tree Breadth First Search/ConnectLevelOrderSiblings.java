/*
Connect Level Order Siblings [MEDIUM]

Given a binary tree, connect each node with its level order successor. The last
node of each level should point to a null node.

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
          \   / \
           9 10  5

Output:         [[12], [7, 1], [9, 10, 5]]
*/

import java.util.*;

public class ConnectLevelOrderSiblings {

    /*
     * This is the same code as all others. The only difference is that in each
     * level, we connect all siblings. We keep a reference to the previous node in
     * the level. If the current node is the first node, the prev will be null so
     * just point prev to curr for the next iteration.
     * 
     * Otherwise, connect.
     * 
     * Time Complexity: O(N) where N is the number of nodes in our tree.
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

        void printLevelOrder() {
            TreeNode nextLevelRoot = this;
            while (nextLevelRoot != null) {
                TreeNode current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) {
                    System.out.print(current.val + " ");
                    if (nextLevelRoot == null) {
                        if (current.left != null) {
                            nextLevelRoot = current.left;
                        } else if (current.right != null) {
                            nextLevelRoot = current.right;
                        }
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }

    public static void connect(TreeNode root) {

        // Create a queue to hold nodes for processing.
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        // Add the root node.
        bfsQueue.offer(root);

        // Iterate while the queue is not empty.
        while (!bfsQueue.isEmpty()) {
            // Get the level size of the queue.
            int levelSize = bfsQueue.size();

            // Create a prev pointer that points to the previous node.
            TreeNode prev = null;
            // Iterate through level.
            for (int i = 0; i < levelSize; i++) {
                // Get the first node from queue.
                TreeNode current = bfsQueue.poll();

                // If the current node is NOT the first node, connect previous node to current.
                if (prev != null) {
                    prev.next = current;
                }
                // If the current is the first node, just point prev to current.
                // Same for other situations too. Move prev to curr for next iteration.
                prev = current;

                // Add children to queue, if exists.
                if (current.left != null) {
                    bfsQueue.offer(current.left);
                }
                if (current.right != null) {
                    bfsQueue.offer(current.right);
                }
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
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }
}
