/*
Minimum Depth Of A Binary Tree [EASY]

Find the minimum depth of a binary tree. The minimum depth is the number of nodes
along the shortest path from the root node to the nearest leaf node.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \
       4   5

Output:         2

- EX 2 -
Input:     12
          /   \
         7     1
              / \
             10  5

Output:         2

- EX 3 -
Input:     12
          /   \
         7     1
          \   / \
           9 10  5
            /
           11

Output:         3
*/

import java.util.*;

public class MinimumDepthOfABinaryTree {
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

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);
        int depth = 0;
        while (!bfsQueue.isEmpty()) {
            depth++;
            int levelSize = bfsQueue.size();

            for (int i = 0; i < levelSize; i++) {

                TreeNode currentNode = bfsQueue.poll();

                if (currentNode.left == null && currentNode.right == null) {
                    return depth;
                }

                if (currentNode.left != null) {
                    bfsQueue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    bfsQueue.offer(currentNode.right);
                }
            }
        }

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
