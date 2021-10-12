/*
Triplet Sum Close To Target [MEDIUM]

Given an array of unsorted numbers and a target number, find a triplet in the array whose
sum is as close to the target number as possible. Return the sum of the triplet. If there
are more than one such triplet, return the sum of the triplet with the smallest sum.

--- EXAMPLES ---

- EX 1 -
Input:          [-2, 0, 1, 2], target = 2
Output:         1
Explanation:    The triplet [-2, 1, 2] has the closest sum to the target.

- EX 2 -
Input:          [-3, -1, 1, 2], target = 1
Output:         0
Explanation:    The triplet [-3, 1, 2] has the closest sum to the target.

- EX 3 -
Input:          [1, 0, 1, 1], target = 100
Output:         3
Explanation:    The triplet [1, 1, 1] has the closest sum to the target.
*/

import java.util.*;

public class TripletSumCloseToTarget {

    /*
     * The approach we use here is the same as triplet sum to zero. The difference
     * is that we look for the triplet that gives us a sum that is closest to the
     * target sum. The triplet that adds to the target sum is the obvious winner.
     * 
     * To do this, we sort the array. We also keep track of the closest distance
     * seen so far. Initialize it to the max integer possible. We don't have to
     * worry about returning this large value because our array will have an answer
     * no matter what.
     * 
     * We iterate through our array to the 3rd to last to make room for the last
     * possible triplet.
     * 
     * For every iteration of our first number, we do two sum on the remainder with
     * our low and high pointers.
     * 
     * We work our way in. We find the difference between the target sum and the
     * current triplet's sum. If the sum is the target, we can immediately return
     * the sum.
     * 
     * If not, this means we have to look more. We are basically just calculating
     * the difference. If the calculated difference is smaller than what we have so
     * far, we update it.
     * 
     * Now, we need to update the low and high pointers. If our current sum is
     * greater than the target (target - currentSum < 0), this means our distance is
     * negative. We need to go lower. So, decrement high.
     * 
     * If our current sum is less than target, we need to go higher. Increment low.
     * 
     * Basically, we aren't keeping track of triplets. We are keeping track the
     * distance of triplets to the target. No matter what triplets add up to a
     * certain sum, we can always find that sum with our target and distance to
     * target.
     * 
     * Once we're all done, we just take the difference between the target and the
     * closest distance to target we have found. This will give us the sum of the
     * triplets that are the closest to target.
     * 
     * Time Complexity: O(N^2 + N log N) because we are sorting and searching the
     * array for every iteration of the first number. The asymptotic time complexity
     * is then O(N^2).
     * 
     * Space Complexity: O(N) if we take into account the space used for sorting.
     */

    public static int searchTriplet(int[] arr, int targetSum) {
        // Sort the array
        Arrays.sort(arr);

        // Keep track of the closest distance a triplet's sum had so far.
        int closestDistance = Integer.MAX_VALUE;
        // Iterate through the array.
        for (int i = 0; i < arr.length - 2; i++) {
            // Perform two sum.
            // Get low and high pointers.
            int low = i + 1;
            int high = arr.length - 1;

            // Work our way in from both ends.
            while (low < high) {
                // Calculate the distance from the triplet sum to the target sum.
                // Our idea is that the closer it is to the target, we keep the sum.
                int distance = targetSum - arr[i] - arr[low] - arr[high];

                // If our triplet's sum IS the target, we return the sum of the triplet
                // immediately. The sum of the triplet = target sum - distance. The distance is
                // 0, so we just return the targetSum.
                if (distance == 0) {
                    return targetSum;
                }

                // We update the closest distance so far.
                // If the current triplet sum distance is shorter than a previously seen one, we
                // update it.
                if (Math.abs(distance) < Math.abs(closestDistance)) {
                    closestDistance = distance;
                }

                // If our distance is positive, this means that our target sum is greater than
                // the current triplet sum. So, we need a higher sum. Increment low.
                if (distance > 0) {
                    low++;
                } else { // If distance is negative. This means target sum is less than current sum.
                    // Need lower sum. Decrement high.
                    high--;
                }
            }

        }

        // At the end of everything, our closestDistance represents the best triplet sum
        // close to target that we could have found. So we just return the sum of the
        // triplet that allows this. This is targetSum - closestDistance.
        return targetSum - closestDistance;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { -2, 0, 1, 2 };
        int[] s2 = new int[] { -3, -1, 1, 2 };
        int[] s3 = new int[] { 1, 0, 1, 1 };

        // Calculate results.
        int r1 = searchTriplet(s1, 2);
        int r2 = searchTriplet(s2, 1);
        int r3 = searchTriplet(s3, 100);

        // Print results.
        System.out.println("The sum of the triplet that sums closests to 2 in " + Arrays.toString(s1) + " is: " + r1);
        System.out.println("The sum of the triplet that sums closests to 1 in " + Arrays.toString(s2) + " is: " + r2);
        System.out.println("The sum of the triplet that sums closests to 100 in " + Arrays.toString(s3) + " is: " + r3);
    }
}
