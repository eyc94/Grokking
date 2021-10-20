/*
Permutations [MEDIUM]

Given a set of distinct numbers, find all of its permutations.

Permutation is defined as the re-arranging of the elements of the set. For example,
{1, 2, 3} has the following size permutations:

    1. {1, 2, 3}
    2. {1, 3, 2}
    3. {2, 1, 3}
    4. {2, 3, 1}
    5. {3, 1, 2}
    6. {3, 2, 1}

If a set has 'n' distinct elements it will have n! permutations.

--- EXAMPLES ---

- EX 1 -
Input:      [1, 3, 5]
Output:     [1, 3, 5], [1, 5, 3], [3, 1, 5], [3, 5, 1], [5, 1, 3], [5, 3, 1].
*/

import java.util.*;

public class Permutations {
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findPermutations(new int[] { 1, 3, 5 });
        System.out.println("Here are all the permutations: " + result);
    }
}
