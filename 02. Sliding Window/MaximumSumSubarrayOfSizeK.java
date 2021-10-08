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

public class MaximumSumSubarrayOfSizeK {

    /*
     * There is a brute force approach that requires us to find every possible
     * contiguous subarray of size 'k'. We calculate the max sum so far after we
     * find a subarray.
     * 
     * The above approach is very bad. The time complexity is O(N * k) where 'N' is
     * the length of our input and 'k' is the length of our contiguous subarray.
     * This is because for every element of our array (minus the last 'k + 1'
     * elements) we iterate 'k' elements over to find the subarray.
     * 
     * --- OPTIMAL ---
     * 
     * The optimal solution is below. We keep a sliding window that maintains a size
     * 'k'. When it reaches size 'k', we calculate the sum and compare to max so
     * far. When moving our window, we shrink then expand. By this, we subtract from
     * our window sum the left end. We increment the left end (shrink). Increment
     * the right end (expand). Then add the right end to the window sum. The
     * condition matches again. The process repeats.
     * 
     * This saves us time in computing the sum of already computed overlapping sums.
     * 
     * Time Complexity: O(N) where 'N' is the length of our input array.
     * 
     * Space Complexity: O(1).
     */

    public static int findMaxSumSubArray(int k, int[] arr) {
        // Calculates the max sum of any contiguous subarray of size 'k' so far.
        int maxSumSoFar = 0;
        // Current window sum.
        int windowSum = 0;
        // This is the start of the window.
        int windowStart = 0;
        // Expand the window from the right end.
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Add the right end of the window to current window sum.
            windowSum += arr[windowEnd];
            // If the window meets the length requirement.
            if (windowEnd >= k - 1) {
                // Update the max sum if needed.
                maxSumSoFar = Math.max(maxSumSoFar, windowSum);
                // Shrink our window.
                // Subtract the left end from window sum.
                windowSum -= arr[windowStart];
                // Move the left end to the right one element.
                windowStart++;
            }
        }
        // Return result.
        return maxSumSoFar;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 2, 1, 5, 1, 3, 2 };
        int[] s2 = new int[] { 2, 3, 4, 1, 5 };

        // Calculate results.
        int r1 = findMaxSumSubArray(3, s1);
        int r2 = findMaxSumSubArray(2, s2);

        // Print results.
        System.out.println("Max sum of a subarray of size " + 3 + " of " + Arrays.toString(s1) + " is: " + r1);
        System.out.println("Max sum of a subarray of size " + 2 + " of " + Arrays.toString(s2) + " is: " + r2);
    }
}