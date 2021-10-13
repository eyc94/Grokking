/*
--- PROBLEM CHALLENGE 3 ---

Given an array, find the length of the smallest subarray in it which when sorted will
sort the whole array.

--- EXAMPLES ---

- EX 1 -
Input:          [1, 2, 5, 3, 7, 10, 9, 12]
Output:         5
Explanation:    We need to sort only the subarray [5, 3, 7, 10, 9] to make the whole array sorted.

- EX 2 -
Input:          [1, 3, 2, 0, -1, 7, 10]
Output:         5
Explanation:    We need to sort only the subarray [1, 3, 2, 0, -1] to make the whole array sorted.

- EX 3 -
Input:          [1, 2, 3]
Output:         0
Explanation:    The array is already sorted.

- EX 4 -
Input:          [3, 2, 1]
Output:         3
Explanation:    The whole array needs to be sorted.
*/

import java.util.*;

public class MinimumWindowSort {

    /*
     * This is the two pointer approach. We put the two pointers on both ends. This
     * is a little easy and tricky.
     * 
     * We move our low pointer up until (low + 1)'s value is less than low's value.
     * The low pointer now points to the start of our window.
     * 
     * We move our high pointer down until (high - 1)'s value is greater than high's
     * value. The high pointer now points to the end of the window.
     * 
     * We find the maximum and minimum values in our current window. This is because
     * we still might have values outside our window that needs to go inside our
     * window. For example, our minimum value of window can be -1. We can still have
     * the value of 1 outside our window as well.
     * 
     * We move our low pointer down while our (low - 1)'s value is greater than the
     * minimum of our window. Our low pointer now points to the proper location.
     * 
     * We move our high pointer up while our (high + 1)'s value is less than the
     * maximum of our window. Our high pointer now points to the proper location.
     * 
     * We now return the length of the window.
     * 
     * Time Complexity: O(N) where N is the length of our input array.
     * 
     * Space Complexity: O(1).
     */

    public static int sort(int[] arr) {
        // This is the two pointers starting from both ends.
        // Represents the borders of the subarray that we need to sort.
        int low = 0;
        int high = arr.length - 1;

        // Move low up until we find a number that is less than the current iteration
        // number. This denotes a discrepancy in the sorted array. The 'low' pointer now
        // points to the start of the window.
        while (low < arr.length - 1 && arr[low] <= arr[low + 1]) {
            low++;
        }

        // If low is equal to the last element of the array, this means the array is
        // already sorted. So, return 0.
        if (low == arr.length - 1) {
            return 0;
        }

        // Move high down until we find a number that is greater than the current
        // iteration number. This denotes a discrepancy. The 'high' pointer now points
        // to the start of the discrepancy.
        while (high > 0 && arr[high] >= arr[high - 1]) {
            high--;
        }

        // Find the min and max of the window.
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = low; i <= high; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        // We need to extend low pointer down more in case there are other numbers that
        // are greater than the minimum value of our window.
        // The low pointer now points to the proper spot.
        while (low > 0 && arr[low - 1] > min) {
            low--;
        }

        // We need to extend our high pointer up more in case there are other numbers
        // that are less than the maximum value of our window.
        // The high pointer now points to the proper spot.
        while (high < arr.length - 1 && arr[high + 1] < max) {
            high++;
        }

        // Return the length of our window.
        return high - low + 1;
    }

    public static void main(String[] args) {
        // Samples.
        int[] s1 = new int[] { 1, 2, 5, 3, 7, 10, 9, 12 };
        int[] s2 = new int[] { 1, 3, 2, 0, -1, 7, 10 };
        int[] s3 = new int[] { 1, 2, 3 };
        int[] s4 = new int[] { 3, 2, 1 };

        // Results.
        int r1 = sort(s1);
        int r2 = sort(s2);
        int r3 = sort(s3);
        int r4 = sort(s4);

        // Print.
        System.out.println("The length of subarray needed to sort in " + Arrays.toString(s1) + " is: " + r1);
        System.out.println("The length of subarray needed to sort in " + Arrays.toString(s2) + " is: " + r2);
        System.out.println("The length of subarray needed to sort in " + Arrays.toString(s3) + " is: " + r3);
        System.out.println("The length of subarray needed to sort in " + Arrays.toString(s4) + " is: " + r4);
    }
}
