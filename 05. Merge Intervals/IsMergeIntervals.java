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

    /*
     * This is the same as MergeIntervals.java. Except, we do not add the merged
     * interval. As soon as we encounter an overlap between intervals, we return
     * true. Otherwise, we update the start and end values and keep going.
     * 
     * Time Complexity: O(N + N log N) because of sorting and iterating through our
     * intervals. Asymptotic runtime is O(N log N).
     * 
     * Space Complexity: O(N) for sorting.
     */

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
        // Sort the intervals by start time.
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        // Create an Iterator object from the intervals.
        Iterator<Interval> intervalItr = intervals.iterator();

        // Grab the first interval.
        Interval interval = intervalItr.next();
        // Get the start and end value of the first interval.
        int start = interval.start;
        int end = interval.end;

        // Iterate while we still have an interval.
        while (intervalItr.hasNext()) {
            // Get the next interval.
            interval = intervalItr.next();
            // If the current interval's start time is less than or equal to previous
            // interval's end time, return true. This is because there is an overlap.
            if (interval.start <= end) {
                return true;
            } else {
                // No overlap, move on to next interval by updating the interval.
                start = interval.start;
                end = interval.end;
            }
        }

        // If we get here, there is no overlapping.
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
