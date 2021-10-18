/*
--- PROBLEM CHALLENGE 1 ---
Reverse Alternating K-Element SubList [MEDIUM]

Given the head of a LinkedList and a number 'k', reverse every alternating 'k'
sized sub-list starting from the head.

If, in the end, you are left with a sub-list with less than 'k' elements, reverse
it too.

--- EXAMPLES ---

- EX 1 -
Input:          1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> null, k = 2
Output:         2 -> 1 -> 3 -> 4 -> 6 -> 5 -> 7 -> 8 -> null
Explanation:    We see that we reverse every other sized-2 sub-lists.
*/

public class ReverseAlternatingKElementSubList {
    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverse(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        boolean reverse = true;

        while (true) {
            ListNode nodeBeforeP = prev;
            ListNode lastNodeOfSubList = curr;

            if (reverse) {
                for (int i = 0; curr != null && i < k; i++) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }

                if (nodeBeforeP != null) {
                    nodeBeforeP.next = prev;
                } else {
                    head = prev;
                }

                lastNodeOfSubList.next = curr;
                if (curr == null) {
                    break;
                }
                prev = lastNodeOfSubList;
                reverse = false;
            } else {
                for (int i = 0; curr != null && i < k; i++) {
                    curr = curr.next;
                    prev = prev.next;
                }
                reverse = true;
            }

            if (curr == null) {
                break;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        // Sample Linked List.
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);

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
        ListNode result = reverse(head, 2);
        System.out.println("LinkedList after reversing every other sized-2 sub-list: ");
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
