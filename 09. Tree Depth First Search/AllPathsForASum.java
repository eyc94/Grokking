/*
All Paths For A Sum [MEDIUM]

Given a binary tree and a number 'S', find all paths from root-to-leaf such that the sum
of all the node values of each path equals 'S'.

--- EXAMPLES ---

- EX 1 -
Input:      1       S = 12
           / \
          7   9
         / \ / \
        4  5 2  7

Output:         2
Explanation:    There are two paths with sum '12'. 1 -> 7 -> 4 and 1 -> 9 -> 2.

- EX 2 -
Input:      12      S = 23
           /  \
          7    1
         /    / \
        4    10  5

Output1:        2
Explanation:    Here are the two paths with sums '23'. 12 -> 7 -> 4 and 12 -> 1 -> 10.
*/

import java.util.*;

public class AllPathsForASum {

    /*
     * We use DFS recursive calls with a helper function to achieve this. We have an
     * allPaths list that holds all paths that add to the original sum we are
     * looking for. We also keep track of the current path because we need to see if
     * our current path adds to the sum so we can add to all paths.
     * 
     * These two are declared OUTSIDE the recursive function call. This is because
     * this is always updating. We just pass the reference to these lists, so we can
     * keep updating them.
     * 
     * We first call the dfs on the root node with the lists and required sum.
     * 
     * In the function, we check first to see if the node is null. The node is null
     * when we encounter a leaf node's children or a node whose has one child. These
     * two situations are the only times node will be null. In these cases, we just
     * want to return immediately because there's nothing to do.
     * 
     * We add the current value to the current path we are on.
     * 
     * We then check if the node is a leaf node. This is when the children are both
     * null. If the node is a leaf and the sum is also equal to the sum we're
     * looking for, we add the current path to all paths.
     * 
     * If the node is a leaf and the sum is not equal to the sum, we traverse to the
     * left and right children. If the node is not a leaf and the sum is NOT equal
     * to the target, we traverse to the left and right.
     * 
     * The traversal to the left and right is the same as before. We pass the left
     * and right child depending on which we go down first. We also pass the two
     * lists. We also pass the next sum to look for. This is the sum - current
     * node's value.
     * 
     * After traversing or adding current path to all paths, we remove the current
     * node from the current path. This is to backtrack when we finish the nodes.
     * 
     * At the end, we're going to return to the root with no current path and all
     * paths filled out.
     * 
     * Time Complexity: O(N^2) where N is the number of nodes in the tree. This is
     * because we traverse each node with takes O(N). For each leaf node, we add the
     * current path to all paths. This takes O(N). So, O(N * N).
     * 
     * Space Complexity: O(N) because of the recursion stack. What is the space used
     * for allPaths? Max number of elements in allPaths is O(N). Each path can have
     * many nodes in them. For a balanced binary tree, each leaf node will be at max
     * depth. The depth (or height) of a balanced binary tree is O(log N). Each path
     * can have log N nodes in it. Total size of allPaths list is O(N log N). If we
     * ignore allPaths space, space is O(N). If not, it's O(N log N).
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

    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        // This holds all paths that add up to the sum.
        List<List<Integer>> allPaths = new ArrayList<>();
        // This holds the current path of our traversal. This constantly changes.
        List<Integer> currentPath = new ArrayList<>();
        // Call the recursive function with the root and original sum.
        findPathsRecursive(root, sum, currentPath, allPaths);
        return allPaths;
    }

    public static void findPathsRecursive(TreeNode currentNode, int sum, List<Integer> currentPath,
            List<List<Integer>> allPaths) {
        // If the current node is null, we just return.
        // We reach here when we're done processing a leaf node and the value of it is
        // not equal to the sum. We are forced to traverse the null children.
        if (currentNode == null) {
            return;
        }

        // If the node is not null, add the value to the current path.
        currentPath.add(currentNode.val);

        // It leaf and value is equal to the sum, add current path to all paths.
        if (currentNode.val == sum && currentNode.left == null && currentNode.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else { // If not leaf or not equal to sum, traverse the left and right children.
            findPathsRecursive(currentNode.left, sum - currentNode.val, currentPath, allPaths);
            findPathsRecursive(currentNode.right, sum - currentNode.val, currentPath, allPaths);
        }

        // Remove the current node from the current path.
        // This is to backtrack up the recursive calls.
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        // Print.
        int sum = 23;
        List<List<Integer>> result = findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }
}
