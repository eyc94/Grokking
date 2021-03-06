# Introduction to Sliding Window
- In many problems dealing with an array (or a LinkedList), we are asked to find or calculate something among all the contiguous subarrays (or sublists) of a given size.
- For example:

`Given an array, find the average of all contiguous subarrays of size 'K' in it.`

- Real input:
```
Array: [1, 3, 2, 6, -1, 4, 1, 8, 2], K = 5
```
- We are asked to find the average of all contiguous subarrays of size '5' in the given array.
    1. For the first 5 numbers (subarray from index 0 - 4), the average is: (1 + 3 + 2 + 6 - 1) / 5 => 2.2
    2. The average of the next 5 numbers (subarray from index 1 - 5) is: (3 + 2 + 6 - 1 + 4) / 5 => 2.8
    3. For the next 5 numbers (subarray from index 2 - 6), the average is: (2 + 6 - 1 + 4 + 1) / 5 => 2.4
    4. And so on ...
- The final output is below:
```
Output: [2.2, 2.8, 2.4, 3.6, 2.8]
```
- Brute force algorithm would be to calculate the sum of every 5-element contiguous subarray of the given array.
- Divide the sum by '5' to find the average.
```java
import java.util.Arrays;

class AverageOfSubarrayOfSizeK {
    public static double[] findAverages(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1];
        for (int i = 0; i <= arr.length - K; i++) {
            double sum = 0;
            for (int j = i; j < i + K; j++) {
                sum += arr[j];
            }
            result[i] = sum / K;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] sample = new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 };
        double[] result = findAverages(5, sample);
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }
}
```
- Time Complexity:
    - Since for every element of the input array, we are calculating the sum of its next 'K' elements, the time complexity of the above algorithm will be `O(N * K)` where 'N' is the length of the input array and 'K' is the length of contiguous subarray.
- Is there a better solution?
- Current solution is inefficient because for any two consecutive subarrays of size '5', the overlapping part (4 elements) will be evaluated twice.
- See the image below:

![alt text](https://github.com/eyc94/Grokking/blob/master/images/sliding_window_one.png 'Sliding Window Opimization Example')

- As shown, we see there are 4 overlapping elements.
    - First subarray is from index 0 - 4.
    - Second subarray is from index 1 - 5.
- Can we reuse the `sum` we have calculated for the overlapping elements?
- Visualize a sliding window of 5 elements that is the contiguous subarray.
- When we move to the next subarray, we slide the window by 1 element.
    - We subtract the element going out of the window.
    - We add the element coming into the window.
- The time complexity would result `O(N)`.

![alt text](https://github.com/eyc94/Grokking/blob/master/images/sliding_window_two.png 'Sliding Window One Step Forward')

- Algorithm for `Sliding Window` approach:
```java
import java.util.Arrays;

class AverageOfSubarrayOfSizeK {
    public static double[] findAverages(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1];
        double windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // Add the next element.
            // Slide the window. We do not need to slide if we have not hit the required since size of 'K'.
            if (windowEnd >= K - 1) {
                result[windowStart] = windowSum / K; // Calculate the average.
                windowSum -= arr[windowStart]; // Subtract the element going out.
                windowStart++; // Slide the window ahead.
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] sample = new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 };
        double[] result = findAverages(5, sample);
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }
}
```
- In some problems, size of sliding window is not fixed.
- Expand or shrink window based on problem constraints.