/*
--- PROBLEM CHALLENGE 1 ---
Tree Diameter [MEDIUM]

Given a binary tree, find the length of its diameter. The diameter of a tree is the number of
nodes on the longest path between any two leaf nodes. The diameter of a tree may or may not
pass through the root.

Note: You can always assume that there are at least two leaf nodes in the given tree.

--- EXAMPLES ---

- EX 1 -
Input:      1
           / \
          2   3
         /   / \
        4    5  6

Output:         5
Explanation:    The diameter of the tree is [4, 2, 1, 3, 6].

- EX 2 -
Input:       1
           /  \
          2    3
             /   \
             5    6
            / \   /
           7   8 9
              / /
            10 11

Output:        7
Explanation:   The diameter of the tree is [10, 8, 5, 3, 6, 9, 11].
*/

public class TreeDiameter {

    /*
     * This is a similar approach to before. We are again recursing all the way left
     * until we reach a null node. Then, we are repeatedly sending the answer all
     * the way up.
     * 
     * At every step, we need to find the height of both children of the current
     * node. This is why we make two recursive calls. Once it bottoms out to null,
     * we know that the leaf node height is just 1 (left null + right null + 1). The
     * null nodes return 0.
     * 
     * The height of the current node (again like above) is just the height of the
     * left subtree + right subtree + 1.
     * 
     * The tree diameter at the current node will be equal to the height of the left
     * child plus the height of the right child plus 1 (for the current node).
     * 
     * We use a class variable to update the max tree diameter.
     * 
     * When we move back up the tree and return a value, we return the height of the
     * subtrees whose height is the maximum + 1 (for the current node). This is
     * because we want to return the HEIGHT of the node up to the next node. Because
     * remember, to calculate the tree diameter, we need the left and right children
     * height. To return the height, we need a max of either the left or right
     * subtrees.
     * 
     * Time Complexity: O(N) where N is the number of nodes in our tree.
     * 
     * Space Complexity: O(N) because of the recursion call stack.
     */

    // We use a class variable to update tree diameter.
    public static int treeDiameter = 0;

    // This is the TreeNode class.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int findDiameter(TreeNode root) {
        calculateHeight(root);
        return treeDiameter;
    }

    // This is the recursive helper function.
    public static int calculateHeight(TreeNode currentNode) {
        // When we reach our null node (base case), return height of 0.
        if (currentNode == null) {
            return 0;
        }

        // Get the left subtree height.
        int leftTreeHeight = calculateHeight(currentNode.left);
        // Get the right subtree height.
        int rightTreeHeight = calculateHeight(currentNode.right);

        // Calculate the current node's diameter.
        // It's just the left tree height + right tree height + 1 (for the current
        // node).
        int diameter = leftTreeHeight + rightTreeHeight + 1;
        // Calculate the tree diameter. This is the max diameter so far.
        treeDiameter = Math.max(diameter, treeDiameter);

        // Return to the parent node the max tree heigh (left or right) + 1.
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + findDiameter(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: " + findDiameter(root));
    }
}
