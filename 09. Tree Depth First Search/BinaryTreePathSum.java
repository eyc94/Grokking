/*
Binary Tree Path Sum [EASY]

Given a binary tree and a number 'S', find if the tree has a path from root-to-leaf
such that the sum of all the node values of that path equals 'S'.

--- EXAMPLES ---

- EX 1 -
Input:      1       S = 10
           / \
          2   3
         / \ / \
        4  5 6  7

Output:         true
Explanation:    The path with sum '10' is 1 -> 3 -> 6

- EX 2 -
Input:      12      S1 = 23, S2 = 16
           /  \
          7    1
         /    / \
        9    10  5

Output1:        true
Explanation:    The path with sum '23' is 12 -> 1 -> 10

Output2:        false
Explanation:    There is no root-to-leaf path with sum '16'.
*/

public class BinaryTreePathSum {

    /*
     * This uses recursive calls to solve the problem.
     * 
     * We have a base case. If the root or node is null, return false. This will
     * denote the null children of a leaf node. Once we reach the leaf and its value
     * does not add to the sum, we traverse to the left and right children. These
     * children will both be null and will return false.
     * 
     * Start the DFS with the root of the tree.
     * 
     * If the current node is NOT a leaf node, we do two things:
     * 
     * 1. Subtract the value of the current node from the given number to get a new
     * sum: sum = sum - node.value.
     * 
     * 2. Make two recursive calls for both the children of the current node with
     * the new number calculated in the previous step.
     * 
     * At every step, see if the current node being visited is a leaf node and if
     * its value is equal to the given number 'S'. If both of these are true, we
     * have found a root-to-leaf path. Return true.
     * 
     * If current node is a leaf but value is not equal to 'S', return false.
     * 
     * Time Complexity: O(N) where 'N' is the number of nodes in the tree. This is
     * because we traverse each node once.
     * 
     * Space Complexity: O(N) because of the space used to store the recursion
     * stack. Worst case when tree is a LinkedList.
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

    public static boolean hasPath(TreeNode root, int sum) {
        // This is the case for an empty tree.
        // This is the base case.
        if (root == null) {
            return false;
        }

        // If the node's value is the sum and it's a leaf, return true immediately.
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }

        // Otherwise, we traverse the left and right subtrees.
        // This returns true if either branch to left and right return true.
        return hasPath(root.left, sum - root.val) || hasPath(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        // Print.
        System.out.println("Tree has path: " + hasPath(root, 23));
        System.out.println("Tree has path: " + hasPath(root, 16));
    }
}