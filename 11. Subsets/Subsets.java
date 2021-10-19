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

    /*
     * This is a little tricky. We use BFS to solve this. We can pretty much tell
     * how BFS works here. We initially add the (root) common subset. This is the
     * empty set. If you notice, all subsets will have an empty subset. Even for
     * inputs that are empty.
     * 
     * So, we initially add the empty subset.
     * 
     * We then iterate through our nums array placing each number in all the subsets
     * that came before it.
     * 
     * To do this, we grab the list of subsets's size. We iterate through the
     * subset's size, and we create a new list with the values in that subset's 'i'
     * position. We add our current number into it. We add this set to the subset.
     * Then, we repeat for the remaining 'i' values in our subset.
     * 
     * Basically, we're kind of doing a BFS in the sense that we grab the size of
     * our subset so far. We iterate through the subset's element one by one up to
     * the size of the subset. We add our current number to it like we said we would
     * do (add the current number to the subsets before it).
     * 
     * We keep doing this until we run out of the current numbers.
     * 
     * Time Complexity: O(2^N) where 'N' is the total number of elements in the
     * input set. In each step, the number of subsets doubles as we add each element
     * to all the existing subsets. This means in the end we will have O(2^N)
     * subsets.
     * 
     * Space Complexity: The additional space used by our algorithm is for the
     * output list. We said that the number of subsets is 2^N so the space is
     * O(2^N).
     */

    public static List<List<Integer>> findSubsets(int[] nums) {
        // List of subsets to return to the user.
        List<List<Integer>> subsets = new ArrayList<>();

        // Initially add the empty subset.
        subsets.add(new ArrayList<>());

        for (int currentNumber : nums) {
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(currentNumber);
                subsets.add(set);
            }
        }

        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findSubsets(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = findSubsets(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}