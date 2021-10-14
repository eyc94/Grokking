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

    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode findMiddle(ListNode head) {

        return head;
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
