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
    public static int searchTriplet(int[] arr, int targetSum) {
        Arrays.sort(arr);

        int closestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            int low = i + 1;
            int high = arr.length - 1;

            while (low < high) {
                int distance = targetSum - arr[i] - arr[low] - arr[high];

                if (distance == 0) {
                    return targetSum - distance;
                }

                if (Math.abs(distance) < Math.abs(closestDistance)) {
                    closestDistance = distance;
                }

                if (distance > 0) {
                    low++;
                } else {
                    high--;
                }
            }

        }

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
