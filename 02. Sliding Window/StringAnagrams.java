/*
--- PROBLEM CHALLENGE 2 ---
String Anagrams [HARD]

Given a string and a pattern, find all anagrams of the pattern in the given string.

Anagram is actually a Permutation of a string. For example, "abc" has the following size
anagrams:

    1. abc
    2. acb
    3. bac
    4. bca
    5. cab
    6. cba

Write a function to return a list of starting indices of the anagrams of the pattern in
the given string.

--- EXAMPLES ---

- EX 1 -
Input:          String = "ppqp", Pattern = "pq"
Output:         [1, 2]
Explanation:    The two anagrams of the pattern in the given string are "pq" and "qp".

- EX 2 -
Input:          String = "abbcabc", Pattern = "abc"
Output:         [2, 3, 4]
Explanation:    The three anagrams of the pattern in the given string are "bca", "cab",
                and "abc".
*/

import java.util.*;

public class StringAnagrams {
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<>();
        HashMap<Character, Integer> charFreq = new HashMap<>();
        for (char letter : pattern.toCharArray()) {
            charFreq.put(letter, charFreq.getOrDefault(letter, 0) + 1);
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
                // Add index of windowStart to result indices.
                resultIndices.add(windowStart);
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

        return resultIndices;
    }

    public static void main(String[] args) {
        // Sample strings.
        String s1 = "ppqp";
        String s2 = "abbcabc";

        // Calculate results.
        List<Integer> r1 = findStringAnagrams(s1, "pq");
        List<Integer> r2 = findStringAnagrams(s2, "abc");

        // Print results.
        System.out.println(
                "The anagrams of the pattern 'pq' appear in string " + s1 + " at indices: " + Arrays.asList(r1));
        System.out.println(
                "The anagrams of the pattern 'abc' appear in string " + s2 + " at indices: " + Arrays.asList(r2));
    }
}
