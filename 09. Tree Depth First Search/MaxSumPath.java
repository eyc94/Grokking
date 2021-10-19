/*
--- SIMILAR PROBLEMS 2 ---
Max Sum Path

Given a binary tree, find the root-to-leaf path with the maximum sum.

--- EXAMPLES ---

- EX 1 -
Input:      1
           / \
          7   9
         / \ / \
        4  5 2  7

Output:         17
Explanation:    1 -> 9 -> 7.

- EX 2 -
Input:      12
           /  \
          7    1
         /    / \
        6    10  5

Output:        25
Explanation:    12 -> 7 -> 6
*/

import java.util.*;

public class MaxSumPath {

    /*
     * This is pretty much like the other codes. The only difference is that we have
     * to look for the path that is the max sum. This means we need to keep track of
     * the sum and the largest we have seen so far.
     * 
     * Because we are recursing, we cannot use a local copy of largest sum. We
     * create a class called Maximum that holds the property of the largest sum so
     * far. We then create the object in our code and pass the object into the
     * recursive function. We update this value and use it using the max.largestSum
     * dot property notation.
     * 
     * We traverse our tree and add node's values to the sum. We add the node to the
     * current path. When we encounter a leaf and the current path sum is larger
     * than the largest sum so far, we copy over the current path contents to the
     * maxPath. We also update the largestSum property of the object.
     * 
     * If not leaf or not max sum, we traverse left and right again. If we encounter
     * a null node, remember to return.
     * 
     * After traversing, we remove the last node in our path, which is the leaf
     * node.
     * 
     * Time Complexity: O(N^2) because traverse each node once. For each node, we
     * may have to keep copying the contents over which takes O(N) time.
     * 
     * Space Complexity: O(N) if we ignore the space for maxPath length and just
     * look at recursion call stack.
     */

    // This is the Maximum class holding the largest sum of anything.
    public static class Maximum {
        int largestSum = 0;
    }

    // This is the TreeNode class.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<Integer> maxSumPath(TreeNode root) {
        // Max path list.
        List<Integer> maxPath = new ArrayList<>();
        // Current path list.
        List<Integer> currentPath = new ArrayList<>();
        // Sum accumulator that starts at 0.
        int sum = 0;
        // Create an object of Maximum to keep track of largest sum overall.
        Maximum max = new Maximum();
        // Call dfs helper recursive function.
        dfs(root, currentPath, maxPath, sum, max);
        return maxPath;
    }

    // DFS recursive helper.
    public static void dfs(TreeNode currentNode, List<Integer> currentPath, List<Integer> maxPath, int sum,
            Maximum max) {
        // This is the base case. If node is null, return.
        if (currentNode == null) {
            return;
        }

        // Add the node's value to the accumulating sum.
        sum += currentNode.val;
        // Add the current node's value to the path.
        currentPath.add(currentNode.val);

        // If leaf AND sum is greater than the largest sum.
        if (currentNode.left == null && currentNode.right == null && sum > max.largestSum) {
            // Clear the maxPath list.
            System.out.println(max.largestSum);
            maxPath.clear();
            // Copy over the contents of current path to maxPath.
            for (int val : currentPath) {
                maxPath.add(val);
            }
            // Update the largest sum to the new largest sum.
            max.largestSum = sum;
        } else {
            // Otherwise, we traverse left and right.
            dfs(currentNode.left, currentPath, maxPath, sum, max);
            dfs(currentNode.right, currentPath, maxPath, sum, max);
        }

        // After traversing to leaf, remove the leaf node from current path to
        // backtrack.
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(5);

        // Print.
        List<Integer> result = maxSumPath(root);
        System.out.println("Max path: " + result);
    }
}
