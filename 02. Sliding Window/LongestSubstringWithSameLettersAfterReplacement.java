/*
Longest Substring With Same Letters After Replacement [HARD]

Given a string with lowercase letters only, if you are allowed to replace no more than 'k'
letters with any letter, find the length of the longest substring having the same letters
after replacement.

--- EXAMPLES ---

- EX 1 -
Input:          String = "aabccbb", k = 2
Output:         5
Explanation:    Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".

- EX 2 -
Input:          String = "abbcb", k = 1
Output:         4
Explanation:    Replace the 'c' with 'b' to have a longest repeating substring "bbbb".

- EX 3 -
Input:          String = "abccde", k = 1
Output:         3
Explanation:    Replace the 'b' or 'd' with 'c' to have the longest repeating substring "ccc".
*/

import java.util.HashMap;

public class LongestSubstringWithSameLettersAfterReplacement {

    /*
     * This utilizes sliding window by following one requirement. The requirement is
     * that we need a substring that has repeating characters after 'k'
     * replacements. This string needs to be the longest possible. To achieve this,
     * we use a hash map that keeps track of the frequency of the characters inside
     * our substring.
     * 
     * We keep track of the most frequent character's frequency. This is because our
     * longest substring will have these repeating characters plus 'k' because we
     * can replace any 'k' letters along with the most repeated letters. So, we
     * constantly check the right character's frequency, as we expand, to see if
     * this value changes over time.
     * 
     * So, when our window is too large, that is (when length of window - most
     * repeated characters > k), we need to shrink our window until it is <= k. To
     * shrink our window, we do what we normally did. Grab the left character and
     * decrement its frequency.
     * 
     * Now, our window fulfills the requirements. We update the longest length.
     * 
     * Time Complexity: O(N) where N is the length of our input string.
     * 
     * Space Complexity: O(1). This is because our hash map will, in the worst case,
     * hold 26 letters. All lowercase only. This is asymptotically O(1).
     */

    public static int findLength(String str, int k) {
        // This is our hash map that maps characters to their frequency in our sliding
        // window.
        HashMap<Character, Integer> charMap = new HashMap<>();
        // This is the length of the longest substring with repeating characters after k
        // replacements.
        int longestLength = 0;
        // This keeps track of the character that occurs the most frequently.
        // If we subtract this value from the length of our current window, that is how
        // many characters that need replacing. We compare this value later to how many
        // replacements we can make.
        int mostCharFreq = 0;

        // This is the left end of the sliding window.
        int windowStart = 0;
        // We expand our window from the right end.
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // Grab the right character in our sliding window.
            char rightChar = str.charAt(windowEnd);
            // Update the frequency of that character as it goes in our window.
            charMap.put(rightChar, charMap.getOrDefault(rightChar, 0) + 1);
            // Update the most frequent character if this character is the most frequent
            // now.
            mostCharFreq = Math.max(mostCharFreq, charMap.get(rightChar));

            // Run these statements if the number of characters that are not repeating are
            // greater than the number of replacements allowed. If so, we need to shrink our
            // window.
            while (windowEnd - windowStart + 1 - mostCharFreq > k) {
                // Shrink our window by grabbing the left end character.
                char leftChar = str.charAt(windowStart);
                // Decrement the frequency in our hash map.
                charMap.put(leftChar, charMap.get(leftChar) - 1);
                // Shrink the window by moving left end to the right.
                windowStart++;
            }

            // Everything fits the requirement, so update the longest length possible.
            longestLength = Math.max(longestLength, windowEnd - windowStart + 1);
        }
        return longestLength;
    }

    public static void main(String[] args) {
        // Sample strings.
        String s1 = "aabccbb";
        String s2 = "abbcb";
        String s3 = "abccde";

        // Calculate results.
        int r1 = findLength(s1, 2);
        int r2 = findLength(s2, 1);
        int r3 = findLength(s3, 1);

        // Print results.
        System.out.println("The longest substring with repeating characters with no more than " + 2
                + " replacements of string " + s1 + " is: " + r1);
        System.out.println("The longest substring with repeating characters with no more than " + 1
                + " replacements of string " + s2 + " is: " + r2);
        System.out.println("The longest substring with repeating characters with no more than " + 1
                + " replacements of string " + s3 + " is: " + r3);
    }
}
