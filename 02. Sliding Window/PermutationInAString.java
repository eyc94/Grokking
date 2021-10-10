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
        // We create a hash map that maps characters to its frequency for the string
        // "pattern". This is how we keep track of the characters used in our sliding
        // window.
        HashMap<Character, Integer> charFreq = new HashMap<>();
        for (char letters : pattern.toCharArray()) {
            charFreq.put(letters, charFreq.getOrDefault(letters, 0) + 1);
        }

        // This keeps track of whether we matched a letter from the pattern. Whether
        // there is more than one letter of the same letter does not matter. If the
        // frequency of a letter reaches 0, the letter is matched no matter what.
        int matched = 0;
        // This is the left-end of our window.
        int windowStart = 0;
        // We expand the sliding window from the right end.
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // Grab the right character from the right end and add it to the window.
            char rightChar = str.charAt(windowEnd);
            // If the map contains the character.
            if (charFreq.containsKey(rightChar)) {
                // Decrement the character's frequency.
                charFreq.put(rightChar, charFreq.get(rightChar) - 1);
                // After decrementing, if the frequency reaches 0, we know we have matched one
                // character.
                if (charFreq.get(rightChar) == 0) {
                    matched++;
                }
            }

            // If at any time the matched variable equals the map's size, we return true.
            // This is because we have matched all characters in our current sliding window.
            if (matched == charFreq.size()) {
                return true;
            }

            // We need to shrink the window as soon as our window reaches the length of the
            // pattern. Our condition below accounts for the subsequent indices that our
            // right end take. So, our window will always be either the size of the pattern
            // or 1 plus the size of the pattern.
            if (windowEnd >= pattern.length() - 1) {
                // Grab the left character.
                char leftChar = str.charAt(windowStart);
                // If the left character is in the map.
                if (charFreq.containsKey(leftChar)) {
                    // BEFORE we increment the frequency, check the current frequency.
                    // If it is 0, we know that after incrementing will be 1, and our window will no
                    // longer have the matched character. So, we decrement matched variable.
                    if (charFreq.get(leftChar) == 0) {
                        matched--;
                    }
                    // Increment the frequency of the left character.
                    charFreq.put(leftChar, charFreq.get(leftChar) + 1);
                }
                // Shrink the window from the left end.
                windowStart++;
            }
        }
        // Return false if all else fails.
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
