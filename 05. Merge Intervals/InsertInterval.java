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

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> s1 = new ArrayList<>();
        s1.add(new Interval(1, 3));
        s1.add(new Interval(5, 7));
        s1.add(new Interval(8, 12));
        System.out.println("------------------------------------------------------");
        System.out.print("List Of Intervals: ");
        for (Interval interval : s1) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.print("Merged intervals: ");
        for (Interval interval : merge(s1)) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------");

        List<Interval> s2 = new ArrayList<>();
        s2.add(new Interval(6, 7));
        s2.add(new Interval(2, 4));
        s2.add(new Interval(5, 9));
        System.out.println("------------------------------------------------------");
        System.out.print("List Of Intervals: ");
        for (Interval interval : s2) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.print("Merged intervals: ");
        for (Interval interval : merge(s2)) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------");

        List<Interval> s3 = new ArrayList<>();
        s3.add(new Interval(1, 4));
        s3.add(new Interval(2, 6));
        s3.add(new Interval(3, 5));
        System.out.println("------------------------------------------------------");
        System.out.print("List Of Intervals: ");
        for (Interval interval : s3) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.print("Merged intervals: ");
        for (Interval interval : merge(s3)) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------");

        // int[][] s4 = new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        // int[][] result = mergeIntervalsV2(s4);
        // System.out.println(Arrays.deepToString(result));
    }
}
