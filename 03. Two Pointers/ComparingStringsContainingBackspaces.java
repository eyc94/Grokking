/*
--- PROBLEM CHALLENGE 2 ---
Comparing Strings Containing Backspaces [MEDIUM]

Given two strings containing backspaces (identified by the character '#'), check if
the two strings are equal.

--- EXAMPLES ---

- EX 1 -
Input:          str1 = "xy#z", str2 = "xzz#"
Output:         true
Explanation:    After applying backspaces the strings become "xz" and "xz" respectively.

- EX 2 -
Input:          str1 = "xy#z", str2 = "xyz#"
Output:         false
Explanation:    After applying backspaces the strings become "xz" and "xy" respectively.

- EX 3 -
Input:          str1 = "xp#", str2 = "xyz##"
Output:         true
Explanation:    After applying backspaces the strings become "x" and "x" respectively. In
                "xyz##", the first '#' removes the 'z' and the second '#' removes the 'y'.

- EX 4 -
Input:          str1 = "xywrrmp", str2 = "xywrrmu#p"
Output:         true
Explanation:    After applying backspaces the strings become "xywrrmp" and "xywrrmp" respectively.
*/

public class ComparingStringsContainingBackspaces {

    /*
     * This is the two pointer approach. Only difference here is that we are putting
     * one pointer on both strings.
     * 
     * We iterate from the back of the string. We find the next valid character
     * after backspacing that we can compare. Once we do, we compare the characters
     * at that index. The code is pretty straightforward.
     * 
     * Time Complexity: O(N + M) where N is the length of str1 and M is the length
     * of str2.
     * 
     * Space Complexity: O(1).
     */

    public static boolean compareV2(String str1, String str2) {
        // We iterate two pointers from the ends of both strings.
        // We will land on the next valid character to compare.
        // These characters should be equal at every iteration.
        int pointerOne = str1.length() - 1;
        int pointerTwo = str2.length() - 1;
        // Iterate while the two pointers are not -1.
        while (pointerOne >= 0 || pointerTwo >= 0) {
            // Get the next valid character index.
            // This takes us to a helper function that processes the backspaces.
            // It brings back the index of the character that we can compare for both
            // strings 1 and 2.
            int i1 = getNextValidCharIndex(str1, pointerOne);
            int i2 = getNextValidCharIndex(str2, pointerTwo);

            // If both indices reach -1, this means that the last character (index 0) was
            // backspaced. As in when we reach index 0, and we have 1 backspace left, the
            // function returns -1 because there is no next valid character. This means both
            // are equal.
            if (i1 < 0 && i2 < 0) {
                return true;
            }

            // If only one of the strings reach -1 and the other string still has a
            // character to process, this means they are not equal after backspaces. This
            // usually happens in uneven string lengths or strings with more backspaces than
            // the other.
            if (i1 < 0 || i2 < 0) {
                return false;
            }

            // Once we reach a valid character, if both characters in both strings are NOT
            // equal, this means the strings are NOT equal after backspaces.
            if (str1.charAt(i1) != str2.charAt(i2)) {
                return false;
            }

            // Point the two pointers behind the valid index for next processing.
            pointerOne = i1 - 1;
            pointerTwo = i2 - 1;
        }

        // If we reach this point, it means that the last character (index 0) was not
        // backspaced. We just ran out of room for both, meaning it's valid.
        return true;
    }

    // This is the helper function. We count backspaces and decrement index and
    // backspace count as needed.
    public static int getNextValidCharIndex(String str, int index) {
        int backspaceCount = 0;
        while (index >= 0) {
            if (str.charAt(index) == '#') {
                backspaceCount++;
            } else if (backspaceCount > 0) {
                backspaceCount--;
            } else {
                break;
            }
            index--;
        }
        return index;
    }

    public static boolean compare(String str1, String str2) {
        StringBuilder stringOne = new StringBuilder();
        StringBuilder stringTwo = new StringBuilder();
        int backspaceCount = 0;

        for (int i = str1.length() - 1; i >= 0;) {
            if (str1.charAt(i) == '#') {
                backspaceCount++;
                i--;
            } else {
                if (backspaceCount > 0 && i > 0) {
                    backspaceCount--;
                } else {
                    stringOne.append(str1.charAt(i));
                }
                i--;
            }
        }

        backspaceCount = 0;
        for (int i = str2.length() - 1; i >= 0;) {
            if (str2.charAt(i) == '#') {
                backspaceCount++;
                i--;
            } else {
                if (backspaceCount > 0 && i > 0) {
                    backspaceCount--;
                } else {
                    stringTwo.append(str2.charAt(i));
                }
                i--;
            }
        }

        return stringOne.toString().equals(stringTwo.toString());
    }

    public static void main(String[] args) {
        // Strings.
        String s1 = "xy#z";
        String s2 = "xzz#";
        String s3 = "xy#z";
        String s4 = "xyz#";
        String s5 = "xp#";
        String s6 = "xyz##";
        String s7 = "xywrrmp";
        String s8 = "xywrrmu#p";

        // Results.
        boolean r1 = compareV2(s1, s2);
        boolean r2 = compareV2(s3, s4);
        boolean r3 = compareV2(s5, s6);
        boolean r4 = compareV2(s7, s8);

        // Print.
        System.out.println("After backspaces, the strings " + s1 + " and " + s2 + " are equal: " + r1);
        System.out.println("After backspaces, the strings " + s3 + " and " + s4 + " are equal: " + r2);
        System.out.println("After backspaces, the strings " + s5 + " and " + s6 + " are equal: " + r3);
        System.out.println("After backspaces, the strings " + s7 + " and " + s8 + " are equal: " + r4);
    }
}
