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

    /*
     * This is an easy problem. We use the code from ReverseASubList.java and use it
     * as a helper function. The only thing different is that we have to find out
     * the length of our LinkedList. We find it in the beginning.
     * 
     * If the length is even, we reverse 1 to n/2. We then reverse n/2 + 1 to n. The
     * variable 'n' is the length of our LinkedList.
     * 
     * If the length is odd, we reverse 1 to n/2. We keep the middle the same. We
     * reverse n/2 + 2 to n. It is n/2 + 2 because we skip over the middle.
     * 
     * We basically call our reverse helper function here with the parameters for
     * 'p' and 'q'. The function is explained in my other code. Basically just
     * reverse from 'p' to 'q'. We return the head of the reversed list.
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

    public static ListNode reverseBasedOnSize(ListNode head) {
        // Count the nodes in our Linked List to perform reverse operations based on
        // size.
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // If the length is even, we reverse the first half (1, n/2).
        // We reverse the second half (n/2 + 1, n).
        if (length % 2 == 0) {
            head = reverse(head, 1, length / 2);
            head = reverse(head, length / 2 + 1, length);
        } else {
            // If the length is odd, we reverse the first half (1, n/2).
            // We keep the middle. We basically skip the middle.
            // We reverse the second hald (n/2 + 2, n).
            head = reverse(head, 1, length / 2);
            head = reverse(head, length / 2 + 2, length);
        }

        return head;
    }

    // This is the helper function. This is basically the ReverseASubList code.
    public static ListNode reverse(ListNode head, int p, int q) {
        // Reverse position p to q.

        // Skip 'p - 1' nodes.
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; curr != null && i < p - 1; ++i) {
            prev = curr;
            curr = curr.next;
        }

        // Keep track of the node at position 'p - 1' before 'p'.
        ListNode nodeBeforeP = prev;
        // Keep track of the last node of our sublist after reversing.
        // This is basically position 'p'.
        ListNode lastNodeOfSubList = curr;

        // We reverse 'p' to 'q'.
        for (int i = 0; curr != null && i < q - p + 1; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // If there was no node before 'p'. 'p - 1' is null.
        // So just point our head to the head of the reversed sub-list, prev.
        // If it's not null, point that node to the head of the reversed sub-list, prev.
        if (nodeBeforeP != null) {
            nodeBeforeP.next = prev;
        } else {
            head = prev;
        }

        // Point the last node of the reversed sub-list to 'q + 1'.
        lastNodeOfSubList.next = curr;
        // Return the head of our reversed list.
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
