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

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);

        while (!bfsQueue.isEmpty()) {
            int levelSize = bfsQueue.size();

            TreeNode prev = null;
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = bfsQueue.poll();

                if (prev != null) {
                    prev.next = current;
                }
                prev = current;

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
