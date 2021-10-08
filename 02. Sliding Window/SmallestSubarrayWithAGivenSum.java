/*
Smallest Subarray With A Given Sum [EASY]

Given an array of positive numbers and a positive number 'S', find the length of the
smallest contiguous subarray whose sum is greater than or equal to 'S'. Return 0, if
no such subarray exists.

--- EXAMPLES ---

- EX 1 -
Input:          [2, 1, 5, 2, 3, 2], S = 7
Output:         2
Explanation:    The smallest subarray with a sum greater than or equal to '7'
                is [5, 2].

- EX 2 -
Input:          [2, 1, 5, 2, 8], S = 7
Output:         1
Explanation:    The smallest subarray with a sum greater than or equal to '7'
                is [8].

- EX 3 -
Input:          [3, 4, 1, 1, 6], S = 8
Output:         3
Explanation:    Smallest subarrays with a sum greater than or equal to '8' are
                [3, 4, 1] or [1, 1, 6].
*/

import java.util.Arrays;

public class SmallestSubarrayWithAGivenSum {
    public static int findMinSubArray(int S, int[] arr) {
        int smallestLength = Integer.MAX_VALUE;

        int windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            while (windowSum >= S) {
                smallestLength = Math.min(smallestLength, windowEnd - windowStart + 1);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }

        return smallestLength == Integer.MAX_VALUE ? 0 : smallestLength;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 2, 1, 5, 2, 3, 2 };
        int[] s2 = new int[] { 2, 1, 5, 2, 8 };
        int[] s3 = new int[] { 3, 4, 1, 1, 6 };

        // Calculate results.
        int r1 = findMinSubArray(7, s1);
        int r2 = findMinSubArray(7, s2);
        int r3 = findMinSubArray(8, s3);
        int r4 = findMinSubArray(30, s3);

        // Print results.
        System.out.println("Min length of a subarray with sum greater than or equal to " + 7 + " of "
                + Arrays.toString(s1) + " is: " + r1);
        System.out.println("Min length of a subarray with sum greater than or equal to " + 7 + " of "
                + Arrays.toString(s2) + " is: " + r2);
        System.out.println("Min length of a subarray with sum greater than or equal to " + 8 + " of "
                + Arrays.toString(s3) + " is: " + r3);
        System.out.println("Min length of a subarray with sum greater than or equal to " + 30 + " of "
                + Arrays.toString(s3) + " is: " + r4);
    }
}
