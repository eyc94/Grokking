/*
Longest Substring With K Distinct Characters [MEDIUM]

Given a string, find the length of the longest substring in it with no more than
K distinct characters.

--- EXAMPLES ---

- EX 1 -
Input:          String = "araaci", K = 2
Output:         4
Explanation:    The longest substring with no more than '2' distinct characters
                is "araa".

- EX 2 -
Input:          String = "araaci", K = 1
Output:         2
Explanation:    The longest substring with no more than '1' distinct characters
                is "aa".

- EX 3 -
Input:          String = "cbbebi", K = 3
Output:         5
Explanation:    The longest substrings with no more than '3' distinct characters
                are "cbbeb" & "bbebi".
*/

import java.util.HashMap;

public class LongestSubstringWithKDistinctCharacters {

    /*
     * We use the same sliding window approach. There is no fixed size of the
     * sliding window. We expand and shrink based on a requirement. This requirement
     * is that the substring can have no more than 'k' distinct characters. We have
     * a hash map that keeps track of the character frequencies in our substring.
     * The size of the hash map tells us the number of distinct characters in the
     * substring.
     * 
     * If the map size reaches greater than 'k', we need to shrink our hash map. To
     * shrink our hash map, we decrement frequency of left character. As soon as a
     * character's frequency hits 0, this means our substring no longer has that
     * specific character in it. We can then remove the character from the hash map.
     * The size, then, will decrease.
     * 
     * Once our window meets the requirements, we calculate the length and update
     * the longest length if needed.
     * 
     * Time Complexity: O(N) where N is the length of our string. The outer 'for'
     * loops runs for all characters in our string. The inner while loop processes
     * each character only once. The time complexity is O(N + N), which is O(N)
     * asymptotically.
     * 
     * Space Complexity: O(k) where k is the number of distinct characters allowed.
     * The max we will store is k + 1 because we need a way to detect if our size is
     * bigger than the max number of distinct characters allowed.
     */

    public static int findLength(String str, int k) {
        // Throw error if null string, empty string, or if string is less than unique
        // characters allowed.
        if (str == null || str.length() == 0 || str.length() < k) {
            throw new IllegalArgumentException();
        }

        // Hash map that keeps track of frequency of characters in our substring.
        // Size of map represents the number of unique characters in our substring.
        HashMap<Character, Integer> charFreq = new HashMap<>();
        // Keep track of the longest substring with no more than 'k' distinct characters
        // to return to user.
        int longestSubstring = 0;

        // Make window left end.
        int windowStart = 0;

        // Expand window right end.
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // Grab the right character.
            char rightChar = str.charAt(windowEnd);
            // Increment frequency of character in hash map or add new.
            charFreq.put(rightChar, charFreq.getOrDefault(rightChar, 0) + 1);

            // Shrink our substring if there are more unique characters than there are
            // allowed. Keep doing this in a while loop.
            while (charFreq.size() > k) {
                // Grab the left character (character to leave substring).
                char leftChar = str.charAt(windowStart);
                // Decrement the frequency of the left character.
                charFreq.put(leftChar, charFreq.get(leftChar) - 1);
                // If frequency of the decremented character goes to 0, we remove it.
                // This handles the size decrease to get out of the while loop.
                if (charFreq.get(leftChar) == 0) {
                    charFreq.remove(leftChar);
                }
                // Shrink our window from the left.
                windowStart++;
            }

            // Update the longest substring with no more than 'k' distinct characters.
            // At this point, we have a valid substring that satisfies the distinct
            // character requirements.
            longestSubstring = Math.max(longestSubstring, windowEnd - windowStart + 1);
        }
        return longestSubstring;
    }

    public static void main(String[] args) {
        // Sample strings.
        String s1 = "araaci";
        String s2 = "cbbebi";

        // Calculate results.
        int r1 = findLength(s1, 2);
        int r2 = findLength(s1, 1);
        int r3 = findLength(s2, 3);

        // Print results.
        System.out.println("Longest substring of " + s1 + " with no more than " + 2 + " distinct characters is: " + r1);
        System.out.println("Longest substring of " + s1 + " with no more than " + 1 + " distinct characters is: " + r2);
        System.out.println("Longest substring of " + s2 + " with no more than " + 3 + " distinct characters is: " + r3);
    }
}