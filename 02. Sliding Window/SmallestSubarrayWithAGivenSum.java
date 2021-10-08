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

    /*
     * We implement a Sliding Window technique. This is different in that there is
     * no "fixed" size. Our size can be anything just as long as it meets the sum
     * requirement, and it is the smallest possible.
     * 
     * We increase until we reach greater than or equal to 'S'. Once we do,
     * calculate the length and update min length if needed. Now, see if we can make
     * our window smaller. Make the window smaller by subtracting the left end from
     * our window sum. And keep checking. This process repeats until our sum is less
     * than 'S'. We then increment the right end, and we repeat the above.
     * 
     * Time Complexity: O(N) where N is the length of our input array. We will be
     * processing every value of our array. The outer for loop runs for all
     * elements. The inner while loop processes each element only once. Therefore,
     * time complexity is O(N + N), which is O(N) asymptotically.
     * 
     * Space Complexity: O(1).
     */

    public static int findMinSubArray(int S, int[] arr) {
        // Holds the smallest length subarray whose sum is greater than or equal to 'S'.
        int smallestLength = Integer.MAX_VALUE;

        // This holds the current window's sum.
        int windowSum = 0;
        // This is the left end of the window.
        int windowStart = 0;
        // We will expand the window from the right end.
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Add the right end to the current window sum.
            windowSum += arr[windowEnd];
            // Check to see if our current window's sum is greater than or equal to 'S'.
            // We will shrink our window to see if the sum requirements fit and how much
            // smaller we can get.
            while (windowSum >= S) {
                // While it is, we do two things.
                // 1. Update the min length if needed.
                smallestLength = Math.min(smallestLength, windowEnd - windowStart + 1);
                // 2. Shrink our window by subtracting the left end from the window sum.
                windowSum -= arr[windowStart];
                // Increment our left end to the right.
                windowStart++;
            }
        }

        // If our smallest length is still holding the largest possible value, this
        // means there does not exist a subarray whose sum is greater than or equal to
        // 'S'. So, we return 0. Otherwise, return the value in the variable.
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
