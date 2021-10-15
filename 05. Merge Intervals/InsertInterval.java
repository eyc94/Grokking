/*
Insert Interval [MEDIUM]

Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the
correct position and merge all unnecessary intervals to produce a list that has only mutually
exclusive intervals.

--- EXAMPLES ---

- EX 1 -
Input:          Intervals = [[1, 3], [5, 7], [8, 12]], New Interval = [4, 6]
Output:         [[1, 3], [4, 7], [8, 12]]
Explanation:    After insertion, since [4, 6] overlaps with [5, 7], we merged then in to one [4, 7].

- EX 2 -
Input:          Intervals = [[1, 3], [5, 7], [8, 12]], New Interval = [4, 10]
Output:         [[1, 3], [4, 12]]
Explanation:    After insertion, since [4, 10] overlaps with [5, 7] & [8, 12], we merged them into
                [4, 12].

- EX 3 -
Input:          Intervals = [[2, 3], [5, 7]], New Interval = [1, 4]
Output:         [[1, 4], [5, 7]]
Explanation:    After intsertion, since [1, 4] overlaps with [2, 3], we merged them in to one [1, 4].
*/

import java.util.*;

public class InsertInterval {
    // This is the Interval class.
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> mergedIntervals = new ArrayList<>();

        // We iterate through the intervals that do not merge with our inserted
        // intervals.
        // Add those intervals to the merged intervals.
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            mergedIntervals.add(intervals.get(i));
            i++;
        }

        // We continue our iteration of our intervals list.
        // If the current interval's start value is <= the new interval's end, update
        // the new interval's start and end values.
        // The new interval (if overlapping with current) will always have an 'end'
        // value greater than the current interval's 'start'.
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }

        // Add the merged interval.
        mergedIntervals.add(newInterval);

        // There may be more intervals to add, so just add the remaining that do not
        // overlap with the new interval.
        while (i < intervals.size()) {
            mergedIntervals.add(intervals.get(i));
            i++;
        }

        // Return the merged intervals.
        return mergedIntervals;
    }

    public static void main(String[] args) {
        // Sample 1.
        List<Interval> s1 = new ArrayList<>();
        s1.add(new Interval(1, 3));
        s1.add(new Interval(5, 7));
        s1.add(new Interval(8, 12));
        Interval n1 = new Interval(4, 6);

        System.out.println("------------------------------------------------------");
        System.out.println("New Interval: [" + n1.start + ", " + n1.end + "]");
        System.out.print("List Of Intervals: ");
        for (Interval interval : s1) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.print("Merged intervals: ");
        for (Interval interval : insert(s1, n1)) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------");

        // Sample 2.
        List<Interval> s2 = new ArrayList<>();
        s2.add(new Interval(1, 3));
        s2.add(new Interval(5, 7));
        s2.add(new Interval(8, 12));
        Interval n2 = new Interval(4, 10);
        System.out.println("------------------------------------------------------");
        System.out.println("New Interval: [" + n2.start + ", " + n2.end + "]");
        System.out.print("List Of Intervals: ");
        for (Interval interval : s2) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.print("Merged intervals: ");
        for (Interval interval : insert(s2, n2)) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------");

        // Sample 3.
        List<Interval> s3 = new ArrayList<>();
        s3.add(new Interval(2, 3));
        s3.add(new Interval(5, 7));
        Interval n3 = new Interval(1, 4);
        System.out.println("------------------------------------------------------");
        System.out.println("New Interval: [" + n3.start + ", " + n3.end + "]");
        System.out.print("List Of Intervals: ");
        for (Interval interval : s3) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.print("Merged intervals: ");
        for (Interval interval : insert(s3, n3)) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------");

        // int[][] s4 = new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        // int[][] result = mergeIntervalsV2(s4);
        // System.out.println(Arrays.deepToString(result));
    }
}
