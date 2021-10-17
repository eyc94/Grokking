/*
--- SIMILAR PROBLEMS 1 ---
Reverse First K Elements

Reverse the first 'k' elements of a given LinkedList.

--- EXAMPLES ---

- EX 1 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> null, k = 3
Output:         3 -> 2 -> 1 -> 4 -> 5 -> null
Explanation:    The first k (3) nodes are reversed. Notice that 'k' is 1-indexed.
*/

public class ReverseFirstKElements {

    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverseFirstK(ListNode head, int k) {
        // We need the reference to the node that will be the last node after reversal.
        ListNode lastNodeAfterReverse = head;

        // Reverse the list from position 0 to position 'k'.
        ListNode curr = head;
        ListNode prev = null;
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Because the prev will be the new head of the list, have the head point
        // directly to prev.
        head = prev;
        // Point the last node of the reversed sub-list to the position 'k + 1'.
        // This is curr pointer.
        lastNodeAfterReverse.next = curr;

        // Return head.
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
        ListNode result = reverseFirstK(head, 3);
        System.out.println("LinkedList after reversing first 3 nodes (index 0 - 2): ");
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
