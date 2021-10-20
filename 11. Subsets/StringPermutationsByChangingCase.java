/*
String Permutations By Changing Case [MEDIUM]

Given a string, find all of its permutations preserving the character sequence
but changing case.

--- EXAMPLES ---

- EX 1 -
Input:      "ad52"
Output:     "ad52", "Ad52", "aD52", "AD52"

- EX 2 -
Input:      "ab7c"
Output:     "ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
*/

import java.util.*;

public class StringPermutationsByChangingCase {
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();

        return permutations;
    }

    public static void main(String[] args) {
        List<String> result = findLetterCaseStringPermutations("ad52");
        System.out.println("String permutations are: " + result);

        result = findLetterCaseStringPermutations("ab7c");
        System.out.println("String permutations are: " + result);
    }
}
