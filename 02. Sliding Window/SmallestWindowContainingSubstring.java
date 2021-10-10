/*
--- PROBLEM CHALLENGE 3 ---
Smallest Window Containing Substring [HARD]

Given a string and a pattern, find the smallest substring in the given string which
has all the characters of the given pattern.

--- EXAMPLES ---

- EX 1 -
Input:          String = "aabdec", Pattern = "abc"
Output:         "abdec"
Explanation:    The smallest substring having all characters of the pattern is "abdec".

- EX 2 -
Input:          String = "abdabca", Pattern = "abc"
Output:         "abc"
Explanation:    The smallest substring having all characters of the pattern is "abc".

- EX 3 -
Input:          String = "abcad", Pattern = "abc"
Output:         ""
Explanation:    No substring in the given string has all characters of the pattern.
*/

import java.util.*;

public class SmallestWindowContainingSubstring {
    public static String findSubstring(String str, String pattern) {
        // We use a hash map to map all characters of our pattern to its frequencies.
        HashMap<Character, Integer> charFreq = new HashMap<>();
        for (char letter : pattern.toCharArray()) {
            charFreq.put(letter, charFreq.getOrDefault(letter, 0) + 1);
        }

        // This is to keep track of our substring to return to the user.
        // Initialized to 0s for both.
        int subStringStart = 0;
        int subStringEnd = 0;

        // This is to track how many matched characters there are.
        int matched = 0;
        // This is the left end of our sliding window.
        int windowStart = 0;
        // This is the expanding right end of our sliding window.
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // Grab the right character.
            char rightChar = str.charAt(windowEnd);
            // If this character is part of our pattern.
            if (charFreq.containsKey(rightChar)) {
                // Decrement the frequency in the map.
                charFreq.put(rightChar, charFreq.get(rightChar) - 1);
                // If the frequency reaches 0, we matched our letter. So, increase matched.
                if (charFreq.get(rightChar) == 0) {
                    matched++;
                }
            }

            // This is to account for shrinking.
            // When we have matched all letters in our map, we need to ask ourselves:
            // Can we shrink our substring and still have the requirements?
            while (matched == charFreq.size()) {
                // First set the substring start and end to the current window boundaries.
                subStringStart = windowStart;
                subStringEnd = windowEnd;
                // Grab the left character.
                char leftChar = str.charAt(windowStart);
                // If the left character is a pattern.
                if (charFreq.containsKey(leftChar)) {
                    // Check first to see if this is the last of its letter to be removed.
                    // If it is, decrement matched because it'll be gone and there will be no more
                    // of it.
                    if (charFreq.get(leftChar) == 0) {
                        matched--;
                    }
                    // Increment the frequency.
                    charFreq.put(leftChar, charFreq.get(leftChar) + 1);
                }
                // Shrink the window from the left.
                windowStart++;
            }
        }

        // In the end, if our substring indices do not change, we return an empty
        // string. This is because we couldn't find a substring.
        if (subStringStart == 0 && subStringEnd == 0) {
            return "";
        }

        // Return substring.
        return str.substring(subStringStart, subStringEnd + 1);
    }

    public static void main(String[] args) {
        // Sample strings.
        String s1 = "aabdec";
        String s2 = "abdabca";
        String s3 = "adcad";

        // Calculate results.
        String r1 = findSubstring(s1, "abc");
        String r2 = findSubstring(s2, "abc");
        String r3 = findSubstring(s3, "abc");

        // Print results.
        System.out.println("The smallest substring of " + s1 + " with the pattern 'abc' is: " + r1);
        System.out.println("The smallest substring of " + s2 + " with the pattern 'abc' is: " + r2);
        System.out.println("The smallest substring of " + s3 + " with the pattern 'abc' is: " + r3);
    }
}
