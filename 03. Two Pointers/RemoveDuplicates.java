/*
Remove Duplicates [EASY]

Given an array of sorted numbers, remove all duplicates from it. You should not use any
extra space; after removing the duplicates in-place return the new length of the array.

--- EXAMPLES ---

- EX 1 -
Input:          [2, 3, 3, 3, 6, 9, 9]
Output:         4
Explanation:    The first four elements after removing the duplicates will be [2, 3, 6, 9].

- EX 2 -
Input:          [2, 2, 2, 11]
Output:         2
Explanation:    The first two elements after removing the duplicates will be [2, 11].
*/

import java.util.*;

public class RemoveDuplicates {

    /*
     * This is the two pointer approach. We start both pointers from the start of
     * our array. One pointer 'i' is the scanner. The other pointer
     * 'nextNonDuplicate' is the border between those that are all unique (left) and
     * those that we have not processed yet or are duplicates (right).
     * 
     * What we do is we scan from left to right. Compare the current 'i' value to
     * the value before 'nextNonDuplicate'. If the values are the same, this means
     * we have already placed this value in our left side. So, move on. If they are
     * NOT the same, we swap the current 'i' value with the border
     * (nextNonDuplicate) value.
     * 
     * This process repeats and the border will land on the index after the final
     * number of the new array. Because it is 0-indexed, we can just return the
     * border value (index) to the user.
     * 
     * Time Complexity: O(N) where N is the length of our array.
     * 
     * Space Complexity: O(1).
     */

    public static int remove(int[] arr) {
        // This is the pointer that tracks the next position in the array that we can
        // swap. Everything before this pointer has no duplicates.
        // We initialize it to 1 because the first number is itself not a duplicate of
        // anything before it.
        int nextNonDuplicate = 1;
        // We initialize our iterator to 1. This is the same location as the next non
        // duplicate. We iterate through to the end of the array.
        for (int i = 1; i < arr.length; i++) {
            // Compare the iterator value to the value before the next non duplicate.
            // If the values are equal, we DO NOT need to swap. This is because the value
            // placed in the unique list to the left of nextNonDuplicate already has the
            // number we're looking at. So we DO NOT need to swap.

            // HOWEVER. If the current iterator value IS NOT equal to the value before the
            // border, we can move the current iterator value to the border value because we
            // have not seen the value yet. Then, increment the border (nextNonDuplicate).
            if (arr[i] != arr[nextNonDuplicate - 1]) {
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }
        return nextNonDuplicate;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        int[] s2 = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        int[] s3 = new int[] { 2, 2, 2, 11 };
        int[] s4 = new int[] { 2, 2, 2, 11 };

        // Calculate results.
        int r1 = remove(s1);
        int r2 = remove(s3);

        // Print results.
        System.out
                .println("After removing duplicates from " + Arrays.toString(s2) + " the new array has length: " + r1);
        System.out
                .println("After removing duplicates from " + Arrays.toString(s4) + " the new array has length: " + r2);
    }
}
