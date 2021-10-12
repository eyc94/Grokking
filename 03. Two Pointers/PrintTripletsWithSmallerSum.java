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
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i < arr.length - 2; i++) {
            int low = i + 1;
            int high = arr.length - 1;

            while (low < high) {
                int tripletSum = arr[i] + arr[low] + arr[high];

                if (tripletSum < target) {
                    for (int j = high; j > low; j--) {
                        triplets.add(Arrays.asList(arr[i], arr[low], arr[j]));
                    }
                    low++;
                } else {
                    high--;
                }
            }
        }

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
