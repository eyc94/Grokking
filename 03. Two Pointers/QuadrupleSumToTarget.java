/*
--- PROBLEM CHALLENGE 1 ---
Quadruple Sum To Target [MEDIUM]

Given an array of unsorted numbers and a target number, find all unique quadruplets in it,
whose sum is equal to the target number.

--- EXAMPLES ---

- EX 1 -
Input:          [4, 1, 2, -1, 1, -3], target = 1
Output:         [-3, -1, 1, 4], [-3, 1, 1, 2]
Explanation:    Both the quadruplets add up to the target.

- EX 2 -
Input:          [2, 0, -1, 1, -2, 2], target = 2
Output:         [-2, 0, 2, 2], [-1, 0, 1, 2]
Explanation:    Both the quadruplets add up to the target.
*/

import java.util.*;

public class QuadrupleSumToTarget {

    /*
     * We use the same pointer approach. This time we add an extra loop because we
     * are looking for quadruplets. The process is all the same. Look at code for
     * explanation.
     * 
     * Time Complexity: O(N^3). We need O(N log N) to sort. Finding triplets now
     * takes O(N^3) time. Total is O(N^3 + N log N) which is O(N^3) asymptotically.
     * 
     * Space Complexity: O(N) if we just take into account space required to sort.
     */

    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        // Sort the array.
        Arrays.sort(arr);
        // List of quadruplets to return.
        List<List<Integer>> quadruplets = new ArrayList<>();

        // We iterate to the 3rd to last to make room for the final quadruplet to
        // process.
        for (int i = 0; i < arr.length - 3; i++) {
            // If we are not on the very first of the first numbers, and this new first
            // number is equal to the old first number, we skip. This is because if we do
            // not skip, there will be duplicates.
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            // This is the loop for the second number (start of a triplet).
            for (int j = i + 1; j < arr.length - 2; j++) {
                // Same thing here, check to see if we have already encountered the same second
                // number. If so, skip it.
                if (j > i + 1 && arr[j] == arr[j - 1]) {
                    continue;
                }
                // This is the two sum portion.
                // Get the low and high boundaries to work inwards.
                int low = j + 1;
                int high = arr.length - 1;

                // Work inwards.
                while (low < high) {
                    // Get the quadruplet sum.
                    int quadrupletSum = arr[i] + arr[j] + arr[low] + arr[high];
                    // If quadruplet sum is the target.
                    if (quadrupletSum == target) {
                        // Add the quadruplet to the list to return.
                        quadruplets.add(Arrays.asList(arr[i], arr[j], arr[low], arr[high]));
                        // Increment both low and high.
                        low++;
                        high--;

                        // Now before we proceed, we check to see if our new high and lows are the same
                        // as the ones we just processed and added. If so, we skip over these values. Do
                        // this by incrementing low and decrementing high.
                        while (low < high && arr[low] == arr[low - 1]) {
                            low++;
                        }

                        while (low < high && arr[high] == arr[high + 1]) {
                            high--;
                        }
                    } else if (quadrupletSum < target) {
                        // If the sum is less than the target, do what we normally did. Increment low.
                        low++;
                    } else {
                        // If the sum is greater than the target, decrement high.
                        high--;
                    }
                }
            }
        }

        return quadruplets;
    }

    public static void main(String[] args) {
        // Arrays.
        int[] s1 = new int[] { 4, 1, 2, -1, 1, -3 };
        int[] s2 = new int[] { 2, 0, -1, 1, -2, 2 };

        // Results.
        List<List<Integer>> r1 = searchQuadruplets(s1, 1);
        List<List<Integer>> r2 = searchQuadruplets(s2, 2);

        // Print.
        System.out.println("The quadruplets of " + Arrays.toString(s1) + " that add to 1 are: " + r1);
        System.out.println("The quadruplets of " + Arrays.toString(s2) + " that add to 2 are: " + r2);
    }
}
