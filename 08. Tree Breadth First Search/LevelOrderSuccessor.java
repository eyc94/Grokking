/*
Level Order Successor [EASY]

Given a binary tree and a node, find the level order successor of the given node
in the tree. The level order successor is the node that appears right after the given
node in the level order traversal.

--- EXAMPLES ---

- EX 1 -
Input:      1       key = 3
          /  \
         2    3
        / \
       4   5

Output:         4

- EX 2 -
Input:     12       key = 9
          /   \
         7     1
          \   / \
           9 10  5

Output:         10

- EX 3 -
Input:     12       key = 12
          /   \
         7     1
          \   / \
           9 10  5

Output:         7
*/

import java.util.*;

public class LevelOrderSuccessor {

    /*
     * This is the same code as before when doing BFS. The only difference is that
     * we do NOT need to keep track of the current level. Just keep adding the
     * children and check if the current node is the key.
     * 
     * If the current node is the key, we know the node after the key is the
     * level-order successor. So, we break.
     * 
     * Then, we return the front of the queue with the peek() method.
     * 
     * Time Complexity: O(N) where N is the number of nodes in the tree.
     * 
     * Space Complexity: O(N) because we need to hold nodes in the queue.
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

    public static TreeNode findSuccessor(TreeNode root, int key) {
        // If there is no tree, return 0.
        if (root == null) {
            return null;
        }

        // Create a queue to hold the nodes to process.
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        // Add the root node.
        bfsQueue.offer(root);

        // Iterate while queue is not empty.
        while (!bfsQueue.isEmpty()) {
            // Get the first node in the queue.
            TreeNode currentNode = bfsQueue.poll();

            // If the node has children, add to the queue.
            if (currentNode.left != null) {
                bfsQueue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                bfsQueue.offer(currentNode.right);
            }
            // If we found the node with they key, just break.
            // This is because the next node in the queue is the successor.
            if (currentNode.val == key) {
                break;
            }
        }
        // Return the front of the queue.
        return bfsQueue.peek();
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        TreeNode result = findSuccessor(root, 12);
        if (result != null) {
            System.out.println(result.val + " ");
        }
        result = findSuccessor(root, 9);
        if (result != null) {
            System.out.println(result.val + " ");
        }
    }
}
