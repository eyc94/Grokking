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

    /*
     * The strategy here is to utilize fast and slow pointers to find a cycle, if
     * one exists. Our array will have negative and positive numbers. The positive
     * represents forward movement, and the negative represents backwards movement.
     * 
     * We iterate through all the numbers because if one iteration has no cycle,
     * another might.
     * 
     * We find the direction of that number. We flag it as true (positive) and false
     * (negative).
     * 
     * Our idea is to move our slow pointer one number at a time and the fast
     * pointer two pointers at a time. We use a helper function to get the next
     * index that our pointers will go to. If our function returns a '-1', this
     * means that there is no cycle and we can break out of our loop.
     * 
     * We move both the slow and fast pointers once. Before we move the fast pointer
     * again, we check to see if fast pointer is not '-1'. If it isn't we can
     * continue.
     * 
     * In the end, either our slow or fast will equal '-1'. Or, our slow will equal
     * fast. If our slow or fast equals '-1' at the end of all our iterations, we
     * will end our loop. Then, we return false because we haven't found a cycle.
     * 
     * However, if the slow and fast pointers meet and they are not '-1', a cycle
     * has been found. We can return true immediately.
     * 
     * Time Complexity: O(N^2) where N is the length of our array. This is because
     * we are iterating through each number while also iterating to find a cycle.
     * 
     * Space Complexity: O(1).
     * 
     * There is an alternate approach by keeping track of numbers that have already
     * been visited in an auxiliary array. This reduces time to O(N) but increases
     * space to O(N).
     */

    public static boolean loopExists(int[] arr) {
        // We are going to iterate through all numbers in our array.
        // Even though one iteration does not have a cycle, we may find one in another
        // iteration.
        for (int i = 0; i < arr.length; i++) {
            // We keep track of whether we are going forwards or backwards.
            // If the number we are at is positive, this indicates forward movement.
            boolean isForward = arr[i] >= 0;
            // We have a slow and fast pointer.
            int slow = i;
            int fast = i;

            // Move the slow and fast pointer once and twice before checking conditions.
            do {
                // Move slow and fast pointer to next index.
                slow = nextIndex(arr, isForward, slow);
                fast = nextIndex(arr, isForward, fast);
                // If our fast pointer is NOT -1. That is, our function above did NOT return -1.
                // We can move our pointer again. So, move fast pointer once more.
                if (fast != -1) {
                    fast = nextIndex(arr, isForward, fast);
                }
                // We stop when either pointer is -1, indicating invalid cycle.
                // Or, we stop when fast == slow because this means we found a cycle.
            } while (slow != -1 && fast != -1 && slow != fast);

            // If we found a cycle, return true.
            if (slow != -1 && slow == fast) {
                return true;
            }
        }
        // No valid cycle.
        return false;
    }

    // This helper function finds the next index the pointers will point to after
    // one movement.
    public static int nextIndex(int[] arr, boolean isForward, int currentIndex) {
        // Calculate direction of current index.
        // If current index number is positive, we are going positive direction.
        boolean direction = arr[currentIndex] >= 0;
        // If the current direction is different than the new direction, this means we
        // are going backwards. This is not allowed, so return -1.
        if (isForward != direction) {
            return -1;
        }

        // Otherwise, we find the next index.
        // Add the current index's number to the current index to find the next index.
        // If our next index goes over the allowed length, we divide by length and take
        // the remainder.
        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;

        // If the next index is a negative index, we just add the length of the array to
        // find the index from the back.
        if (nextIndex < 0) {
            nextIndex += arr.length;
        }

        // If the next index we found is equal to the current index (next == prev), this
        // is a cycle. One element cycle. Return -1.
        if (nextIndex == currentIndex) {
            nextIndex = -1;
        }

        // Otherwise, return the next index the pointers will be at.
        return nextIndex;
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
