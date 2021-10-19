/*
--- PROBLEM CHALLENGE 2 ---
Path With Maximum Sum [HARD]

Find the path with the maximum sum in a given binary tree. Write a function that returns
the maximum sum. A path can be defined as a sequence of nodes between any two nodes and
doesn't necessarily pass through the root.

--- EXAMPLES ---

- EX 1 -
Input:      1
           / \
          2   3
         /   / \
        4    5  6

Output:         16
Explanation:    The path with maximum sum is: [4, 2, 1, 3, 6].

- EX 2 -
Input:       1
           /   \
          2      3
         / \   /   \
        1  3   5    6
              / \   /
             7   8  9

Output:        31
Explanation:   The path with maximum sum is: [8, 5, 3, 6, 9].
*/

public class PathWithMaximumSum {

    public static int maxTreeDiameter;

    // This is the TreeNode class.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int findMaximumPathSum(TreeNode root) {
        maxTreeDiameter = Integer.MIN_VALUE;
        dfs(root);
        return maxTreeDiameter;
    }

    public static int dfs(TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftTreeSum = dfs(currentNode.left);
        int rightTreeSum = dfs(currentNode.right);

        leftTreeSum = Math.max(leftTreeSum, 0);
        rightTreeSum = Math.max(rightTreeSum, 0);

        int sumOfPath = leftTreeSum + rightTreeSum + currentNode.val;
        maxTreeDiameter = Math.max(sumOfPath, maxTreeDiameter);

        return Math.max(leftTreeSum, rightTreeSum) + currentNode.val;
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));

        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));
    }
}
