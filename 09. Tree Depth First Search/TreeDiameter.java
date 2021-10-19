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
        return -1;
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
