/*
Longest Subarray With Ones After Replacement [HARD]

Given an array containing 0s and 1s, if you are allowed to replace no more than 'k' 0s with 1s,
find the length of the longest contiguous subarray having all 1s.

--- EXAMPLES ---

- EX 1 -
Input:          Array = [0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1], k = 2
Output:         6
Explanation:    Replace the '0' at index 5 and 8 to have the longest contiguous subarray of 1s
                having length 6.

- EX 2 -
Input:          Array = [0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1], k = 3
Output:         9
Explanation:    Replace the '0' at index 6, 9, and 10 to have the longest contiguous subarray of
                1s having length 9.
*/

import java.util.*;

public class LongestSubarrayWithOnesAfterReplacement {

    public static int findLength(int[] arr, int k) {
        // We keep track of how many 1s our current sliding window has.
        int onesCount = 0;
        // We keep track of the longest length of repeating 1s after 'k' replacements.
        int longestLength = 0;

        // This is the left end of our sliding window.
        int windowStart = 0;
        // We expand the right end of our sliding window.
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Grab the right end's number.
            int rightNum = arr[windowEnd];
            // If the number is a 1, we increment the ones count.
            if (rightNum == 1) {
                onesCount++;
            }

            // Now, we have to find out if our current window supports 'k' replacements.
            // The current window length - onesCount gives us the number of 0s that need to
            // change to 1s. If this value is greater than the operations allowed (k), we
            // begin shrinking our window from the left end. Otherwise, we do not go through
            // this if statement.
            if (windowEnd - windowStart + 1 - onesCount > k) {
                // If the value on the left end is 1, we decrement the 1st count.
                if (arr[windowStart] == 1) {
                    onesCount--;
                }
                // Move the left window end to the right. Shrink!
                windowStart++;
            }

            // Now our current sliding window fulfills the requirements. So, update max
            // length if needed.
            longestLength = Math.max(longestLength, windowEnd - windowStart + 1);
        }
        return longestLength;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 };
        int[] s2 = new int[] { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 };

        // Calculate results.
        int r1 = findLength(s1, 2);
        int r2 = findLength(s2, 3);

        // Print results.
        System.out.println("The longest subarray having all ones in array " + Arrays.toString(s1) + " is: " + r1);
        System.out.println("The longest subarray having all ones in array " + Arrays.toString(s2) + " is: " + r2);
    }
}
