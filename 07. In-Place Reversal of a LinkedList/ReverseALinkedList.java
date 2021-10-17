/*
Reverse A LinkedList [EASY]

Given the head of a Singly LinkedList, reverse the LinkedList. Write a function to return the
new head of the reversed LinkedList.

--- EXAMPLES ---

- EX 1 -
Input:          2 -> 4 -> 6 -> 8 -> 10 -> null
Output:         10 -> 8 -> 6 -> 4 -> 2 -> null
*/

public class ReverseALinkedList {

    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverse(ListNode head) {
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        ListNode result = reverse(head);
        System.out.println("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}