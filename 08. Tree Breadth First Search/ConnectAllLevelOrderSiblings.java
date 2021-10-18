import jdk.nashorn.api.tree.Tree;

/*
--- PROBLEM CHALLENGE 1 ---
Connect All Level Order Siblings [MEDIUM]

Given a binary tree, connect each node with its level order successor. The last node of each
level should point to the first node of the next level.

--- EXAMPLES ---

- EX 1 -
Input:      1
          /  \
         2    3
        / \  / \
       4   5 6  7

Output:         1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null

- EX 2 -
Input:     12
          /   \
         7     1
        /     / \
       9     10  5

Output:         12 -> 7 -> 1 -> 9 -> 10 -> 5 -> null
*/

public class ConnectAllLevelOrderSiblings {

    // This is the TreeNode class.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }
    }

    public static void connect(TreeNode root) {

    }

    public static void main(String[] args) {
        // Sample tree.
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        connect(root);

        // Print.
        TreeNode current = root;
        System.out.println("Traversal using 'next' pointer: ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}
