/*
--- PROBLEM CHALLENGE 1 ---

Given the head of a Singly LinkedList, write a method to check if the LinkedList is a
palindrome or not.

Your algorithm should use constant space and the input LinkedList should be in the original
form once the algorithm is finished. The algorithm should have O(N) time complexity

--- EXAMPLES ---

- EX 1 -
Input:          2 -> 4 -> 6 -> 4 -> 2 -> null
Output:         true

- EX 2 -
Input:          2 -> 4 -> 6 -> 4 -> 2 -> 2 -> null
Output:         false
*/

public class PalindromeLinkedList {

    // This is the ListNode class.
    public static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }

        // Find the middle.
        ListNode fast = head;
        ListNode slow = head;
        ListNode tailOne = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            tailOne = slow;
            slow = slow.next;
        }

        // Slow pointer now points to middle.

        // Reverse the second half.
        ListNode prev = null;
        ListNode curr = slow;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Find new headTwo and tailTwo and headOne and tailOne.

        ListNode headOne = head;
        tailOne.next = null;
        ListNode headTwo = prev;
        ListNode tailTwo = slow;

        // Compare each head values. (account for even and odd length).
        while (headOne != null && headTwo != null) {
            if (headOne.value != headTwo.value) {
                // Reconnect before returning false.
                curr = prev;
                prev = null;

                while (curr != null) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }

                tailOne.next = prev;
                return false;
            }

            headOne = headOne.next;
            headTwo = headTwo.next;
        }

        // Even length palindrome. Both will reach null at the same time.
        if (headOne == null && headTwo == null) {
            curr = prev;
            prev = null;

            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            tailOne.next = prev;
            return true;
        }

        // If the first half finishes scanning before the second half.
        // In odd length Linked Lists, this will always be the case when it's a valid
        // palindrome.
        if (headOne == null && headTwo == tailTwo) {
            curr = prev;
            prev = null;

            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            tailOne.next = prev;
            return true;
        }

        // If we get here, this means that our Linked List is NOT a palindrome.
        System.out.println("We reachd here!");
        curr = headTwo;
        prev = null;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        tailOne.next = prev;
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));
        head.next = new ListNode(4);
        System.out.println("Is palindrome: " + isPalindrome(head));
        head.next.next = new ListNode(6);
        System.out.println("Is palindrome: " + isPalindrome(head));
        head.next.next.next = new ListNode(6);
        System.out.println("Is palindrome: " + isPalindrome(head));
        head.next.next.next.next = new ListNode(4);
        System.out.println("Is palindrome: " + isPalindrome(head));
        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));
        head.next.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));
    }
}
