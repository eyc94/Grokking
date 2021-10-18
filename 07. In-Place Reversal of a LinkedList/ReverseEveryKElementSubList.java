/*
Reverse Every K-Element SubList [MEDIUM]

Given the head of a LinkedList and a number 'k', reverse every 'k' sized sub-list starting
from the head.

If, in the end, you are left with a sub-list with less than 'k' elements, reverse it too.

--- EXAMPLES ---

- EX 1 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> null, k = 3
Output:         3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 8 -> 7 -> null
Explanation:    We reverse every size 3 sub-lists. The remaining nodes 7 and 8 are sized 2.
                This is okay because it's less than 'k'.
*/

public class ReverseEveryKElementSubList {

    /*
     * We basically keep reversing a sublist of size 'k' or less while we are still
     * in our loop. So, while true, we will reverse every 'k' sized sub-lists.
     * 
     * We initialize our prev and curr pointers to the beginning of our loop. Once
     * our while loop starts, we need a reference to the node before 'p', which is
     * prev. We need a reference to the last node of the to-be reversed sub-list.
     * 
     * We then reverse the next 'k' nodes. Note the condition that curr != null.
     * This handles the case where the sub-list we're reversing is less than 'k'.
     * The curr pointer will reach null, and we stop.
     * 
     * The prev pointer will point to the new head of our reversed sub-list. We now
     * have to point the node before 'p' to point to the prev node. If there is no
     * node before 'p' (p is the first node of the list), we just point head to it.
     * 
     * Then, we need to point our last node of the reversed sub-list to the next
     * to-be last node of the next to-be reversed sub-list. This is the curr
     * pointer.
     * 
     * At this point, if curr is null, we finished iterating and we can break.
     * Otherwise, we update prev to point to the last node of our reversed sub-list
     * that we JUST reversed. This way, on the next iteration, it's before the next
     * last node of the to-be reversed sub-list.
     * 
     * Time Complexity: O(N) where N is the length of the Linked List.
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

    public static ListNode reverse(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }

        // Initialize prev to be null.
        // Initialize curr to point to head.
        ListNode prev = null;
        ListNode curr = head;

        // Keeps iterating until we reach the end of the LinkedList.
        while (true) {

            // Keep reference to the node before 'p'.
            ListNode lastNodeBeforeP = prev;
            // Keep reference to the 'curr' node. This node will be the last node after we
            // reverse the sub-list of size 'k'.
            ListNode lastNodeAfterReverse = curr;

            // We reverse the nodes from position 'p' to position 'k'.
            // The curr != null condition here allows us to stop reversing when the sub-list
            // size is less than 'k'.
            for (int i = 0; curr != null && i < k; i++) {
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

            // The last node after we reverse needs to point to the next 'p' in the
            // iteration to complete this link between the last node of the reversed
            // sub-list to the next last node of the next sub-list of size 'k' or less.
            lastNodeAfterReverse.next = curr;

            // If the curr pointer is null (We reached the end of the LinkedList).
            // Break.
            if (curr == null) {
                break;
            }

            // Otherwise, update the prev pointer to point to the node before the next
            // sub-list to process.
            prev = lastNodeAfterReverse;
        }
        return head;
    }

    public static void main(String[] args) {
        // Sample Linked List.
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

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
        ListNode result = reverse(head, 3);
        System.out.println("LinkedList after reversing every 3 nodes: ");
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
