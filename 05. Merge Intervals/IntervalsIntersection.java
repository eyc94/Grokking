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
        List<Interval> intervalsIntersection = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if ((arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end)
                    || (arr2[j].start >= arr1[i].start && arr2[j].start <= arr1[i].end)) {
                intervalsIntersection
                        .add(new Interval(Math.max(arr1[i].start, arr2[j].start), Math.min(arr1[i].end, arr2[j].end)));
            }

            if (arr1[i].end < arr2[j].end) {
                i++;
            } else {
                j++;
            }
        }

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
