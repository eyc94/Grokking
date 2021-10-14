/*
--- PROBLEM CHALLENGE 3 ---
Cycle In A Circular Array [HARD]

We are given an array containing positive and negative numbers. Suppose the array contains a number 'M' at
a particular index. Now, if 'M' is positive we will move forward 'M' indices. You should assume that the
array is circular which means two things:

1. If, while moving forward, we reach the end of the array, we will jump to the first element to continue
the movement.

2. If, while moving backward, we reach the beginning of the array, we will jump to the last element to
continue the movement.

Write a method to determine if the array has a cycle. The cycle should have more than one element and should
follow one direction which means the cycle should not contain both forward and backward movements.

--- EXAMPLES ---

- EX 1 -
Input:          [1, 2, -1, 2, 2]
Output:         true
Explanation:    The array has a cycle among indices: 0 -> 1 -> 3 -> 0

- EX 2 -
Input:          [2, 2, -1, 2]
Output:         true
Explanation:    The array has a cycle among indices: 1 -> 3 -> 1

- EX 3 -
Input:          [2, 1, -1, -2]
Output:         false
Explanation:    The array does not have any cycle.
*/

import java.util.*;

public class CycleInACircularArray {

    public static boolean loopExists(int[] arr) {
        int slow = 0;
        int fast = 0;

        for (int i = 0; i < arr.length; i++) {
            boolean positive = false;
            if (arr[slow] > 0) {
                positive = true;
            }

            slow = nextIndex(slow, arr);
            if (positive && arr[slow] < 0) {
                continue;
            }
            if (!positive && arr[slow] > 0) {
                continue;
            }

            fast = nextIndex(nextIndex(fast, arr), arr);

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static int nextIndex(int num, int[] arr) {
        num += arr[num];
        if (num > arr.length - 1) {
            num %= arr.length;
        } else if (num < 0) {
            num %= arr.length;
            num = -num;
        }
        return num;
    }

    public static void main(String[] args) {
        // Arrays.
        int[] s1 = new int[] { 1, 2, -1, 2, 2 };
        int[] s2 = new int[] { 2, 2, -1, 2 };
        int[] s3 = new int[] { 2, 1, -1, -2 };

        // Results.
        boolean r1 = loopExists(s1);
        boolean r2 = loopExists(s2);
        boolean r3 = loopExists(s3);

        // Print.
        System.out.println("The array " + Arrays.toString(s1) + " contains a cycle: " + r1);
        System.out.println("The array " + Arrays.toString(s2) + " contains a cycle: " + r2);
        System.out.println("The array " + Arrays.toString(s3) + " contains a cycle: " + r3);
    }
}
