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
        int leftBorder = 0;
        int rightBorder = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                leftBorder = i;
                break;
            }
        }

        for (int i = arr.length - 1; i > leftBorder; i--) {
            if (arr[i] < arr[i - 1]) {
                rightBorder = i;
                break;
            }
        }

        for (int i = leftBorder; i <= rightBorder; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        for (int i = leftBorder; i >= 0; i--) {
            if (arr[i] > min) {
                leftBorder = i;
            }
        }

        for (int i = rightBorder; i < arr.length; i++) {
            if (arr[i] < max) {
                rightBorder = i;
            }
        }

        if (leftBorder == 0 && rightBorder == 0) {
            return 0;
        }
        return rightBorder - leftBorder + 1;
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
