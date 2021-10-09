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
