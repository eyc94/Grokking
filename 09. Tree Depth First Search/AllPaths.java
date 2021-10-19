/*
--- SIMILAR PROBLEMS 1 ---
All Paths

Given a binary tree, return all root-to-leaf paths

--- EXAMPLES ---

- EX 1 -
Input:      1
           / \
          7   9
         / \ / \
        4  5 2  7

Output:         4
Explanation:    1 -> 7 -> 4.
                1 -> 7 -> 5.
                1 -> 9 -> 2.
                1 -> 9 -> 7.

- EX 2 -
Input:      12
           /  \
          7    1
         /    / \
        4    10  5

Output1:        3
Explanation:    12 -> 7 -> 4
                12 -> 1 -> 10
                12 -> 1 -> 5
*/

import java.util.*;

public class AllPaths {

    /*
     * This is the same as the AllPathsForASum.java. The only thing is is that we
     * are not looking for a sum. As soon as we encounter a leaf node, we
     * immediately add the current path to the all paths list regardless of the
     * node's values.
     * 
     * This is basically it. To go more in depth, check the code file mentioned
     * above. It's practically the same thing.
     * 
     * Time Complexity: O(N^2) where 'N' is the number of nodes in our tree. We
     * traverse our nodes once. For each node, we may have to add all the current
     * path nodes to the list which takes O(N) time.
     * 
     * Space Complexity: O(N) where N is the recursion call stack. This is if we
     * ignore the space of all Paths.
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

    public static List<List<Integer>> allPaths(TreeNode root) {
        // Keep track of all paths in our tree.
        List<List<Integer>> allPaths = new ArrayList<>();
        // Keep track of our current path so far.
        List<Integer> currentPath = new ArrayList<>();
        // Make a recursive call to traverse all nodes.
        dfs(root, currentPath, allPaths);
        // Return all paths after traversing tree.
        return allPaths;
    }

    public static void dfs(TreeNode currentNode, List<Integer> currentPath, List<List<Integer>> allPaths) {
        // If the node is null, return immediately.
        // This is the base case.
        if (currentNode == null) {
            return;
        }

        // Otherwise, add the value to the current path.
        currentPath.add(currentNode.val);

        // If the node is a leaf node, add the path to all paths.
        if (currentNode.left == null && currentNode.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else { // Else, we traverse the left and right subtrees.
            dfs(currentNode.left, currentPath, allPaths);
            dfs(currentNode.right, currentPath, allPaths);
        }

        // After traversing and reaching the end of the path, remove the last node to
        // backtrack. This is for backtracking purposes for recursive calls.
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        // Print.
        List<List<Integer>> result = allPaths(root);
        System.out.println("All Paths: " + result);
    }
}
