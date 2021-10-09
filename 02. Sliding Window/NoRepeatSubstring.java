/*
No Repeat Substring [HARD]

Given a string, find the length of the longest substring which has no repeating characters.

--- EXAMPLES ---

- EX 1 -
Input:          String = "aabccbb"
Output:         3
Explanation:    The longest substring without any repeating characters is "abc".

- EX 2 -
Input:          String = "abbbb"
Output:         2
Explanation:    The longest substring without any repeating characters is "ab".

- EX 3 -
Input:          String = "abccde"
Output:         3
Explanation:    The longest substrings without any repeating characters are "abc" & "cde".
*/

import java.util.HashMap;

public class NoRepeatSubstring {

    /*
     * So there are two ways. Both are efficient but one is better than the other. I
     * thought of solution 1.
     * 
     * Solution 1:
     * 
     * We do what we normally did. We expand our window and use a hash map to map
     * characters to their frequencies. If the frequency of our right character is
     * 2, this means that the character is repeating and we need to make sure it's
     * not repeating.
     * 
     * To do this, we shrink our window and decrement frequencies of characters from
     * the left end. Once our right character's frequency is back to 1, we can start
     * expanding again from the right end.
     * 
     * Although this solution is good, we have to linearly move the left end to find
     * the new index for windowStart. This new index will keep moving right until
     * the right character frequency is 1.
     * 
     * Solution 2 (Better):
     * 
     * A better solution is to use the hash map to map characters to their index in
     * our string.
     * 
     * We expand our window and we add the right character to our map along with its
     * index in the string. As soon as we encounter a character that is already in
     * the hash map, we need to update our windowStart so that there is only one
     * occurrence of the right character.
     * 
     * To do this, we update our windowStart. If windowStart is already ahead of the
     * last index of 'rightChar', we keep windowStart. If not, we go one position
     * after rightChar.
     * 
     * Update the new rightChar's index and continue.
     * 
     * Time Complexity: O(N) where N is the length of our string.
     * 
     * Space Complexity: O(K) where K is the number of distinct characters in the
     * input string. This means K <= N because in the worst case, the whole string
     * might not have any repeating characters, so entire string is in hash map. We
     * can also say that there is a fixed character set of 26 letters. So, we can
     * say its fixed space of O(1). We can use a fixed array size instead of a hash
     * map.
     */

    public static int findLength(String str) {
        // This hash map maps characters to their index in the string.
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        // This keeps track of the longest substring with no repeating characters so
        // far.
        int longestSubstring = 0;
        // This is the left end of our sliding window.
        int windowStart = 0;

        // This is the part where we expand the right end of our sliding window.
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // Get the right character of our sliding window.
            char rightChar = str.charAt(windowEnd);
            // If our sliding window already has the right character.
            // We need to shrink window so that we only have 1 of that repeating character.
            if (charIndexMap.containsKey(rightChar)) {
                // Tricky (2 situations):
                // 1. If windowStart is already greater than last index of repeating character,
                // keep window start.
                // 2. If the last index of repeating character is greater than windowStart, go
                // one index after that previous index.
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            }
            // Update the index of the right character to that of the right end.
            charIndexMap.put(rightChar, windowEnd);
            // Update longest substring with no repeating characters.
            longestSubstring = Math.max(longestSubstring, windowEnd - windowStart + 1);
        }
        return longestSubstring;
    }

    public static void main(String[] args) {
        // Sample strings.
        String s1 = "aabccbb";
        String s2 = "abbbb";
        String s3 = "abccde";

        // Calculate results.
        int r1 = findLength(s1);
        int r2 = findLength(s2);
        int r3 = findLength(s3);

        // Print results.
        System.out.println("The longest substring with no repeating characters of " + s1 + " is: " + r1);
        System.out.println("The longest substring with no repeating characters of " + s2 + " is: " + r2);
        System.out.println("The longest substring with no repeating characters of " + s3 + " is: " + r3);
    }
}
