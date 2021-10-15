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
        List<Interval> mergedIntervals = new LinkedList<>();

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> s1 = new ArrayList<>();
        s1.add(new Interval(1, 4));
        s1.add(new Interval(2, 5));
        s1.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : merge(s1)) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
    }
}