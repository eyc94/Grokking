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
    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];
        int newListPointer = arr.length - 1;

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int leftSquare = arr[low] * arr[low];
            int rightSquare = arr[high] * arr[high];

            if (rightSquare > leftSquare) {
                squares[newListPointer] = rightSquare;
                high--;
            } else {
                squares[newListPointer] = leftSquare;
                low++;
            }
            newListPointer--;
        }

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
