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

    /*
     * This is pretty straightforward. We use the TreeDiameter code with minor
     * adjustments. We are now looking for the max path from any node to any node.
     * This is basically finding the largest tree diameter sum.
     * 
     * So, instead of returning the heights of subtrees and their maximums, we
     * return the path sums so far. If we reach null, we return the sum of 0 because
     * nulls represent 0. Otherwise, we get the left subtree sum and right subtree
     * sum and add them together along with the current node's value.
     * 
     * Compare the current node's local maximum to the global max and update it.
     * 
     * Remember to make sure to reset path sum to 0 if our current path sum is
     * negative. This is because negative path sums can never be the greatest path.
     * 
     * When returning values to the parents, we want to return the sum from subtree
     * with max sum. We return this along with our current node's value add to it.
     * 
     * Time Complexity: O(N) where N is the nodes in our tree.
     * 
     * Space Complexity: O(N) because of recursion call stack.
     */

    // Global variable to hold largest tree diamter.
    // Biggest tree diameter represents the longest path from any node to any node.
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
        // Initialize the max tree diameter to the lowest possible.
        maxTreeDiameter = Integer.MIN_VALUE;
        // Call the recursive function.
        dfs(root);
        // Return the largest tree diameter.
        return maxTreeDiameter;
    }

    public static int dfs(TreeNode currentNode) {
        // If our node is null, return 0. This indicates a 0 sum.
        // This is the case with a leaf node's children in which they are null.
        // This also applies to being the null child of a node with one child.
        if (currentNode == null) {
            return 0;
        }

        // Traverse left and right and store their respective subtree sums into
        // variables.
        int leftTreeSum = dfs(currentNode.left);
        int rightTreeSum = dfs(currentNode.right);

        // So, we compare this sum with 0 because we may have negative paths.
        // Negative paths can never be max sum paths.
        // So, we reset to 0 if path sum is currently negative.
        leftTreeSum = Math.max(leftTreeSum, 0);
        rightTreeSum = Math.max(rightTreeSum, 0);

        // Calculate the sum path at current node by take the max sum path from left and
        // right and adding the current node's value to it.
        int sumOfPath = leftTreeSum + rightTreeSum + currentNode.val;
        // Update the global diameter if the path is largest so far.
        maxTreeDiameter = Math.max(sumOfPath, maxTreeDiameter);

        // Return to the parent node whichever subtree sum is the largest + current
        // node's value.
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
