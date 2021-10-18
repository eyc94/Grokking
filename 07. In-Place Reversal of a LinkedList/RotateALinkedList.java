/*
--- PROBLEM CHALLENGE 2 ---
Rotate A LinkedList [MEDIUM]

Given the head of a Singly LinkedList and a number 'k', rotate the LinkedList to
the right by 'k' nodes.

--- EXAMPLES ---

- EX 1 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, k = 3
Output:         4 -> 5 -> 6 -> 1 -> 2 -> 3 -> null
Explanation:    We move the first three nodes to the back of the LinkedList. We rotate the first
                node to the back 'k' times.

- EX 2 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> null, k = 8
Output:         3 -> 4 -> 5 -> 1 -> 2 -> null
Explanation:    We move the first node around to the back 8 times.
*/

public class RotateALinkedList {
    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode rotate(ListNode head, int rotations) {
        return head;
    }

    public static void main(String[] args) {
        // Sample Linked List.
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // head.next.next.next.next.next = new ListNode(6);
        // head.next.next.next.next.next.next = new ListNode(7);
        // head.next.next.next.next.next.next.next = new ListNode(8);
        // head.next.next.next.next.next.next.next.next = new ListNode(9);
        // head.next.next.next.next.next.next.next.next.next = new ListNode(10);

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
        ListNode result = rotate(head, 3);
        System.out.println("LinkedList after rotating three times: ");
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
