/*
Conflicting Appointments [MEDIUM]

Given an array of intervals representing 'N' appointments, find out if a person can
attend all the appointments.

--- EXAMPLES ---

- EX 1 -
Input:          [[1, 4], [2, 5], [7, 9]]
Output:         false
Explanation:    Since [1, 4] and [2, 5] overlap, a person cannot attend both of these appointments.

- EX 2 -
Input:          [[6, 7], [2, 4], [8, 12]]
Output:         true
Explanation:    None of the appointments overlap, therefore a person can attend all of them.

- EX 3 -
Input:          [[4, 5], [2, 3], [3, 6]]
Output:         false
Explanation:    Since [4, 5] and [3, 6] overlap, a person cannot attend both of these appointments.
*/

import java.util.*;

public class ConflictingAppointments {

    /*
     * This is similar to our Merge Intervals problem. This is a lot easier than it
     * seems. We just sort our array by start time.
     * 
     * When we encounter an interval whose start time is before the end time of the
     * previous interval, we return false immediately. There is an overlap and we
     * cannot take all courses.
     * 
     * If we successfully get through our array, we know there is no overlap. So,
     * return true.
     * 
     * Time Complexity: O(N + N log N) which is O(N log N) asymptotically. This is
     * because we need to sort our array.
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

    public static boolean canAttendAllAppointments(Interval[] intervals) {
        // We sort our array by start time.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        // We iterate from the second interval onwards.
        for (int i = 1; i < intervals.length; i++) {
            // Check if our current interval overlaps with the previous interval.
            // Because our array is sorted, if our current interval starts before the
            // previous ends, we know that there is an overlap.
            if (intervals[i].start <= intervals[i - 1].end) {
                // We can return false immediately.
                return false;
            }
        }

        // If we get through all intervals and no problems occur, return true.
        return true;
    }

    public static void main(String[] args) {
        // Sample 1.
        System.out.println("------------------------------------------------------");
        Interval[] s1 = { new Interval(1, 4), new Interval(2, 5), new Interval(7, 9) };
        boolean r1 = canAttendAllAppointments(s1);
        System.out.print("List Of Appointments: ");
        for (Interval interval : s1) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("Can attend all appointments: " + r1);

        // Sample 2.
        System.out.println("------------------------------------------------------");
        Interval[] s2 = { new Interval(6, 7), new Interval(2, 4), new Interval(8, 12) };
        boolean r2 = canAttendAllAppointments(s2);
        System.out.print("List Of Appointments: ");
        for (Interval interval : s2) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("Can attend all appointments: " + r2);

        // Sample 3.
        System.out.println("------------------------------------------------------");
        Interval[] s3 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6) };
        boolean r3 = canAttendAllAppointments(s3);
        System.out.print("List Of Appointments: ");
        for (Interval interval : s3) {
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        }
        System.out.println();
        System.out.println("Can attend all appointments: " + r3);
        System.out.println("------------------------------------------------------");
    }
}
