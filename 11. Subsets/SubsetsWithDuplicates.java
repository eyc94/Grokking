/*
Subsets With Duplicates [EASY]

Given a set of numbers that might contain duplicates, find all of its distinct subsets.

--- EXAMPLES ---

- EX 1 -
Input:      [1, 3, 3]
Output:     [], [1], [3], [1, 3], [3, 3], [1, 3, 3]

- EX 2 -
Input:      [1, 5, 3, 3]
Output:     [], [1], [5], [1, 5], [3], [1, 3], [5, 3], [1, 5, 3], [3, 3], [1, 3, 3],
            [5, 3, 3], [1, 5, 3, 3].
*/

import java.util.*;

public class SubsetsWithDuplicates {

    /*
     * This is an adaptation of the previous code Subsets.java. The only difference
     * is that we CANNOT have duplicate sets in our subsets.
     * 
     * To make it easier to combat this, we sort our array so that duplicates are
     * next to each other. When we encounter a duplicate, we want to skip the
     * numbers that are first copy already iterated over. Usually, this is the first
     * half. We want to skip over this first half. We only want to add our duplicate
     * to the subsets created in the previous step.
     * 
     * Basically, we keep track of a start and end index that we can use to keep
     * track of which subsets we are on. Originally, in our last code, we just
     * iterated to the subset size. We don't want to do this anymore. We update the
     * start index based on whether we have a duplicate.
     * 
     * If we DO NOT encounter a duplicate, business as usual. We just keep start
     * index at 0 and end index at the size - 1. This way the end index points to
     * the last set of our subset.
     * 
     * If we DO encounter a duplicate, we know that we referenced the previous
     * iteration's subsets by looking at the subsets that ended at the end index
     * which pointed at size - 1. We know the previous iteration's subsets started
     * at end index + 1, which is just size. So, we shift start index to end index +
     * 1. The end index then gets updated to size - 1 as usual.
     * 
     * We then iterate from start index to end index to get the index to grab the
     * subset from the list of subsets to copy over to our set. Then, business as
     * usual. Add the current number to the set, and add the set to the list of
     * subsets.
     * 
     * Time Complexity: O(2^N). We know O(N log N) to sort. O(2^N) like the previous
     * code. 'N' is the length of nums. This also means we have O(2^N) subsets.
     * 
     * Space Complexity: O(2^N) because like we said we end up with 2^N subsets.
     */

    public static List<List<Integer>> findSubsets(int[] nums) {
        // Sort the array.
        Arrays.sort(nums);
        // Make a list of subsets.
        List<List<Integer>> subsets = new ArrayList<>();
        // Add an empty set to the list of subsets.
        subsets.add(new ArrayList<>());

        // Make a start and end index for us to traverse the previous subsets to add
        // current number to.
        int startIndex = 0;
        int endIndex = 0;

        // Iterate through the nums array.
        for (int i = 0; i < nums.length; i++) {
            // Initialize start index to 0 at the start of every iteration.
            startIndex = 0;

            // If our index is from 2nd position onward and the number is equal to previous
            // number, we need to skip our start index to the start of the previous
            // iteration's subsets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                startIndex = endIndex + 1;
            }

            // Otherwise, the end index reaches the end of the subsets.
            endIndex = subsets.size() - 1;

            // Iterate our subsets from startIndex to endIndex.
            // This is basically our previous iteration's list of subsets.
            for (int j = startIndex; j <= endIndex; j++) {
                // Make a list for the current set to add.
                // We copy over the set at position 'j'.
                List<Integer> set = new ArrayList<>(subsets.get(j));
                // Add current number 'i' from nums array to the set.
                set.add(nums[i]);
                // Add the completed set to the subsets.
                subsets.add(set);
            }
        }

        // Return the list of subsets.
        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findSubsets(new int[] { 3, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
