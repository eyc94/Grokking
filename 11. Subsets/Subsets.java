/*
Subsets [EASY]

Given a set with distinct elements, find all of its distinct subsets.

--- EXAMPLES ---

- EX 1 -
Input:      [1, 3]
Output:     [], [1], [3], [1, 3]

- EX 2 -
Input:      [1, 5, 3]
Output:     [], [1], [5], [3], [1, 5], [1, 3], [5, 3], [1, 5, 3]
*/

import java.util.*;

public class Subsets {

    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();

        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findSubsets(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = findSubsets(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}