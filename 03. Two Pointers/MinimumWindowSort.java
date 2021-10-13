/*
--- PROBLEM CHALLENGE 3 ---

Given an array, find the length of the smallest subarray in it which when sorted will
sort the whole array.

--- EXAMPLES ---

- EX 1 -
Input:          [1, 2, 5, 3, 7, 10, 9, 12]
Output:         5
Explanation:    We need to sort only the subarray [5, 3, 7, 10, 9] to make the whole array sorted.

- EX 2 -
Input:          [1, 3, 2, 0, -1, 7, 10]
Output:         5
Explanation:    We need to sort only the subarray [1, 3, 2, 0, -1] to make the whole array sorted.

- EX 3 -
Input:          [1, 2, 3]
Output:         0
Explanation:    The array is already sorted.

- EX 4 -
Input:          [3, 2, 1]
Output:         3
Explanation:    The whole array needs to be sorted.
*/

import java.util.*;

public class MinimumWindowSort {
    public static int sort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while (low < arr.length - 1 && arr[low] <= arr[low + 1]) {
            low++;
        }

        if (low == arr.length - 1) {
            return 0;
        }

        while (high > 0 && arr[high] >= arr[high - 1]) {
            high--;
        }

        for (int i = low; i <= high; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        while (low > 0 && arr[low - 1] > min) {
            low--;
        }

        while (high < arr.length - 1 && arr[high + 1] < max) {
            high++;
        }

        return high - low + 1;
    }

    public static void main(String[] args) {
        // Samples.
        int[] s1 = new int[] { 1, 2, 5, 3, 7, 10, 9, 12 };
        int[] s2 = new int[] { 1, 3, 2, 0, -1, 7, 10 };
        int[] s3 = new int[] { 1, 2, 3 };
        int[] s4 = new int[] { 3, 2, 1 };

        // Results.
        int r1 = sort(s1);
        int r2 = sort(s2);
        int r3 = sort(s3);
        int r4 = sort(s4);

        // Print.
        System.out.println("The length of subarray needed to sort in " + Arrays.toString(s1) + " is: " + r1);
        System.out.println("The length of subarray needed to sort in " + Arrays.toString(s2) + " is: " + r2);
        System.out.println("The length of subarray needed to sort in " + Arrays.toString(s3) + " is: " + r3);
        System.out.println("The length of subarray needed to sort in " + Arrays.toString(s4) + " is: " + r4);
    }
}
