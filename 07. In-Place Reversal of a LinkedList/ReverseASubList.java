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
    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverse(ListNode head, int p, int q) {
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
