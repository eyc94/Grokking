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
        HashMap<Character, Integer> charFreq = new HashMap<>();
        for (char letter : pattern.toCharArray()) {
            charFreq.put(letter, charFreq.getOrDefault(letter, 0) + 1);
        }

        int subStringStart = 0;
        int subStringEnd = 0;

        int matched = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFreq.containsKey(rightChar)) {
                charFreq.put(rightChar, charFreq.get(rightChar) - 1);
                if (charFreq.get(rightChar) == 0) {
                    matched++;
                }
            }

            while (matched == charFreq.size()) {
                subStringStart = windowStart;
                subStringEnd = windowEnd;
                char leftChar = str.charAt(windowStart);
                if (charFreq.containsKey(leftChar)) {
                    if (charFreq.get(leftChar) == 0) {
                        matched--;
                    }
                    charFreq.put(leftChar, charFreq.get(leftChar) + 1);
                }
                windowStart++;
            }
        }
        if (subStringStart == 0 && subStringEnd == 0) {
            return "";
        }
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
