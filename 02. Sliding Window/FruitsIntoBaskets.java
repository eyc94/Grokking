/*
Fruits Into Baskets [MEDIUM]

Given an array of characters where each character represents a fruit tree, you are given two
baskets and your goal is to put maximum number of fruits in each basket. The only restriction
is that each basket can have only one type of fruit.

You can start with any tree, but once you have started you can't skip a tree. You will pick
one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a
third fruit type.

Write a function to return the maximum number of fruits in both the baskets.

--- EXAMPLES ---

- EX 1 -
Input:          Fruit = ['A', 'B', 'C', 'A', 'C']
Output:         3
Explanation:    We can put 2 'C' in one basket and one 'A' in the other from the subarray
                ['C', 'A', 'C'].

- EX 2 -
Input:          Fruit = ['A', 'B', 'C', 'B', 'B', 'C']
Output:         5
Explanation:    We can put 3 'B' in one basket and two 'C' in the other basket. This can be
                done if we start with the second letter ['B', 'C', 'B', 'B', 'C'].
*/

import java.util.HashMap;
import java.util.Arrays;

public class FruitsIntoBaskets {

    /*
     * This solution uses a dynamic sliding window that expands and shrinks based on
     * one condition. This condition is that there should only be 2 types of fruits.
     * We will use a hash map to determine this because our hash map's size will
     * tell us how many unique fruits we have. The size must be <= 2 at all times.
     * 
     * We expand our window and as soon as we reach a map size greater than 2, we
     * will proceed to shrink our window until the size is <= 2. Once our hash map
     * meets the requirement that the size is <= 2, we can now update the max fruits
     * as needed.
     * 
     * Time Complexity: O(N) where N is the length of our character input array. The
     * outer loop runs for all characters and the inner while loop processes each
     * character only once. The time complexity is O(N + N) which is O(N)
     * asymptotically.
     * 
     * Space Complexity: O(1) because our hash map will only hold max 3 characters
     * regardless of how big our input array is.
     */

    public static int findLength(char[] arr) {
        // This hash map keeps track of the character frequencies in our sliding window.
        HashMap<Character, Integer> charFreq = new HashMap<>();
        // This keeps track of the max fruits in basket so far.
        int maxFruits = 0;
        // This is the left end of our sliding window.
        int windowStart = 0;
        // We expand our window to the right from the right end.
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Grab the right character.
            char rightChar = arr[windowEnd];
            // Add to map as new or increment frequency.
            charFreq.put(rightChar, charFreq.getOrDefault(rightChar, 0) + 1);

            // Shrink our window if needed. The map size represents the unique fruits.
            // Number of baskets should be 2, so size cannot exceed 2.
            while (charFreq.size() > 2) {
                // Get the left character.
                char leftChar = arr[windowStart];
                // Decrement the frequency of the left character.
                charFreq.put(leftChar, charFreq.get(leftChar) - 1);
                // If the frequency of the decremented character is 0, remove it.
                // The size of the hash map determines if we went over 2 baskets.
                if (charFreq.get(leftChar) == 0) {
                    charFreq.remove(leftChar);
                }
                // Shrink our window from the left end.
                windowStart++;
            }
            // Update the max number of fruits in our basket now that it fulfills our
            // requirements.
            maxFruits = Math.max(maxFruits, windowEnd - windowStart + 1);
        }
        return maxFruits;
    }

    public static void main(String[] args) {
        // Sample arrays.
        char[] s1 = new char[] { 'A', 'B', 'C', 'A', 'C' };
        char[] s2 = new char[] { 'A', 'B', 'C', 'B', 'B', 'C' };

        // Calculate results.
        int r1 = findLength(s1);
        int r2 = findLength(s2);

        // Print results.
        System.out.println("Max number of fruits for array " + Arrays.toString(s1) + " is: " + r1);
        System.out.println("Max number of fruits for array " + Arrays.toString(s2) + " is: " + r2);
    }
}
