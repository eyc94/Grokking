/*
--- PROBLEM CHALLENGE 1 ---
Permutation In A String [HARD]

Given a string and a pattern, find out if the string contains any permutation of the pattern.

Permutation is defined as the re-arranging of the characters of the string. For example, "abc"
has the following six permutations:

    1. abc
    2. acb
    3. bac
    4. bca
    5. cab
    6. cba

If a string has 'n' distinct characters it will have n! permutations.

--- EXAMPLES ---

- EX 1 -
Input:          String = "oidbcaf", Pattern = "abc"
Output:         true
Explanation:    The string contains "bca" which is a permutation of the given pattern.

- EX 2 -
Input:          String = "odicf", Pattern = "dc"
Output:         false
Explanation:    No permutation of the pattern is present in the given string as a substring.

- EX 3 -
Input:          String = "bcdxabcdy", Pattern = "bcdyabcdx"
Output:         true
Explanation:    Both the string and the pattern are a permutation of each other.

- EX 4 -
Input:          String = "aaacb", Pattern = "abc"
Output:         true
Explanation:    The string contains "acb" which is a permutation of the given pattern.
*/

import java.util.*;

public class PermutationInAString {

    public static boolean findPermutation(String str, String pattern) {
        HashMap<Character, Integer> charFreq = new HashMap<>();
        for (char letters : pattern.toCharArray()) {
            charFreq.put(letters, charFreq.getOrDefault(letters, 0) + 1);
        }

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

            if (matched == charFreq.size()) {
                return true;
            }

            if (windowEnd >= pattern.length() - 1) {
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
        return false;
    }

    public static void main(String[] args) {
        // Sample strings.
        String s1 = "oidbcaf";
        String s2 = "odicf";
        String s3 = "bcdxabcdy";
        String s4 = "aaacb";

        // Calculate results.
        boolean r1 = findPermutation(s1, "abc");
        boolean r2 = findPermutation(s2, "dc");
        boolean r3 = findPermutation(s3, "bcdyabcdx");
        boolean r4 = findPermutation(s4, "abc");

        // Print results.
        System.out.println("The string " + s1 + " contains the permutation 'abc': " + r1);
        System.out.println("The string " + s2 + " contains the permutation 'dc': " + r2);
        System.out.println("The string " + s3 + " contains the permutation 'bcdyabcdx': " + r3);
        System.out.println("The string " + s4 + " contains the permutation 'abc': " + r4);
    }
}
