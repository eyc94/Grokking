/*
Pair With Target Sum [EASY]

Given an array of sorted numbers and a target sum, find a pair in the array
whose sum is equal to the given target.

Write a function to return the indices of the two numbers (i.e. the pair)
such that they add up to the given target.

--- EXAMPLES ---

- EX 1 -
Input:          [1, 2, 3, 4, 6], target = 6
Output:         [1, 3]
Explanation:    The numbers at index 1 and 3 add up to 6: 2 + 4 = 6.

- EX 2 -
Input:          [2, 5, 9, 11], target = 11
Output:         [0, 2]
Explanation:    The numbers at index 0 and 2 add up to 11: 2 + 9 = 11.
*/

import java.util.*;

public class PairWithTargetSum {

    public static int[] search(int[] arr, int targetSum) {
        // This is the pointer that starts at the beginning.
        int low = 0;
        // This is the pointer that starts at the end.
        int high = arr.length - 1;

        // We start shrinking the two pointers until they meet.
        while (low < high) {
            // Grab the sum of the low and high pointers.
            int sum = arr[low] + arr[high];
            // If the sum is equal to the target sum, return the low and high values.
            if (sum == targetSum) {
                return new int[] { low, high };
            } else if (sum < targetSum) { // If sum is less than target, increase.
                low++;
            } else { // If sum is greater than target, decrease.
                high--;
            }
        }
        // Return -1 to both indices if there is no such pair that adds to target.
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 1, 2, 3, 4, 6 };
        int[] s2 = new int[] { 2, 5, 9, 11 };

        // Calculate results.
        int[] r1 = search(s1, 6);
        int[] r2 = search(s2, 11);

        // Print results.
        System.out
                .println("Array " + Arrays.toString(s1) + " has two indices that add up to 6: " + Arrays.toString(r1));
        System.out
                .println("Array " + Arrays.toString(s2) + " has two indices that add up to 11: " + Arrays.toString(r2));
    }
}