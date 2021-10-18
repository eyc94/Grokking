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
        List<Double> result = new ArrayList<>();

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);

        while (!bfsQueue.isEmpty()) {
            int levelSize = bfsQueue.size();
            double sum = 0.0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = bfsQueue.poll();
                sum += currentNode.val;

                if (currentNode.left != null) {
                    bfsQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    bfsQueue.offer(currentNode.right);
                }
            }
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
