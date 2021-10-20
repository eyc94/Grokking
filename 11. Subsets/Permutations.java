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

    /*
     * We use BFS to solve the permutations of an input array. Imagine the example
     * above:
     * 
     * 1. If the given set is empty then we have only an empty permutation set: []
     * 
     * 2. Let's add the first element (1), the permutations will be: [1]
     * 
     * 3. Let's add the second element (3), the permutations will be: [3, 1], [1, 3]
     * 
     * 4. Let's add the third element (5), the permutations will be: [5, 3, 1], [3,
     * 5, 1], [3, 1, 5], [5, 1, 3], [1, 5, 3], and [1, 3, 5]
     * 
     * let's analyze step 3 to 4. Notice that when we add 5 to the permutations, we
     * are adding them in every position to generate the new permutations.
     * 
     * 1. Inserting '5' before '3': [5, 3, 1]
     * 
     * 2. Inserting '5' between '3' and '1': [3, 5, 1]
     * 
     * 3. Inserting '5' after '1': [3, 1, 5]
     * 
     * So, what we are going to do is iterate through all our input numbers. For
     * each number, we will place them in the positions of the previous
     * permutations.
     * 
     * We first add an empty array to the permutations queue. We iterate through the
     * nums input array. We also iterate through the queue size.
     * 
     * We take the first list out of the queue. This represents the previous
     * iteration's permutation.
     * 
     * We iterate through the numbers in our permutations we just took from our
     * queue. This is because each number 'j' will represent the index of the
     * permutation we will add our new number. If the permutation we pulled from the
     * queue is of size 2, our 'j' values will be 0, 1, and 2. This means we will
     * place the current number (new) in spots 0 (before the first), 1 (between the
     * first and second), and 2 (after the second).
     * 
     * We copy the permutation we got from the queue into a new permutation list. We
     * then add the current number into the new permutation.
     * 
     * We check if the size of the new permutation is equal to the length of our
     * input nums array. If so, add this permutation to the results list. Otherwise,
     * we add it back to the queue to add the next number for processing.
     * 
     * Time Complexity: O(N * N!) where 'n' is the length of our input. This is
     * because we know there are N! permutations possible. We are iterating through
     * all our nums. To insert a number into arraylist will take O(N).
     * 
     * Space Complexity: O(N * N!) because of results list and queue.
     */

    public static List<List<Integer>> findPermutations(int[] nums) {
        // This is the list of permutations.
        // These permutations are in the form of a list.
        List<List<Integer>> result = new ArrayList<>();

        // We have a queue that holds the permutations to process.
        // These permutations are not complete yet. That is, they are not equal to the
        // size of the original input size.
        Queue<List<Integer>> permutations = new LinkedList<>();

        // Add the empty ArrayList.
        permutations.add(new ArrayList<>());

        // We iterate through all numbers in our array.
        for (int currentNumber : nums) {
            // We find the size of the permutations queue.
            // We will be processing the queue of permutations to add the current number to
            // it.
            int n = permutations.size();

            // We iterate through the permutations.
            for (int i = 0; i < n; i++) {
                // Take the first list from the queue.
                List<Integer> oldPermutation = permutations.poll();

                // We iterate from 0 to the size of permutation we just took out.
                // We want to insert the current number into all slots in the list.
                for (int j = 0; j <= oldPermutation.size(); j++) {
                    // Make a temporary permutation to store the new permutation.
                    // We copy over the permutation we just took from the queue to the new
                    // permutation.
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);
                    // We add the current number to the spot at 'j'.
                    // 'j' varies from 0 to the size of the list we just took from the queue.
                    newPermutation.add(j, currentNumber);
                    // If the new permutation we just made is the same length as the input array.
                    // We know it's a valid permutation.
                    if (newPermutation.size() == nums.length) {
                        // So, we add it to the results.
                        result.add(newPermutation);
                    } else {
                        // Otherwise, we add it to the queue for further processing with the next
                        // number in our input array.
                        permutations.add(newPermutation);
                    }
                }
            }
        }

        // Return result list.
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findPermutations(new int[] { 1, 3, 5 });
        System.out.println("Here are all the permutations: " + result);
    }
}
