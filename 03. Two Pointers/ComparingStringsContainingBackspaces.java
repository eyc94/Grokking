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

    public static boolean compareV2(String str1, String str2) {
        int pointerOne = str1.length() - 1;
        int pointerTwo = str2.length() - 1;
        while (pointerOne >= 0 || pointerTwo >= 0) {
            int i1 = getNextValidCharIndex(str1, pointerOne);
            int i2 = getNextValidCharIndex(str2, pointerTwo);

            if (i1 < 0 && i2 < 0) {
                return true;
            }

            if (i1 < 0 || i2 < 0) {
                return false;
            }

            if (str1.charAt(i1) != str2.charAt(i2)) {
                return false;
            }

            pointerOne = i1 - 1;
            pointerTwo = i2 - 1;
        }
        return true;
    }

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
