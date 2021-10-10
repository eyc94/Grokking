/*
--- PROBLEM CHALLENGE 4 ---
Words Concatenation [HARD]

Given a string and a list of words, find all the starting indices of substrings in the
given string that are a concatenation of all the given words exactly once without any
overlapping of words. It is given that all words are of the same length.

--- EXAMPLES ---

- EX 1 -
Input:          String = "catfoxcat", Words = ["cat", "fox"]
Output:         [0, 3]
Explanation:    The two substring containing both the words are "catfox" & "foxcat".

- EX 2 -
Input:          String = "catcatfoxfox", Words = ["cat", "fox"]
Output:         [3]
Explanation:    The only substring containing both the words is "catfox".
*/

import java.util.*;

public class WordsConcatenation {
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        HashMap<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        List<Integer> resultIndices = new ArrayList<>();
        int wordsCount = words.length;
        int wordLength = words[0].length();

        for (int i = 0; i <= str.length() - wordsCount * wordLength; i++) {
            HashMap<String, Integer> wordsSeen = new HashMap<>();
            for (int j = 0; j < wordsCount; j++) {
                int nextWordIndex = i + j * wordLength;

                String word = str.substring(nextWordIndex, nextWordIndex + wordLength);
                if (!wordFreq.containsKey(word)) {
                    break;
                }

                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1);

                if (wordsSeen.get(word) > wordFreq.getOrDefault(word, 0)) {
                    break;
                }

                if (j + 1 == wordsCount) {
                    resultIndices.add(i);
                }
            }
        }

        return resultIndices;
    }

    public static void main(String[] args) {
        // Sample string.
        String s1 = "catfoxcat";
        String s2 = "catcatfoxfox";

        // Sample words.
        String[] w1 = new String[] { "cat", "fox" };

        // Calculate results.
        List<Integer> r1 = findWordConcatenation(s1, w1);
        List<Integer> r2 = findWordConcatenation(s2, w1);

        // Print results.
        System.out.println(
                "The starting indices of " + s1 + " that contain the words in " + Arrays.toString(w1) + " is " + r1);
        System.out.println(
                "The starting indices of " + s2 + " that contain the words in " + Arrays.toString(w1) + " is " + r2);
    }
}
