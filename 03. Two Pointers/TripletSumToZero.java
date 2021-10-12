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

    /*
     * This utilizes basically the concept from Two Sum. We do Two Sum for every
     * value of our array up to the 3rd to last. This gives us room for the final
     * triplet to process.
     * 
     * First, we must sort the array. This makes everything easier.
     * 
     * We iterate our array through to the 3rd to last number. On each iteration we
     * perform what we would have for Two Sum. Only that the target is now -X where
     * X is the first number of our triplet. Our second and third numbers must add
     * to -X to get a triplet with sum of 0.
     * 
     * If we're on our second and greater iteration, first check if this 'firstNum'
     * is the same as our previous iteration's first number. If so, then this will
     * produce a duplicate and we should skip this iteration. So, we continue.
     * 
     * Otherwise, we know this will be a unique starting number for a potential
     * triplet.
     * 
     * We assign a low and high pointer to point to both ends of the remaining
     * numbers. These represent the second and third number, respectively.
     * 
     * We implement Two Sum where the target is 0 - firstNum (or -firstNum).
     * 
     * If the two numbers add to -firstNum, we found our triplet. Add the triplets
     * to the results and increment low and decrement high.
     * 
     * NOW, we must check for whether our NEW second and third numbers are the same
     * as that we just processed. If our current low is equal to the previous low,
     * increment low. If our current high is equal to the previous high, decrement
     * high.
     * 
     * If the sum of the numbers is less than the target, we need a bigger sum. So,
     * we increment low. If the sum of the numbers is greater than the target, we
     * need a smaller sum. So, we decrement high.
     * 
     * Repeat this whole process until our 'i' scanner reaches 3rd to last number.
     * 
     * Time Complexity: O(N^2) where N is the length of our input array. We need O(N
     * log N) for sorting. When searching for triplets, it takes O(N^2) time. Total
     * is O(N^2 + N log N) which is O(N^2) time asymptotically.
     * 
     * Space Complexity: O(N) if we ignore the space for result triplets and just
     * use space for sorting.
     */

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
