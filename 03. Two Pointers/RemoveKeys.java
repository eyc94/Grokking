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
    public static int remove(int[] arr, int key) {
        // This is the border. This is the next spot where we place non-keys.
        // Everything to the left of this border will have non-keys.
        int border = 0;
        // Iterate from the beginning to the end.
        for (int i = 0; i < arr.length; i++) {
            // If the current value is not a key.
            if (arr[i] != key) {
                // Put that value at the border (next spot to take non-key).
                arr[border] = arr[i];
                // Increment that border.
                border++;
            }
        }
        // Return border because it'll be the length of the array without the keys.
        return border;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 3, 2, 3, 6, 3, 10, 9, 3 };
        int[] s2 = new int[] { 2, 2, 2, 2, 1 };

        // Calculate results.
        int r1 = remove(s1, 3);
        int r2 = remove(s2, 2);

        // Print results.
        System.out.println("The new array after removing 3 from " + Arrays.toString(s1) + " has length: " + r1);
        System.out.println("The new array after removing 2 from " + Arrays.toString(s2) + " has length: " + r2);
    }
}
