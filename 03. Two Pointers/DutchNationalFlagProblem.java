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

    public static void sort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        for (int i = 0; i <= high;) {
            if (arr[i] == 0) {
                int temp = arr[low];
                arr[low] = arr[i];
                arr[i] = temp;
                low++;
                i++;
            } else if (arr[i] == 1) {
                i++;
            } else {
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
