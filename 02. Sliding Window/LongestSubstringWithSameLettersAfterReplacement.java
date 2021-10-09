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
    public static int findLength(String str, int k) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        int longestLength = 0;
        int mostCharFreq = 0;

        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            charMap.put(rightChar, charMap.getOrDefault(rightChar, 0) + 1);
            mostCharFreq = Math.max(mostCharFreq, charMap.get(rightChar));

            while (windowEnd - windowStart + 1 - mostCharFreq > k) {
                char leftChar = str.charAt(windowStart);
                charMap.put(leftChar, charMap.get(leftChar) - 1);
                windowStart++;
            }

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
