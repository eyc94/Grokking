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
    public static boolean compare(String str1, String str2) {
        return false;
    }

    public static void main(String[] args) {

    }
}
