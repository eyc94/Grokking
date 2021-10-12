/*
Triplets With Smaller Sum [MEDIUM]

Given an array "arr" of unsorted numbers and a target sum, count all triplets in it such that
arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices. Write a function
to return the count of such triplets.

--- EXAMPLES ---

- EX 1 -
Input:          [-1, 0, 2, 3], target = 3
Output:         2
Explanation:    There are two triplets whose sum is less than the target: [-1, 0, 3], [-1, 0, 2].

- EX 2 -
Input:          [-1, 4, 2, 1, 3], target = 5
Output:         4
Explanation:    There are four triplets whose sum is less than the target: [-1, 1, 4], [-1, 1, 3],
                [-1, 1, 2], and [-1, 2, 3].-
*/

import java.util.*;

public class TripletsWithSmallerSum {
    public static int searchTriplets(int[] arr, int target) {
        // Sort the array.
        Arrays.sort(arr);
        // Keep track of triplets count whose sums add to less than target.
        int tripletCount = 0;

        // Iterate to the end of the array.
        // Leave room for the last triplet processing.
        for (int i = 0; i < arr.length - 2; i++) {
            // For every first number iteration.
            // Get the low and high boundaries to work our way in.
            int low = i + 1;
            int high = arr.length - 1;

            // Work our way in from both ends.
            while (low < high) {
                // Grab the triplet sum.
                int tripletSum = arr[i] + arr[low] + arr[high];
                // If the triplet sum meets the criteria.
                if (tripletSum < target) {
                    // So, when the triplet sum is less than the target sum.
                    // We know that every number less than the value pointed at by high is capable
                    // of becoming a triplet with sum less than target. This is because our array is
                    // sorted. So, all numbers before high and after low can be a potential third
                    // number. So add the distance between them.
                    tripletCount += high - low;
                    // Also, increment low because sum can go higher and still be less than target.
                    low++;
                } else {
                    // If triplet sum is equal to or greater than target, look for lower sum.
                    // Decrement high.
                    high--;
                }
            }
        }

        // Return count.
        return tripletCount;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { -1, 0, 2, 3 };
        int[] s2 = new int[] { -1, 4, 2, 1, 3 };

        // Calculate results.
        int r1 = searchTriplets(s1, 3);
        int r2 = searchTriplets(s2, 5);

        // Print results.
        System.out.println("Count of triplets in " + Arrays.toString(s1) + " less than 3: " + r1);
        System.out.println("Count of triplets in " + Arrays.toString(s2) + " less than 5: " + r2);
    }
}
