/*
--- SIMILAR PROBLEMS 1 ---

Given a list of appointments, find all the conflicting appointments.

--- EXAMPLES ---

- EX 1 -
Appointments:       [[4, 5], [2, 3], [3, 6], [5, 7], [7, 8]]
Output:             [4, 5] and [3, 6] conflict.
                    [3, 6] and [5, 7] conflict.
*/

import java.util.*;

public class FindConflictingAppointments {

    // This is the Interval class.
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static List<List<Interval>> findConflicts(Interval[] intervals) {
        // Sort our intervals by start time.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        // This list of list of intervals keep track of the conflict pairs.
        // We return a list of list of intervals that conflict.
        List<List<Interval>> conflicts = new ArrayList<>();

        // Use a nested for loop to iterate through each possible pair of intervals.
        // Add the pair to the list of whichever conflicts.
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j].start < intervals[i].end) {
                    List<Interval> currentConflict = new ArrayList<>();
                    currentConflict.add(intervals[i]);
                    currentConflict.add(intervals[j]);
                    conflicts.add(currentConflict);
                }
            }
        }

        // Return conflicts list of lists.
        return conflicts;
    }

    public static void main(String[] args) {
        // Sample 1.
        Interval[] s1 = new Interval[] { new Interval(4, 6), new Interval(2, 3), new Interval(3, 7), new Interval(5, 7),
                new Interval(6, 8) };
        List<List<Interval>> r1 = findConflicts(s1);

        // Print conflicts.
        for (int i = 0; i < r1.size(); i++) {
            System.out.print("Conflict: ");
            for (int j = 0; j < r1.get(i).size(); j++) {
                if (j == r1.get(i).size() - 1) {
                    System.out.print("[" + r1.get(i).get(j).start + ", " + r1.get(i).get(j).end + "]");
                } else {
                    System.out.print("[" + r1.get(i).get(j).start + ", " + r1.get(i).get(j).end + "], ");
                }
            }
            System.out.println();
        }
    }
}
