/*
Start Of Linked List Cycle [MEDIUM]

Given the head of a Singly LinkedList that contains a cycle, write a function to find the
starting node of the cycle.
*/

public class StartOfLinkedListCycle {

    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode findCycleStart(ListNode head) {

        // Find the cycle as usual.
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // Break when cycle detected.
            if (slow == fast) {
                break;
            }
        }

        // Find the length of the cycle as usual.
        int lengthOfCycle = 0;
        ListNode current = slow;
        do {
            current = current.next;
            lengthOfCycle++;
        } while (current != slow);

        // Bring the fast and slow pointers back to the beginning.
        fast = head;
        slow = head;
        // Move the fast pointer up to length of what we found the cycle length to be.
        for (int i = 0; i < lengthOfCycle - 1; i++) {
            fast = fast.next;
        }

        // Move the slow and fast pointers together until the next pointer of fast
        // points to slow.
        while (fast.next != slow) {
            slow = slow.next;
            fast = fast.next;
        }

        // The slow pointer is now on the start of the cycle.
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).value);
    }
}
