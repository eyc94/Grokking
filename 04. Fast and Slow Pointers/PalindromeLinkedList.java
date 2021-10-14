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

    /*
     * This section is pretty straightforward. Follow these steps. We utilize a lot
     * of what we learned.
     * 
     * We check to see if our LinkedList is empty or has one node. This is a valid
     * palindrome, so we return true immediately.
     * 
     * First, find the middle of our LinkedList. This is done with slow and fast
     * pointers. The slow pointer will in a sense point to the middle of the
     * LinkedList. This slow pointer is said to be the tail of our reversed second
     * half later.
     * 
     * Next, we call the reverse helper function on the second half (slow pointer).
     * The function returns a ListNode (new head of the reversed list). The second
     * half head will now point to this returned list.
     * 
     * We keep a reference to this new head because we'll be traversing with the
     * returned head. We need the reference to reverse back to the original.
     * 
     * Now, we run a while loop while the two heads are not null. We compare each
     * value of our head pointers. If the values do not match, we break immediately.
     * This is a sign that we DO NOT have a palindrome. Otherwise, we keep
     * incrementing our head pointers until they both reach null (odd length list)
     * or one reaches null (even length list).
     * 
     * So, we now break from our while loop. There are only (3) reasons we break
     * from our while loop:
     * 
     * 1. h1 and h2 values are not equal, meaning we do not have a palindrome. This
     * causes us to break from the loop unnaturally and neither h1 nor h2 are null.
     * 
     * 2. h1 and h2 both reach null. This is the case of odd length lists of valid
     * palindromes. They will be both null.
     * 
     * 3. h2 is null and h1 is not. This is the case of even length lists of valid
     * palindromes. h2 is null.
     * 
     * So, it seems that when either or both h1 and h2 reach null, we have a valid
     * palindrome.
     * 
     * First, we will revert the second half back to normal. Call reverse helper
     * function on headTwo reference.
     * 
     * We then check if either h1 or h2 are null. If so, we return true. Else, we
     * return false.
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

    public static boolean isPalindrome(ListNode head) {
        // This is for an empty or list with one node.
        // Return true because they are valid palindromes.
        if (head == null || head.next == null) {
            return true;
        }

        // Find the middle of the Linked List.
        // Slow pointer will point to middle of Linked List.
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Reverse second half.
        // Have a reference to point to head 2 of right half.
        ListNode headTwo = reverse(slow);
        // Have a reference to head 2.
        // This is because we'll be scanning with headTwo.
        ListNode headTwoRef = headTwo;

        // Compare each head values.
        while (head != null && headTwo != null) {
            // If the value is ever not equal, break.
            if (head.value != headTwo.value) {
                break;
            }
            // Move the heads of both halves.
            head = head.next;
            headTwo = headTwo.next;
        }

        // Reverse the second half head back to original.
        reverse(headTwoRef);
        // There are three reasons our while loop breaks.
        // 1. h1 and h2 are not equal values, so the loop breaks without h1 or h2
        // reaching null.
        //
        // 2. h2 reaches null before h1 because of even length palindrome.
        //
        // 3. h1 and h2 both reach null in odd length palindrome.
        //
        // So, we see that if our head pointers (either pointer) reaches null, we have a
        // valid palindrome. The only time we do not have a valid palindrome is when the
        // head pointers break without reaching null.
        if (head == null || headTwo == null) {
            // At least one head reached null, so return true.
            return true;
        }
        // This means our while loop broke without reaching null. So not a valid
        // palindrome.
        return false;
    }

    // This is the LinkedList reverse helper function.
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
