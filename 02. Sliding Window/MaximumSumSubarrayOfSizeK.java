/*
Maximum Sum Subarray of Size K [EASY]

Given an array of positive numbers and a positive number 'k', find the maximum sum of any
contiguous subarray of size 'k'.

--- EXAMPLES ---

- EX 1 -
Input:          [2, 1, 5, 1, 3, 2], k = 3
Output:         9
Explanation:    Subarray with maximum sum is [5, 1, 3].

- EX 2 -
Input:          [2, 3, 4, 1, 5], k = 2
Output:         7
Explanation:    Subarray with maximum sum is [3, 4].
*/

import java.util.Arrays;

class MaximumSumSubarrayOfSizeK {
    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSumSoFar = 0;
        int windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if (windowEnd >= k - 1) {
                maxSumSoFar = Math.max(maxSumSoFar, windowSum);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return maxSumSoFar;
    }

    public static void main(String[] args) {
        int[] s1 = new int[] { 2, 1, 5, 1, 3, 2 };
        int[] s2 = new int[] { 2, 3, 4, 1, 5 };

        int r1 = findMaxSumSubArray(3, s1);
        int r2 = findMaxSumSubArray(2, s2);

        System.out.println("Max sum of a subarray of size " + 3 + " of " + Arrays.toString(s1) + " is: " + r1);
        System.out.println("Max sum of a subarray of size " + 2 + " of " + Arrays.toString(s2) + " is: " + r2);
    }
}