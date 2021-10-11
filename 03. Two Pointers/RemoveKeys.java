/*
--- SIMILAR QUESTIONS 1 ---

Given an unsorted array of numberse and a target 'key', remove all instances of 'key' in-place
and return the new length of the array.

--- EXAMPLES ---

- EX 1 -
Input:          [3, 2, 3, 6, 3, 10, 9, 3], Key = 3
Output:         4
Explanation:    The first four elements after removing every 'Key' will be [2, 6, 1, 0, 9].

- EX 2 -
Input:          [2, 11, 2, 2, 1], Key = 2
Output:         2
Explanation:    The first two elements after removing every 'Key' will be [11, 1].
*/

import java.util.*;

public class RemoveKeys {
    public static int removeKeys(int[] arr, int key) {
        return -1;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 3, 2, 3, 6, 3, 10, 9, 3 };
        int[] s2 = new int[] { 2, 11, 2, 2, 1 };

        // Calculate results.
        int r1 = removeKeys(s1, 3);
        int r2 = removeKeys(s2, 2);

        // Print results.
        System.out.println("The new array after removing 3 from " + Arrays.toString(s1) + " has length: " + r1);
        System.out.println("The new array after removing 2 from " + Arrays.toString(s2) + " has length: " + r2);
    }
}
