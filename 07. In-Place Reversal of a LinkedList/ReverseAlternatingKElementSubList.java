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

    /*
     * What I'm doing here is the same as the previous code. We run the while loop
     * until curr reaches the end of the LinkedList.
     * 
     * While we're looping, we use a boolean flag that indicates true or false to
     * reverse or not reverse, respectively.
     * 
     * When the flag is true, we reverse the next 'k' nodes. When the flag is false,
     * we skip the next 'k' nodes.
     * 
     * Once curr reaches the end of the LinkedList, we break out of the while loop.
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

    public static ListNode reverse(ListNode head, int k) {
        // If the sub-list size is just 1 or the list is empty, return the list.
        if (k <= 1 || head == null) {
            return head;
        }

        // We initialize the prev and curr pointers for the first 'k' nodes.
        ListNode prev = null;
        ListNode curr = head;
        // This is the flag that tells us whether we have to reverse or skip 'k' nodes.
        boolean reverse = true;

        // Iterate while true until the curr pointer reaches null.
        while (true) {
            // We update the node before the to-be last node of the to-be reversed sub-list.
            ListNode nodeBeforeP = prev;
            // We update the last node of the next sub-list to-be reversed.
            ListNode lastNodeOfSubList = curr;

            // If we have to reverse, we reverse.
            if (reverse) {
                // Reverse the next 'k' nodes.
                for (int i = 0; curr != null && i < k; i++) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }

                // If the node before 'p' is not null, connect this to the head of the reversed
                // sub-list, prev. Otherwise, point head to it.
                if (nodeBeforeP != null) {
                    nodeBeforeP.next = prev;
                } else {
                    head = prev;
                }

                // Point the last node of the reversed sub-list to the next to-be last node of
                // the next to-be reversed list.
                lastNodeOfSubList.next = curr;

                // After we finish linking, if curr reached the end, we break.
                if (curr == null) {
                    break;
                }

                // Otherwise, update prev to point to the last node of the list we just
                // reversed.
                prev = lastNodeOfSubList;
                // Set reverse flag to false, so we skip 'k' nodes on the next iteration.
                reverse = false;
            } else { // Otherwise, we skip.
                // Skip the next 'k' nodes.
                for (int i = 0; curr != null && i < k; i++) {
                    prev = curr;
                    curr = curr.next;
                }
                // Set reverse flag to true for the next 'k' nodes.
                reverse = true;
            }

            // We break again if curr is null.
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
