/*
Subarrays With Product Less Than A Target [MEDIUM]

Given an array of integers "nums" and an integer "k", return the number of contiguous subarrays
where the product of all elements in the subarray is strictly less than "k".

--- EXAMPLES ---

- EX 1 -
Input:          nums = [10, 5, 2, 6], k = 100
Output:         8
Explanation:    The 8 subarrays that have product less than 100 are:
                [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
                Note that [10, 5, 2] is not included because it is not strictly less than 100.

- EX 2 -
Input:          nums = [1, 2, 3], k = 0
Output:         0
*/

import java.util.*;

public class SubarraysWithProductLessThanTarget {

    /*
     * This is a little tricky problem. We use a sliding window approach. We
     * initialize a product to the value of 1. We multiply the right number to the
     * product on every iteration.
     * 
     * If the product is >= k, we must shrink our window to have it < k. We shrink
     * by dividing the current window product by the window start pointer value.
     * 
     * Afterwards, we add to (length of window + 1) to the count variable. The
     * reason is this:
     * 
     * If our current window is [1, 2, 3] and the next number to add is [4], the
     * possible subarrays are [1], [1, 2], [1, 2, 3], and [1, 2, 3, 4]. This is
     * considering previous subarrays (single values) were already calculated. This
     * is just the NEW subarrays that come with adding [4] to our window. The amount
     * is 4, which is the length of the new window when adding value of 4.
     * 
     * Time Complexity: O(N) where N is the length of our array.
     * 
     * Space Complexity: O(1).
     */

    public static int searchProduct(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int count = 0;
        int product = 1;

        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            product *= nums[windowEnd];

            while (product >= k) {
                product /= nums[windowStart++];
            }

            count += windowEnd - windowStart + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { 10, 5, 2, 6 };
        int[] s2 = new int[] { 1, 2, 3 };

        // Calculate results.
        int r1 = searchProduct(s1, 100);
        int r2 = searchProduct(s2, 0);

        // Print results.
        System.out.println(
                "The number of subarrays with product less than 100 for " + Arrays.toString(s1) + " is: " + r1);
        System.out
                .println("The number of subarrays with product less than 0 for " + Arrays.toString(s2) + " is: " + r2);
    }
}
