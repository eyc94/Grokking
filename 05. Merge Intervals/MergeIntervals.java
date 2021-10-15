/*
Merge Intervals [MEDIUM]

Given a list of intervals, merge all the overlapping intervals to produce a list that has only
mutually exclusive intervals.

--- EXAMPLES ---

- EX 1 -
Intervals:      [[1, 4], [2, 5], [7, 9]]
Output:         [[1, 5], [7, 9]]
Explanation:    Since the first two intervals [1, 4] and [2, 5] overlap, we merged them into [1, 5].

- EX 2 -
Intervals:      [[6, 7], [2, 4], [5, 9]]
Output:         [[2, 4], [5, 9]]
Explanation:    Since the intervals [6, 7] and [5, 9] overlap, we merged them into one [5, 9].

- EX 3 -
Intervals:      [[1, 4], [2, 6], [3, 5]]
Output:         [[1, 6]]
Explanation:    Since all the given intervals overlap, we merged them into one.
*/

import java.util.*;

public class MergeIntervals {

    // This is the Interval class.
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static List<Interval> merge(List<Interval> intervals) {
        // If the intervals list is only 1 or 0 intervals, we just need to return that.
        if (intervals.size() < 2) {
            return intervals;
        }

        // Sort the list of lists by start time.
        // We use Collections.sort which takes a collection and comparator as an
        // argument.
        // We use Lambda expressions that take in parameters 'a' and 'b' and returns the
        // output of the function Integer.compare().
        // We lastly use Integer.compare(a, b) that returns 0 if 'a' == 'b', -1 if 'a' <
        // 'b', and 1 if 'a' > 'b'.
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        // Create the list of Intervals that hold the merged intervals to return.
        List<Interval> mergedIntervals = new LinkedList<>();

        // Create an Iterator object from the collection of Intervals.
        Iterator<Interval> intervalItr = intervals.iterator();
        // Grab the first interval from Interval collection.
        Interval interval = intervalItr.next();
        int start = interval.start; // Grab its start value.
        int end = interval.end; // Grab its end value.

        // Iterate while we still have something in our collection of Intervals.
        while (intervalItr.hasNext()) {
            // Grab the next interval.
            interval = intervalItr.next();
            // If the start value of this interval is <= the end of the previous, there is
            // an overlap.
            // So, we know that the start of the current interval is inside the previous
            // interval. We need to see if the current end value is greater than the
            // previous end value. If so, we use it. Use the max of the two to be the new
            // merged interval end.
            if (interval.start <= end) {
                end = Math.max(interval.end, end);
            } else { // If our current interval is NOT overlapping with previous.
                // Add the previous interval start and end as merged interval in our result
                // list.
                mergedIntervals.add(new Interval(start, end));
                // Update the new start and end values to reflect current interval for next
                // iteration.
                start = interval.start;
                end = interval.end;
            }
        }

        // Add the last interval.
        // Whether it's from merging from first 'if' condition or the else condition.
        // If it's from the 'else' condition, remember that we updated our 'start' and
        // 'end' values. If our while loop ends, we still have a start and end value we
        // need to account for.
        mergedIntervals.add(new Interval(start, end));
        // Return the merged intervals to the user.
        return mergedIntervals;
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
    }
}