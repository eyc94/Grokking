/*
Dutch National Flag Problem [MEDIUM]

Given an array containing 0s, 1s, and 2s, sort the array in-place. You should treat numbers of
the array as objects, hence, we can't count 0s, 1s, and 2s to recreate the array.

The flag of the Netherlands consists of three colors: red, white, and blue; and since our input
array also consists of three different numbers that is why it is called "Dutch National Flag
Problem".

--- EXAMPLES ---

- EX 1 -
Input:          [1, 0, 2, 1, 0]
Output:         [0, 0, 1, 1, 2]

- EX 2 -
Input:          [2, 2, 0, 1, 2, 0]
Output:         [0, 0, 1, 2, 2, 2]
*/

import java.util.*;

public class DutchNationalFlagProblem {

    /*
     * We use the two pointers approach. The two pointers represent the borders.
     * Below low are 0s. Above high are 2s. In between are 1s.
     * 
     * We scan through the array until high. If the value is 0, we swap with low. If
     * value is 1, we skip. If value is 2, we swap with high.
     * 
     * NOTICE how we only increment 'i' when we swap 0s to low. This is because
     * after we swap, our pointer 'i' may still have a non-one value.
     * 
     * Time Complexity: O(N) where N is the length of our array.
     * 
     * Space Complexity: O(1).
     */

    public static void sort(int[] arr) {
        // We use the two pointers approach.
        // Before low is 0s.
        // After high is 2s.
        int low = 0;
        int high = arr.length - 1;

        // We iterate to high because everything after high will be sorted.
        // NOTICE: 'i' is only incremented when we swap 0s to low. This is because we
        // may run into the case where, after swapping, we still have a 0 or 2 at
        // location 'i'.
        for (int i = 0; i <= high;) {
            // If number is 0, we swap with low. Increment low and i.
            if (arr[i] == 0) {
                int temp = arr[low];
                arr[low] = arr[i];
                arr[i] = temp;
                low++;
                i++;
            } else if (arr[i] == 1) { // If number is 1, we skip it.
                i++;
            } else {
                // If number is 2, swap with high. Decrement high.
                int temp = arr[high];
                arr[high] = arr[i];
                arr[i] = temp;
                high--;
            }
        }
    }

    public static void main(String[] args) {
        // Sample 1.
        int[] s1 = new int[] { 1, 0, 2, 1, 0 };
        System.out.println("Before: " + Arrays.toString(s1));
        sort(s1);
        System.out.println("After: " + Arrays.toString(s1));

        // Sample 2.
        int[] s2 = new int[] { 2, 2, 0, 1, 2, 0 };
        System.out.println("Before: " + Arrays.toString(s2));
        sort(s2);
        System.out.println("After: " + Arrays.toString(s2));
    }
}
