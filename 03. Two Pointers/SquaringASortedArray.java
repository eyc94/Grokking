/*
Squaring A Sorted Array [EASY]

Given a sorted array, create a new array containing squares of all the numbers of the
input array in the sorted order.

--- EXAMPLES ---

- EX 1 -
Input:          [-2, -1, 0, 2, 3]
Output:         [0, 1, 4, 4, 9]

- EX 2 -
Input:          [-3, -1, 0, 1, 2]
Output:         [0, 1, 1, 4, 9]
*/

import java.util.*;

public class SquaringASortedArray {

    /*
     * This utilizes a two pointer method. We can use both pointers from the same
     * end. However, we will use the pointers from the left and right ends. We make
     * a new array that holds the squares of the old array in sorted order.
     * 
     * To do this, we add the squares from the back (right-end) of our new array.
     * This is because we compare the two values in our old array. We add to the new
     * array whichever is the greater value.
     * 
     * We then decrement the high pointer if we took the high value. We increment
     * the low pointer if we took the low value. We decrement the squares array
     * pointer to the next open spot to fill.
     * 
     * We keep doing this until the low and high pointers cross. In odd length
     * arrays, the last step will process the low and high pointers at the same
     * values. Even length will have the pointers cross and end the loop.
     * 
     * Time Complexity: O(N) where N is the length of our input array.
     * 
     * Space Complexity: O(N) where N is also the length of our input array. This is
     * because of the new array taking the same length as the old array.
     */

    public static int[] makeSquares(int[] arr) {
        // Create a new array to hold the squares of the old array in sorted order.
        // The new array will have the same length as the old array.
        int[] squares = new int[arr.length];
        // This is the pointer for the new array. It keeps track of the next empty
        // position to be filled.
        int newListPointer = arr.length - 1;

        // This is the left end of our two pointers.
        int low = 0;
        // This is the right end of our two pointers.
        int high = arr.length - 1;

        // While the left and right ends do not meet.
        // We have the equality condition inside our condition.
        // This is to account for an odd length array.
        while (low <= high) {
            // Grab the left and right pointer squares.
            int leftSquare = arr[low] * arr[low];
            int rightSquare = arr[high] * arr[high];

            // Add the bigger of the two to the new squares array.
            if (rightSquare > leftSquare) {
                squares[newListPointer] = rightSquare;
                high--;
            } else {
                squares[newListPointer] = leftSquare;
                low++;
            }
            // Move the new squares array pointer to the next open spot.
            newListPointer--;
        }

        // Return the squares array.
        return squares;
    }

    public static void main(String[] args) {
        // Sample arrays.
        int[] s1 = new int[] { -2, -1, 0, 2, 3 };
        int[] s2 = new int[] { -3, -1, 0, 1, 2 };
        int[] s3 = new int[] { -3, -1, 2, 4, 6 };

        // Calculate results.
        int[] r1 = makeSquares(s1);
        int[] r2 = makeSquares(s2);
        int[] r3 = makeSquares(s3);

        // Print results.
        System.out.println("The array " + Arrays.toString(s1) + " squared and sorted is: " + Arrays.toString(r1));
        System.out.println("The array " + Arrays.toString(s2) + " squared and sorted is: " + Arrays.toString(r2));
        System.out.println("The array " + Arrays.toString(s3) + " squared and sorted is: " + Arrays.toString(r3));
    }
}
