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

    /*
     * This is the classic reversing a LinkedList problem. We keep a reference to a
     * previous node because this is a Singly LinkedList.
     * 
     * We create a pointer that points to head. We iterate until this pointer
     * reaches null. Make a next pointer to the next node after curr. Point
     * curr.next to the prev pointer. Move prev to the curr node. Move curr to the
     * next node for the next iteration.
     * 
     * The prev pointer will, in the end, point to the head of the reversed
     * LinkedList.
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

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        // Sample Linked List.
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

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
        ListNode result = reverse(head);
        System.out.println("Nodes of the reversed LinkedList are: ");
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