/*
Sum Of Path Numbers [MEDIUM]

Given a binary tree where each node can only have a digit (0 - 9) value, each root-to-leaf
path will represent a number. Find the total sum of all the numbers represented by all paths.

--- EXAMPLES ---

- EX 1 -
Input:      1
           / \
          7   9
             / \
             2  9

Output:         408
Explanation:    The sum of all path numbers: 17 + 192 + 199 = 408.

- EX 2 -
Input:      1
           / \
          0   1
         /   / \
        1    6  5

Output1:        332
Explanation:    The sum of all path numbers: 101 + 116 + 115 = 332.
*/

public class SumOfPathNumbers {
    // This is the TreeNode class.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int findSumOfPathNumbers(TreeNode root) {
        return -1;
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        // Print.
        System.out.println("Total sum of Path Numbers: " + findSumOfPathNumbers(root));
    }
}
