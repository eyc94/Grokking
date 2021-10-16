/*
Intervals Intersection [MEDIUM]

Given two lists of intervals, find the intersection of these two lists. Each list consists of
disjoint intervals sorted on their start time.

--- EXAMPLES ---

- EX 1 -
Input:          arr1 = [[1, 3], [5, 6], [7, 9]], arr2 = [[2, 3], [5, 7]]
Output:         [2, 3], [5, 6], [7, 7]
Explanation:    The output list contains the common intervals between the two lists.

- EX 2 -
Input:          arr1 = [[1, 3], [5, 7], [9, 12]], arr2 = [[5, 10]]
Output:         [5, 7], [9, 10]
Explanation:    The output list contains the common intervals between the two lists.
*/

import java.util.*;

public class IntervalsIntersection {
    // This is the Interval class.
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Interval[] merge(Interval[] arr1, Interval[] arr2) {
        // We create a list to keep track of our intersecting intervals.
        // We will convert this to an array later to return to the user.
        // This is because arrays are static.
        List<Interval> intervalsIntersection = new ArrayList<>();

        // Iterate both interval arrays going one interval at a time.
        int i = 0;
        int j = 0;
        // Iterate until one pointer reaches the end of the array.
        while (i < arr1.length && j < arr2.length) {
            // Check for overlapping conditions.
            // If the start time of array1 is >= array2's start and <= array2's end. (array1
            // interval starts between array2's start and end).
            // OR
            // If the start time of array2 is >= array1's start and <= array1's end. (array2
            // interval starts between array1's start and end).
            if ((arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end)
                    || (arr2[j].start >= arr1[i].start && arr2[j].start <= arr1[i].end)) {
                // Add the intersections ONLY.
                // The intersection is the MAX 'start' time of array1 and array2 intervals.
                // The intersection is the MIN 'end' time of array1 and array2 intervals.
                intervalsIntersection
                        .add(new Interval(Math.max(arr1[i].start, arr2[j].start), Math.min(arr1[i].end, arr2[j].end)));
            }

            // We define the requirements for the pointer iterations.
            // We move the pointer of the interval that finishes faster. This way we can
            // account for the longer 'end' time of the two being overlapped in the next
            // interval to follow.
            if (arr1[i].end < arr2[j].end) {
                // If array1 interval finishes first, move that interval.
                i++;
            } else {
                // If array2 interval finishes first, move that interval.
                j++;
            }
        }

        // Convert list to array and return it.
        return intervalsIntersection.toArray(new Interval[intervalsIntersection.size()]);
    }

    public static void main(String[] args) {
        // Sample 1.
        Interval[] s1 = new Interval[] { new Interval(1, 3), new Interval(5, 6), new Interval(7, 9) };
        Interval[] s2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
        System.out.println("---------------------------------------------------");
        System.out.println("Original Lists: ");
        for (Interval interval : s1) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        for (Interval interval : s2) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        Interval[] result = merge(s1, s2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("---------------------------------------------------");

        // Sample 2.
        s1 = new Interval[] { new Interval(1, 3), new Interval(5, 7), new Interval(9, 12) };
        s2 = new Interval[] { new Interval(5, 10) };
        System.out.println("Original Lists: ");
        for (Interval interval : s1) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        for (Interval interval : s2) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        result = merge(s1, s2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("---------------------------------------------------");
    }
}
