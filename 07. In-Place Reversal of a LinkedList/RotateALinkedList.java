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

    /*
     * This problem is pretty cool. Basically, we just have to figure out where the
     * break in our Linked List is and push that towards the back. For example, if
     * our LinkedList is 1 -> 2 -> 3 -> 4 -> 5, and k is 3, we have to push back 1
     * -> 2 -> 3 to the back after 5.
     * 
     * So, we iterate through our LinkedList to find the tail and length of our
     * list. This is important because the number of rotations can exceed the length
     * of our LinkedList by a LOT. To combat this, we use the modulo operator to
     * take the rotations % length to find the relative position after many cycles.
     * 
     * We connect our tail to the head to create a circular linked list. We now find
     * the location where the last of our resulting linked list will be. We update
     * the new head to one node after that node. Then we point the last node's next
     * pointer to null to break the cycle.
     * 
     * Time Complexity: O(N) where N is the length of our Linked List.
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

    public static ListNode rotate(ListNode head, int rotations) {

        // We need the tail of the LinkedList.
        ListNode tail = head;
        // Find the length of our LinkedList while finding the tail of it.
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Point tail's next pointer to head to create a circular LinkedList.
        tail.next = head;
        // We take the modulo of the length of the LinkedList.
        // This is so that if the rotations is a huge number that is greater than the
        // length of our LinkedList, we can just take the modulo of length to find the
        // relative position.
        rotations %= length;
        // We want to skip over nodes that will be sent to the back half of the
        // LinkedList. It's length - rotations will give us the number of nodes that are
        // rotated.
        int skipLength = length - rotations;

        // We place a pointer at the location of the last node of our rotated list. This
        // is the node that will be the last overall node of our LinkedList after
        // rotations.
        ListNode lastNodeOfRotatedList = head;
        for (int i = 0; i < skipLength; i++) {
            lastNodeOfRotatedList = lastNodeOfRotatedList.next;
        }

        // We make the head of the new LinkedList one node after the last node of the
        // rotated nodes. This is because we have a circular LinkedList right now. So
        // the next node after the last node of rotated part is the new head.
        head = lastNodeOfRotatedList.next;
        // Point the next pointer of the last node of our rotated list to null. We are
        // breaking the cycle and creating an actual list.
        lastNodeOfRotatedList.next = null;

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
