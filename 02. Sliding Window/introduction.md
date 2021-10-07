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