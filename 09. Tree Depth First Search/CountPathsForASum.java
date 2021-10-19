/*
Count Paths For A Sum [MEDIUM]

Given a binary tree and a number 'S', find all paths in the tree such that the sum
of all the node values of each path equals 'S'. Please note that the paths can start
or end at any node but all paths must follow direction from parent to child (top to bottom).

--- EXAMPLES ---

- EX 1 -
Input:      1       S = 12
           / \
          7   9
         / \ / \
        6  5 2  3

Output:         3
Explanation:    There are three paths with sum '12':
                7 -> 5
                1 -> 9 -> 2
                9 -> 3

- EX 2 -
Input:      12       S = 11
           /  \
          7    1
         /    / \
        4    10  5

Output:         2
Explanation:    There are two paths with sum '11':
                7 -> 4
                1 -> 10
*/

import java.util.*;

public class CountPathsForASum {

    /*
     * This is fairly straightforward. Basically, we hold a list of the current path
     * we are taking. After adding our node, we reset the path count and path sum.
     * 
     * We create ListIterator object to iterate our current path backwards. We add
     * the values backwards one at a time to the path sum. We check if our path sum
     * is equal to the sum we are looking for. If so, we increment path count.
     * 
     * Then, we traverse the left and right subtrees and add their returned value to
     * our current path count. This is because our subtrees may have more path
     * counts we can add while backtracking.
     * 
     * We then remove the last node of our current path to backtrack back up the
     * tree.
     * 
     * Time Complexity: O(N^2) where N is the number of nodes in our tree. For every
     * traversal of our nodes, we traverse the current path backwards to check if
     * they add to the sum. In the best case, we have a balanced binary tree. The
     * paths will be of length log N and we traverse log N for every node N. Best
     * case is O(N log N).
     * 
     * Space Complexity: O(N) because of the recursion call stack and space for the
     * current path.
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

    public static int countPaths(TreeNode root, int S) {
        // List that holds current path.
        List<Integer> currentPath = new LinkedList<>();
        return countPathsRecursive(root, S, currentPath);
    }

    public static int countPathsRecursive(TreeNode currentNode, int S, List<Integer> currentPath) {
        // If the node is null, return 0.
        // This happens when the node is the child of a leaf node.
        // Or a child of a node who has one child.
        if (currentNode == null) {
            return 0;
        }

        // We add the current node's value to the current path.
        // Our plan is to work BACKWARDS to find the matching sum.
        currentPath.add(currentNode.val);
        // This keeps track of the paths that add to sum.
        int pathCount = 0;
        // This keeps track of the path sum that is accumulating.
        // Note that this path sum resets to zero on each recursive call.
        // This is because we are working backwards and want to find a new path sum from
        // the bottom up.
        int pathSum = 0;

        // Create a ListIterator object that starts iterating from the end.
        // Notice that it starts from index out of range.
        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        // Iterate while we have a path to the left.
        while (pathIterator.hasPrevious()) {
            // Add the value to the current path sum.
            pathSum += pathIterator.previous();
            // If the path sum is equal to 'S', increment the pathCount.
            if (pathSum == S) {
                pathCount++;
            }
        }

        // Traverse the left and right subtrees and add their path count to the current
        // subtree's path count.
        pathCount += countPathsRecursive(currentNode.left, S, currentPath);
        pathCount += countPathsRecursive(currentNode.right, S, currentPath);

        // After traversing, we're back to the root of the subtree, remove the root from
        // the path to backtrack.
        currentPath.remove(currentPath.size() - 1);
        // Return path count.
        return pathCount;
    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);

        // Print.
        System.out.println("Tree has path: " + countPaths(root, 12));
    }
}
