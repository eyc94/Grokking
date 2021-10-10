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
        // Create a hash map to keep track of words frequency.
        HashMap<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Make a list to return the indices in our string that the words start with.
        List<Integer> resultIndices = new ArrayList<>();
        // Count the number of words in our array of words.
        int wordsCount = words.length;
        // Get the word length of our words. All words have the same length.
        int wordLength = words[0].length();

        // Iterate up to length - (total letters in words array).
        // This is because the concatenation of all words in our words array ==
        // (wordsCount * wordLength).
        // We need enough room for the letters.
        for (int i = 0; i <= str.length() - wordsCount * wordLength; i++) {
            // Create a hash map on each iteration to keep track of words seen.
            // This maps the words to its frequency in our iteration.
            HashMap<String, Integer> wordsSeen = new HashMap<>();
            // We iterate to the word count.
            for (int j = 0; j < wordsCount; j++) {
                // Calculate the index of the next word to process.
                // Basically it is every (wordLength)th character.
                // Depending on word count, multiplying 'j' by wordLength always gives us the
                // multiple to add to 'i' that brings us to our next word index.
                // In this case, there are 2 words each of length 3.
                // So, 'j' can only take on values of 0 and 1. We multiply the wordLength (3) to
                // both values to get 0 and 3. These are the spacings between the two words we
                // mentioned.
                // Add these to each iteration counter 'i' to find the next word index.
                int nextWordIndex = i + j * wordLength;

                // We grab the substring of the current word by using the substring method.
                // Use nextWordIndex and nextWordIndex + wordLength as boundaries.
                String word = str.substring(nextWordIndex, nextWordIndex + wordLength);

                // If our current word is NOT in our hash map, this means this word is NOT a
                // part of the pattern. SO, we can break from the current 'j' iteration.
                if (!wordFreq.containsKey(word)) {
                    break;
                }

                // Otherwise, we place the word in the seen hash map and increment seen counter.
                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1);

                // After incrementing the frequency. If we have more of that word than are
                // allowed in our words pattern, break from 'j' iteration and move on.
                if (wordsSeen.get(word) > wordFreq.getOrDefault(word, 0)) {
                    break;
                }

                // If we make it to the last iteration of 'j' and everything is successful and
                // didn't break, add the 'i' as an index to the result indices list.
                if (j + 1 == wordsCount) {
                    resultIndices.add(i);
                }
            }
        }
        // Return the result indices.
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
