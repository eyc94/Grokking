/*
--- SIMILAR PROBLEMS 1 ---
Linked List Cycle Length

Given the head of a LinkedList with a cycle, find the length of the cycle.
*/

public class LinkedListCycleLength {

    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static int cycleLength(ListNode head) {
        // This is the length we are returning.
        // Initialized to 0 because we are starting.
        int length = 0;

        // The two pointers are the fast and slow pointers for finding cycle.
        ListNode fast = head;
        ListNode slow = head;

        // Find the cycle.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Once the pointers meet, we break.
            if (slow == fast) {
                break;
            }
        }

        // This pointer is the scanning pointer that goes around the cycle to find the
        // length.
        ListNode current = slow;
        // First increment the pointer and increment length.
        do {
            current = current.next;
            length++;
            // Then execute this more according to the while condition.
        } while (current != slow);

        // Return length.
        return length;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle length: " + cycleLength(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle length: " + cycleLength(head));
    }
}
