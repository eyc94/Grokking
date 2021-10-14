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
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));

        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));
    }
}
