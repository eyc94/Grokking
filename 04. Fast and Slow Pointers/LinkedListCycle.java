/*
Linked List Cycle [EASY]

Given the head of a Singly Linked List, write a function to determine if the LinkedList
has a cycle in it or not.

--- EXAMPLES ---

- EX 1 -       
Cycle:          head -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> (points back to 3).

- EX 2 -
Non-Cycle:      head -> 2 -> 4 -> 6 -> 8 -> 10 -> null
*/

public class LinkedListCycle {

    /*
     * We use the fast and slow pointer method here. If there is a cycle, the fast
     * and slow pointer WILL MEET. Once they do, return true.
     * 
     * If there is no cycle, the fast pointer will reach null. The loop will end and
     * we return false.
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

    // This is the solution.
    public static boolean hasCycle(ListNode head) {
        // We have the fast and slow pointers.
        ListNode fast = head;
        ListNode slow = head;

        // We check if fast is not null before fast.next because if we land on even
        // length null, it'll account for that first.
        while (fast != null && fast.next != null) {
            // Move fast two spaces.
            fast = fast.next.next;
            // Move slow one space.
            slow = slow.next;
            // They eventually meet. If they do, return true.
            if (fast == slow) {
                return true;
            }
        }

        // If the fast pointer reaches null, this means that there is no cycle. Return
        // false.
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + hasCycle(head));
    }
}