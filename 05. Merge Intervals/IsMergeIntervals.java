/*
--- SIMILAR PROBLEMS 1 ---
Do Intervals Merge

Given a set of intervals, find out if any two intervals overlap.

--- EXAMPLES ---

- EX 1 -
Intervals:      [[1, 4], [2, 5], [7, 9]]
Output:         true
Explanation:    Intervals [1, 4] and [2, 5] overlap.
*/

import java.util.*;

public class IsMergeIntervals {

    // This is the Interval class.
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static boolean doesMerge(List<Interval> intervals) {

        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        Iterator<Interval> intervalItr = intervals.iterator();
        Interval interval = intervalItr.next();
        int start = interval.start;
        int end = interval.end;

        while (intervalItr.hasNext()) {
            interval = intervalItr.next();
            if (interval.start <= end) {
                return true;
            } else {
                start = interval.start;
                end = interval.end;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        List<Interval> s1 = new ArrayList<>();
        s1.add(new Interval(1, 4));
        s1.add(new Interval(2, 5));
        s1.add(new Interval(7, 9));
        System.out.println("------------------------------------------------------");
        System.out.print("List Of Intervals: ");
        for (Interval interval : s1) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("Does merge: " + doesMerge(s1));

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
        System.out.println("Does merge: " + doesMerge(s2));

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
        System.out.println("Does merge: " + doesMerge(s3));

        List<Interval> s4 = new ArrayList<>();
        s4.add(new Interval(1, 4));
        s4.add(new Interval(5, 6));
        s4.add(new Interval(7, 9));
        System.out.println("------------------------------------------------------");
        System.out.print("List Of Intervals: ");
        for (Interval interval : s4) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("Does merge: " + doesMerge(s4));
        System.out.println("------------------------------------------------------");
    }
}
