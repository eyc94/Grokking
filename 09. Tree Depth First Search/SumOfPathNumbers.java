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

Output:        332
Explanation:    The sum of all path numbers: 101 + 116 + 115 = 332.
*/

public class SumOfPathNumbers {

    /*
     * The idea of recursing here is the same. The difference is that when we
     * return, we add to the result of the right and left traversals. This is kind
     * of hard to grasp at first.
     * 
     * Time Complexity: O(N) where N is the number of nodes in our tree. We traverse
     * the nodes once.
     * 
     * Space Complexity: O(N) because of the recursion call stack.
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

    public static int findSumOfPathNumbers(TreeNode root) {
        // Call the dfs helper with the initial sum of 0.
        return findRootToLeafPathNumbers(root, 0);
    }

    public static int findRootToLeafPathNumbers(TreeNode currentNode, int pathSum) {
        // If the node is null, return 0.
        // This only happens when the node is the child of a leaf and when the node is a
        // child of a node with one child (other child).
        if (currentNode == null) {
            return 0;
        }

        // Update the path sum.
        // We multiply our path sum by 10 to leave room for the next digit to be tacked
        // on.
        pathSum = 10 * pathSum + currentNode.val;

        // If we encounter a leaf, return the path sum.
        if (currentNode.left == null && currentNode.right == null) {
            return pathSum;
        }

        // Traverse the left and right children with the current path sum.
        return findRootToLeafPathNumbers(currentNode.left, pathSum)
                + findRootToLeafPathNumbers(currentNode.right, pathSum);
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
