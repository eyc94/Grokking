# Introduction to Two Pointers
- In many problems that deal with sorted arrays (or LinkedLists).
- The need to find a set of elements that fulfill certain constraints.
- Set of elements can be pairs, triplets, or even subarrays.
- For example:

`Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.`

- We can consider each element one by one and iterate through the remaining elements to find pairs of given sum.
- Time complexity would be O(N<sup>2</sup>) where N is the number of elements in our input array.
- Given the array is sorted, we can start one pointer at the beginning and the second pointer at the end.
- See if the numbers at pointers add up to sum.
- If they do not add up to target, do one of two things:
    1. If sum is greater than target, we need a smaller pair. So, decrement end pointer.
    2. If sum is less than target, we need a bigger pair. So, increment the start pointer.

![alt text](https://github.com/eyc94/Grokking/blob/master/images/sliding_window_one.png "Two pointer example one visual")

- The time complexity of the above is O(N).