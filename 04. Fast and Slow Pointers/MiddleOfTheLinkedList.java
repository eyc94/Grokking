/*
Middle Of The LinkedList [EASY]

Given the head of a Singly LinkedList, write a method to return the middle
node of the LinkedList.

If the total number of nodes in the LinkedList is even, return the second
middle node.

--- EXAMPLES ---

- EX 1 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> null
Output:         3

- EX 2 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
Output:         4

- EX 3 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
Output:         4
*/

public class MiddleOfTheLinkedList {

    /*
     * This is another slow and fast pointer strategy. We iterate slow one node at a
     * time. We iterate fast two nodes at a time. The fast pointer will reach null
     * or the node before null. The slow pointer will point to the middle.
     * 
     * Time Complexity: O(N) where N is the length of the LinkedList.
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

    public static ListNode findMiddle(ListNode head) {
        // Make the slow and fast pointers.
        ListNode slow = head;
        ListNode fast = head;

        // Iterate until fast reaches null or node before null.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Slow pointer will be pointing to the middle node.
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Middle node: " + findMiddle(head).value);

        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Middle node: " + findMiddle(head).value);

        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println("Middle node: " + findMiddle(head).value);
    }
}
