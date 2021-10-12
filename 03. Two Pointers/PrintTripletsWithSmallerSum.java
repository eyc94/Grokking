/*
--- SIMILAR PROBLEMS ---
Print Triplets With Smaller Sum

Write a function to return the list of all such triplets instead of the count. How will the time
complexity change in this case?
*/

import java.util.*;

public class PrintTripletsWithSmallerSum {

    /*
     * This is a spin off problem from TripletsWithSmallerSum.java. Instead of
     * returning the number of triplets smallest than target, we return the list of
     * triplets.
     */

    public static List<List<Integer>> searchTriplets(int[] arr, int target) {
        // We sort the array first.
        Arrays.sort(arr);
        // This is the list of triplets.
        List<List<Integer>> triplets = new ArrayList<>();

        // We iterate through the array again, leaving space for the final triplet to
        // process.
        for (int i = 0; i < arr.length - 2; i++) {
            // We create the low and high boundaries for two sum.
            int low = i + 1;
            int high = arr.length - 1;

            // Work our way inside.
            while (low < high) {
                // Grab the triplet sum.
                int tripletSum = arr[i] + arr[low] + arr[high];

                // If the triplet sum matches criteria.
                if (tripletSum < target) {
                    // All numbers before 'high' and after 'low' meet the requirements for triplet
                    // sum smaller than target. So, we iterate from high to low + 1 and make those
                    // numbers the third number in our triplet.
                    for (int j = high; j > low; j--) {
                        triplets.add(Arrays.asList(arr[i], arr[low], arr[j]));
                    }
                    // Increment low because sum is less than target.
                    low++;
                } else {
                    // Decrement high because sum is greater than or equal to target.
                    high--;
                }
            }
        }
        // Return list of triplets.
        return triplets;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { -1, 0, 2, 3 };
        int[] s2 = new int[] { -1, 4, 2, 1, 3 };

        // Calculate results.
        List<List<Integer>> r1 = searchTriplets(s1, 3);
        List<List<Integer>> r2 = searchTriplets(s2, 5);

        // Print results.
        System.out.println("Triplets in " + Arrays.toString(s1) + " less than 3: " + r1);
        System.out.println("Triplets in " + Arrays.toString(s2) + " less than 5: " + r2);
    }
}
