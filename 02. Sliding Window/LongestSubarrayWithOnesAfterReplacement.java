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
        int onesCount = 0;
        int longestLength = 0;

        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            int rightNum = arr[windowEnd];
            if (rightNum == 1) {
                onesCount++;
            }

            while (windowEnd - windowStart + 1 - onesCount > k) {
                if (arr[windowStart] == 1) {
                    onesCount--;
                }
                windowStart++;
            }

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
