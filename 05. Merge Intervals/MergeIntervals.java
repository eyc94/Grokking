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

    /*
     * This is a pretty easy and fun problem to solve. We create a class called
     * Interval that stores the start and end values.
     * 
     * First, check if our intervals list is less than size 2. If so, we know there
     * is only 1 or 0 intervals in our list. This means that we can just return the
     * intervals list. Merged interval of 1 interval is just that interval. Merged
     * interval of 0 is just 0.
     * 
     * Our plan is to iterate through the start times and check if each start time
     * is <= to the previous end time. That is, we want to check if there is any
     * overlap in the intervals.
     * 
     * So, we must sort by start time. Because we have a collection of Intervals, we
     * can use the following:
     * 
     * Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
     * 
     * 1. Collections.sort() takes in the collections to sort and a comparator to
     * denote criteria to compare.
     * 
     * 2. We use the lambda expression '(para1, para2) -> expression'. This takes in
     * two parameters and returns whatever the expression outputs.
     * 
     * 3. Integer.compare(x, y) returns 0 if x == y, -1 if x < y, and 1 if x > y.
     * 
     * We create a list of Intervals to return to the user. This stores merged
     * intervals.
     * 
     * We want to start with the first interval. We create an Iterator object from
     * the Interval collection. We use the .iterator() method to create an Iterator
     * object from our collection. Use the .next() method to get the interval. Grab
     * the start and end values of that interval.
     * 
     * Use the while loop to iterate through our collection with the .hasNext()
     * method. This iterates while we have something in our collection. Grab the
     * next interval with .next() function.
     * 
     * Grab our current interval's start time. Check if it is less than previous
     * interval's end time. If it is, we update the end time. This is because we
     * know that our current start time falls inside the previous interval. This is
     * overlapping. We just need to know if our current end time is greater or
     * smaller than the previous end time. We take the max of the previous end and
     * current end.
     * 
     * If the current start time is > than the previous end time, we know that our
     * current interval is NOT in the previous interval. So, we can go ahead and add
     * the previous interval that we have so far (start and end values) to the
     * merged list. We then update the start and end values to take on the current
     * interval's start and end values.
     * 
     * Once our while loop ends, we will have 'start' and 'end' values leftover.
     * From the first 'if' statement, we do not add anything to the merged list.
     * From the 'else' statement, we just add our previous interval NOT our current
     * interval. If our loop ended there, our current interval will NOT be accounted
     * for. This leaves a leftover either way. So, we add the 'start' and 'end' as a
     * new interval to the merged list.
     * 
     * Return the merged list.
     * 
     * Time Complexity: O(N log N) where N is the size of the list of Intervals. It
     * takes O(N log N) to sort the intervals. It takes O(N) to process the
     * intervals. Total is O(N + N log N). This is O(N log N) asymptotically.
     * 
     * Space Complexity: O(N) because we need to return list of merged intervals. We
     * need O(N) for sorting. Total is O(N).
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