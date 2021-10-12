/*
Triplet Sum To Zero [MEDIUM]

Given an array of unsorted numbers, find all unique triplets in it that add up to zero.

--- EXAMPLES ---

- EX 1 -
Input:          [-3, 0, 1, 2, -1, 1, -2]
Output:         [[-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]]
Explanation:    There are four unique triplets whose sum is equal to zero.

- EX 2 -
Input:          [-5, 2, -1, -2, 3]
Output:         [[-5, 2, 3], [-2, -1, 3]]
Explanation:    There are two unique triplets whose sum is equal to zero.
*/

import java.util.*;

public class TripletSumToZero {

    public static List<List<Integer>> searchTriplets(int[] arr) {
        // Sort the array first.
        Arrays.sort(arr);
        // This is the triplets list we return later.
        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i <= arr.length - 3; i++) {
            // If the first number is the same as the previous iteration's first number.
            // We need to skip this iteration because it'll produce duplicates.
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            // If we get here, first number is unique.
            // Grab the first number.
            int firstNum = arr[i];
            // Grab the low pointer.
            int low = i + 1;
            // Grab the high pointer.
            int high = arr.length - 1;
            // Grab the target that the low and high pointer will find.
            int target = 0 - firstNum;
            // We work our way in from both ends of low and high.
            while (low < high) {
                // Grab the sum of third and second numbers.
                int sum = arr[low] + arr[high];
                // If the sum is the target.
                if (sum == target) {
                    // Add all three numbers to the triplets list.
                    triplets.add(Arrays.asList(firstNum, arr[low], arr[high]));
                    // Move the ends down.
                    low++;
                    high--;
                    // WE NOW CHECK IF 2ND and 3RD NUMBERS ARE THE SAME AS PREVIOUS.
                    // If the low pointer value is the same as the previous low pointer's value.
                    // This means we would produce a duplicate with the same second number.
                    // Increment low.
                    while (low < high && arr[low] == arr[low - 1]) {
                        low++;
                    }

                    // If the high pointer is the same as the previous high pointer's value.
                    // This means we would produce a duplicate with the same third number.
                    // Decrement high.
                    while (low < high && arr[high] == arr[high + 1]) {
                        high--;
                    }
                } else if (sum < target) { // If sum is less than target, go up.
                    low++;
                } else { // If sum is greater than target, go down.
                    high--;
                }
            }
        }
        // Return all triplets.
        return triplets;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { -3, 0, 1, 2, -1, 1, -2 };
        int[] s2 = new int[] { -5, 2, -1, -2, 3 };
        int[] s3 = new int[] { -3, -3, 2, 2, -1, 1, 1, 3 };

        // Calculate results.
        List<List<Integer>> r1 = searchTriplets(s1);
        List<List<Integer>> r2 = searchTriplets(s2);
        List<List<Integer>> r3 = searchTriplets(s3);

        // Print results.
        System.out.println("The triplets in " + Arrays.toString(s1) + " that add up to 0 are: " + r1);
        System.out.println("The triplets in " + Arrays.toString(s2) + " that add up to 0 are: " + r2);
        System.out.println("The triplets in " + Arrays.toString(s3) + " that add up to 0 are: " + r3);
    }
}
