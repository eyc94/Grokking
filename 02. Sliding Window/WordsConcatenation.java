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

    /*
     * This code is a little tricky. I'll go over each step.
     * 
     * First, we create a hash map that keeps track of how many words there are in
     * our words array. We map our words to their frequency.
     * 
     * We create a list to hold resultant indices to return to the user. This is
     * done with an ArrayList implementation.
     * 
     * We get the word count and word length. We know that each word has the same
     * length inside our words array. We need these both because we have to find out
     * where in our iteration we need to stop. If we multiply both the count of the
     * words and the lengths, we get total letters as a result of a concatenation of
     * all words in our words array. If we subtract our string length by the total
     * letters of concatenation, we get the last index to work on in our iteration.
     * This is where 'i' will go to last. We do this to make room for the last
     * iteration. The last iteration needs to accomodate room for (total letters of
     * concatenation) or else we get index out of bounds.
     * 
     * While in our 'i' iterations, we create a new hash map. This new hash map
     * serves to keep track of the words seen so far in our current iteration.
     * 
     * We now create a second pointer 'j' to scan the words from 'i'. Instead of
     * linearly scanning, we only iterate 'j' to the number of words in our array.
     * This is because we will be "jumping" to the start of the next word to check.
     * In order to jump, we find the index of the word. To find this index, we
     * multiply 'k' by the word length. This gives the multiple of the word length
     * to add to 'i' to get the next word index.
     * 
     * For example, if there are 3 words, 'j' takes on values 0, 1, and 2. If each
     * word is of length 3, the multiples are 0, 3, and 6. If our input string has
     * length 12, 'i' iterates to 12 - 9 = 3. 'i' is 0, 1, 2, and 3. If 'i' is on 1,
     * then the next word indices take on values of 1, 4, and 7. These indices
     * represent the starting indices of the 3 words.
     * 
     * Now that we have gotten the next word index, we need the word that starts at
     * that index. To do this, we use the substring method. The first parameter is
     * the index we just calculated. The second parameter is the index at wordLength
     * apart.
     * 
     * Now that we have gotten the word, check if it is in the list of words. This
     * is because we need to see if it is part of the concatenation. IF the word IS
     * NOT is the array of words, we BREAK immediately from the current 'j'
     * iteration. Move to the next 'j' value. This is because this current word does
     * not exist and will not be part of the solution.
     * 
     * Otherwise, we place the word in our seen hash map that we recently created.
     * We increment the seen counter as well.
     * 
     * NOW, we check to see if we have seen the same word MORE than what we CAN see.
     * If our words array only holds 1 frequency of the word, but the seen map shows
     * that we encountered 2 in our iteration, this means that we have too many of
     * that word. We BREAK immediately.
     * 
     * The last condition is if j + 1 == wordCount. If we make it this far and this
     * evaluates to true, this means we have gone through all of 'j' and the
     * concatenated words starting at 'i' belongs to the results list. Add 'i'.
     * 
     * Time Complexity: O(N * M * Len) where N is the length of our input string, M
     * is the number of words, and Len is the length of each word.
     * 
     * Space Complexity: O(M) because we store all words in hash map. Can also have
     * O(N) in the worst case for resulting list. The resulting complexity is O(M +
     * N).
     */

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
