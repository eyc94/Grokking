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

    public static int remove(int[] arr) {
        return -1;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        int[] s2 = new int[] { 2, 2, 2, 11 };

        // Calculate results.
        int r1 = remove(s1);
        int r2 = remove(s2);

        // Print results.
        System.out
                .println("After removing duplicates from " + Arrays.toString(s1) + " the new array has length: " + r1);
        System.out
                .println("After removing duplicates from " + Arrays.toString(s2) + " the new array has length: " + r2);
    }
}
