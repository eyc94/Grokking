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
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);
        boolean zigzag = false;

        while (!bfsQueue.isEmpty()) {
            int levelSize = bfsQueue.size();

            List<Integer> currentLevel = new LinkedList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = bfsQueue.poll();
                if (zigzag) {
                    currentLevel.add(0, currentNode.val);
                } else {
                    currentLevel.add(currentNode.val);
                }

                if (currentNode.left != null) {
                    bfsQueue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    bfsQueue.offer(currentNode.right);
                }
            }
            zigzag = !zigzag;
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
