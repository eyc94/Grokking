/*
--- PROBLEM CHALLENGE 2 ---
Rearrange A LinkedList [MEDIUM]

Given the head of a Singly LinkedList, write a method to modify the LinkedList such that the nodes
from the second half of the LinkedList are inserted alternately to the nodes from the first half in
reverse order. So if the LinkedList has nodes 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, your method should
return 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.

Your algorithm should not use any extra space and the input LinkedList should be modified in-place.

--- EXAMPLES ---

- EX 1 -
Input:          2 -> 4 -> 6 -> 8 -> 10 -> 12 -> null
Output:         2 -> 12 -> 4 -> 10 -> 6 -> 8 -> null

- EX 2 -
Input:          2 -> 4 -> 6 -> 8 -> 10 -> null
Output:         2 -> 10 -> 4 -> 8 -> 6 -> null
*/

public class RearrangeALinkedList {

    /*
     * We use our fast and slow pointer method to find the middle of our LinkedList.
     * Once we do, we reverse the second half. We keep a reference to the heads of
     * both halves.
     * 
     * We run a while loop until one of them reaches null.
     * 
     * We rearrange using the 'temp' pointer. See the code below. Basically, if we
     * have an even length LinkedList, our headTwo pointer will reach null and our
     * headOne.next pointer will be pointing to itself. The headOne pointer will not
     * be null, so we make headOne.next point to null.
     * 
     * In odd length arrays, our headOne pointer will be null while our headTwo
     * pointer will not be. However, we do not need to connect anything else because
     * it'll be connected as a result of the loop.
     * 
     * Time Complexity: O(N) where N is the LinkedList length.
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

    public static void reorder(ListNode head) {
        // If the LinkedList is empty or has one node, the list itself is already
        // reordered.
        if (head == null || head.next == null) {
            return;
        }

        // Find the middle of the LinkedList.
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Reverse the second half of our LinkedList.
        ListNode headTwo = reverse(slow);
        // Assign new head pointer for first half of LinkedList.
        ListNode headOne = head;

        // Iterate our head pointers until one of them reaches null.
        while (headOne != null && headTwo != null) {
            // Rearrange as necessary using temp pointer.
            ListNode temp = headOne.next;
            headOne.next = headTwo;
            headOne = temp;

            temp = headTwo.next;
            headTwo.next = headOne;
            headTwo = temp;
        }

        // This handles an even length LinkedList. When reordering, our headTwo will
        // reach null before the headOne. The headOne.next will point to itself. We need
        // this to point to null.
        if (headOne != null) {
            headOne.next = null;
        }
    }

    // This is the ListNode reverse helper function.
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        reorder(head);
        while (head != null) {
            if (head.next == null) {
                System.out.println(head.value);
            } else {
                System.out.print(head.value + " -> ");
            }
            head = head.next;
        }
    }
}
