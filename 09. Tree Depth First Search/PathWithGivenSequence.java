/*
Path With Given Sequence [MEDIUM]

Given a binary tree and a number sequence, find if the sequence is present
as a root-to-leaf path in the given tree.

--- EXAMPLES ---

- EX 1 -
Input:      1       sequence: [1, 9, 9]
           / \
          7   9
             / \
             2  9

Output:         true
Explanation:    The tree has a path 1 -> 9 -> 9.

- EX 2 -
Input:      1       sequence1: [1, 0, 7], sequence2: [1, 1, 6]
           / \
          0   1
         /   / \
        1    6  5

Output1:        false
Explanation1:   The tree does not have a path 1 -> 0 -> 7.

Output2:        true
Explanation2:   The tree has a path 1 -> 1 -> 6.
*/

public class PathWithGivenSequence {

    /*
     * This is like previous DFS approaches. We return true or false here. We
     * traverse with the sequence index incrementing by 1 each time. We compare our
     * current node and sequence value to see if they are equal.
     * 
     * If they are not equal at any point, we return false. If at any point we
     * return true, we cascade up the tree and return true.
     * 
     * We have to watch out for conditions where our sequence maybe the longer than
     * the tree's depth. This way if the sequence index is >= sequence.length we
     * return false because there's no way there's a valid sequence here.
     * 
     * Also, there is the possibility of reaching the leaf node without being a part
     * of the sequence, so we need to make sure our leaf node and sequence index is
     * the last of the sequence.
     * 
     * Time Complexity: O(N) where N is the number of nodes in our tree. We traverse
     * each node once.
     * 
     * Space Complexity: O(N) for the recursion stack.
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

    public static boolean findPath(TreeNode root, int[] sequence) {
        // If the tree is empty, we return the true or false depending on the sequence
        // length. If the sequence is not present, then a "no sequence" is obviously in
        // a "no tree". So, return true.
        if (root == null) {
            return sequence.length == 0;
        }
        // Call the recursive helper function with the initial sequence index of 0.
        // Pass the root and the sequence array.
        return findPathRecursive(root, 0, sequence);
    }

    public static boolean findPathRecursive(TreeNode currentNode, int sequenceIndex, int[] sequence) {
        // If the node is null, return false.
        // This can happen if we reach the children of a leaf. It can also happen if the
        // we reach the null child of the a node with one child.
        if (currentNode == null) {
            return false;
        }

        // In case the sequence is shorter than the tree depth (AKA we've gone too far).
        // Also if the current node's value is NOT equal to the respective sequence
        // value.
        if (sequenceIndex >= sequence.length || currentNode.val != sequence[sequenceIndex]) {
            return false;
        }

        // If the leaf node's value is not equal to the sequence value, the above if
        // statement will check it.
        // This checks for leaf node AND if the position in the sequence is the last as
        // well. This is because we can have a leaf node WAY past our sequence position.
        if (currentNode.left == null && currentNode.right == null && sequenceIndex == sequence.length - 1) {
            return true;
        }

        // Otherwise, we still have much more to explore. Traverse the left and right
        // subtrees with and update the index to one more.
        return findPathRecursive(currentNode.left, sequenceIndex + 1, sequence)
                || findPathRecursive(currentNode.right, sequenceIndex + 1, sequence);
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
        System.out.println("Tree has path sequence: " + findPath(root, new int[] { 1, 0, 7 }));
        System.out.println("Tree has path sequence: " + findPath(root, new int[] { 1, 1, 6 }));
        System.out.println("Tree has path sequence: " + findPath(root, new int[] { 1, 0, 1 }));
        System.out.println("Tree has path sequence: " + findPath(root, new int[] { 1, 1, 5 }));
    }
}
