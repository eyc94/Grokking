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
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> quadruplets = new ArrayList<>();

        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1]) {
                    continue;
                }
                int low = j + 1;
                int high = arr.length - 1;

                while (low < high) {
                    int quadrupletSum = arr[i] + arr[j] + arr[low] + arr[high];
                    if (quadrupletSum == target) {
                        quadruplets.add(Arrays.asList(arr[i], arr[j], arr[low], arr[high]));
                        low++;
                        high--;

                        while (low < high && arr[low] == arr[low - 1]) {
                            low++;
                        }

                        while (low < high && arr[high] == arr[high + 1]) {
                            high--;
                        }
                    } else if (quadrupletSum < target) {
                        low++;
                    } else {
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
