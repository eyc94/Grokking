/*
Reverse A Sub-List [MEDIUM]

Given the head of a LinkedList and two positions 'p' and 'q', reverse the LinkedList
from position 'p' to 'q'.

--- EXAMPLES ---

- EX 1 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> null, p = 2, q = 4
Output:         1 -> 4 -> 3 -> 2 -> 5 -> null
Explanation:    We reverse the nodes from positions 2 to 4 (index 1 to index 3).
*/

public class ReverseASubList {

    /*
     * This is the Reversed LinkedList problem but we need to tweak it a bit.
     * Basically, we just reverse the sublist given by the inputs 'p' and 'q'.
     * Notice how the values are not 0-index based. This means we have to offset the
     * end of loops to the left by 1.
     * 
     * We need a few things to solve this problem. We need a reference to node 'p',
     * node 'p - 1' (to connect to the head of the new reversed sub-list 'prev'),
     * node 'q + 1' to connect the new tail of our reversed sub-list to.
     * 
     * We first skip over the first 'p - 1' nodes. We keep track of the previous and
     * current pointers. At the end of this iteration, curr will point to the start
     * of the sub-list (node 'p') and prev will point to the node before 'p'
     * (position 'p - 1'). NOTE: If 'p' is 1, this denotes the first node, and prev
     * will remain null.
     * 
     * After this, we found 'p' and 'p - 1'. So, we should keep a reference to these
     * nodes because we'll be using prev and curr to reverse the sub-list. So we
     * create a reference to both of these to connect the reversed sub-list to the
     * original list.
     * 
     * Reverse the sub-list like normal from position 'p' to position 'q'. At the
     * end, prev will point to the new head of our reversed sub-list. The curr
     * pointer will point to 'q + 1'.
     * 
     * Now, we check to see if the reference to 'p - 1' is null or not. If it is
     * null, this means 'p' has a value of 1, and there is no previous node before
     * 'p'. This means that prev is the new head. So, point head to prev.
     * 
     * If 'p - 1' is NOT null, we know there is a node before 'p'. So, point the
     * next pointer of 'p - 1' to point to prev.
     * 
     * Lastly, we need to point our reversed sub-list tail to 'q + 1'. We have the
     * reference to that tail a position 'p'. Point the next pointer of 'p' to 'q +
     * 1' which is referenced by curr.
     * 
     * Time Complexity: O(N) where N is the length of our LinkedList.
     * 
     * Space Complexity: O(1).
     */

    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverse(ListNode head, int p, int q) {
        if (p == q) {
            return head;
        }

        // We keep the first 'p - 1' nodes.
        // Curr will be at position 'p'.
        // Prev will be at position 'p - 1'.
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; curr != null && i < p - 1; ++i) {
            prev = curr;
            curr = curr.next;
        }

        // Keep reference to the last node before 'p' (at position 'p - 1').
        // Keep reference to the last node after we reverse the sublist (position 'p').
        // We need these references to connect the reversed sublist to the original
        // list.
        ListNode lastNodeBeforeP = prev;
        ListNode lastNodeAfterReverse = curr;

        // We reverse the nodes from position 'p' to position 'q'.
        for (int i = 0; curr != null && i <= q - p; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // First check if position 'p - 1' is null.
        // In other words, if we had to reverse the first node of our original list,
        // there is no previous node before the first node.
        // Point head to prev.
        // If not, connect this node to the prev.
        if (lastNodeBeforeP != null) {
            lastNodeBeforeP.next = prev;
        } else {
            head = prev;
        }

        // Lastly, we need to connect the last node after reversing to node 'q + 1'.
        // The curr pointer lands on 'q + 1'.
        lastNodeAfterReverse.next = curr;
        return head;
    }

    public static void main(String[] args) {
        // Sample Linked List.
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Print original LinkedList.
        ListNode scanner = head;
        System.out.println("Nodes of the original LinkedList are: ");
        while (scanner != null) {
            if (scanner.next != null) {
                System.out.print(scanner.value + " -> ");
            } else {
                System.out.print(scanner.value);
            }
            scanner = scanner.next;
        }
        System.out.println();

        // Reverse and print reversed LinkedList.
        ListNode result = reverse(head, 2, 4);
        System.out.println("LinkedList after reversing nodes 2 and 4 (index 1 and 3): ");
        while (result != null) {
            if (result.next != null) {
                System.out.print(result.value + " -> ");
            } else {
                System.out.print(result.value);
            }
            result = result.next;
        }
        System.out.println();
    }
}
