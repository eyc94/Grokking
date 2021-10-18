/*
--- SIMILAR QUESTIONS ---
Reversed Based On Size

Given a LinkedList with 'n' nodes, reverse it based on its size in the following way:

    1. If 'n' is even, reverse the list in a group of n/2 nodes.
    2. If 'n' is odd, keep the middle node as it is, reverse the first 'n/2' nodes and
       reverse the last 'n/2' nodes.

--- EXAMPLES ---

- EX 1 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> null
Output:         4 -> 3 -> 2 -> 1 -> 8 -> 7 -> 6 -> 5 -> null
Explanation:    The LinkedList is even, so reverse the first half (first 4) and link it
                to the reversed second half (last 4).

- EX 2 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> null
Output:         4 -> 3 -> 2 -> 1 -> 5 -> 9 -> 8 -> 7 -> 6 -> null
Explanation:    The LinkedList is odd, so reverse the first half (first 4), keep the
                middle the same, reverse the second half (last 4).
*/

public class ReverseBasedOnSize {
    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverseBasedOnSize(ListNode head) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        if (length % 2 == 0) {
            head = reverse(head, 1, length / 2);
            head = reverse(head, length / 2 + 1, length);
        } else {
            head = reverse(head, 1, length / 2);
            head = reverse(head, length / 2 + 2, length);
        }

        return head;
    }

    public static ListNode reverse(ListNode head, int p, int q) {
        // Reverse p to q.

        // Skip 'p - 1' nodes.
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; curr != null && i < p - 1; ++i) {
            prev = curr;
            curr = curr.next;
        }

        ListNode nodeBeforeP = prev;
        ListNode lastNodeOfSubList = curr;

        for (int i = 0; curr != null && i < q - p + 1; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        if (nodeBeforeP != null) {
            nodeBeforeP.next = prev;
        } else {
            head = prev;
        }

        lastNodeOfSubList.next = curr;
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
        // Even length. Comment either even or odd for different length testing.
        head.next.next.next.next.next.next.next = new ListNode(8);
        // Odd length.
        head.next.next.next.next.next.next.next.next = new ListNode(9);

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
        ListNode result = reverseBasedOnSize(head);
        System.out.println("LinkedList after reversing based on size: ");
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
