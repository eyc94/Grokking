/*
--- PROBLEM CHALLENGE 1 ---

Given a list of intervals representing the start and end time of 'N' meetings,
find the minimum number of rooms required to hold all the meetings.

--- EXAMPLES ---

- EX 1 -
Meetings:       [[1, 4], [2, 5], [7, 9]]
Output:         2
Explanation:    Since [1, 4] and [2, 5] overlap, we need two rooms to hold these
                two meetings. [7, 9] can occur in any of the two rooms later.

- EX 2 -
Meetings:       [[6, 7], [2, 4], [8, 12]]
Output:         1
Explanation:    None of the meetings overlap, therefore we only need one room to
                hold all meetings.

- EX 3 -
Meetings:       [[4, 5], [2, 3], [2, 4], [3, 5]]
Output:         2
Explanation:    Since [1, 4] overlaps with the other two meetings [2, 3] and [3, 6],
                we need two rooms to hold all the meetings.

- EX 4 -
Meetings:       [[4, 5], [2, 3], [2, 4], [3, 5]]
Output:         2
Explanation:    We will need one room for [2, 3] and [3, 5], and another room for
                [2, 4] and [4, 5].
*/

import java.util.*;

public class MinimumMeetingRooms {

    // This is the Interval class.
    public static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        // If there are no meetings or meetings points to nothing, there are no classes.
        if (meetings == null || meetings.size() == 0) {
            return 0;
        }

        // Sort by start time.
        Collections.sort(meetings, (a, b) -> Integer.compare(a.start, b.start));

        // Keep track of minimum rooms needed.
        int minRooms = 0;
        // Create a min heap that holds meetings times by increasing end time.
        PriorityQueue<Meeting> minHeap = new PriorityQueue<>(meetings.size(), (a, b) -> Integer.compare(a.end, b.end));

        for (Meeting meeting : meetings) {
            // Remove all meetings that ended.
            while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end) {
                minHeap.poll();
            }
            // Add current meeting into minHeap.
            minHeap.offer(meeting);
            // All active meetings in minHeap, so we need rooms for all of them.
            minRooms = Math.max(minRooms, minHeap.size());
        }

        // Return minimum rooms needed.
        return minRooms;
    }

    public static void main(String[] args) {
        // Sample 1.
        List<Meeting> s1 = new ArrayList<>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        int result = findMinimumMeetingRooms(s1);
        System.out.println("Minimum meeting rooms required: " + result);

        // Sample 2.
        s1 = new ArrayList<>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        result = findMinimumMeetingRooms(s1);
        System.out.println("Minimum meeting rooms required: " + result);

        // Sample 3.
        s1 = new ArrayList<>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = findMinimumMeetingRooms(s1);
        System.out.println("Minimum meeting rooms required: " + result);

        // Sample 4.
        s1 = new ArrayList<>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = findMinimumMeetingRooms(s1);
        System.out.println("Minimum meeting rooms required: " + result);

        // Sample 5.
        s1 = new ArrayList<>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        result = findMinimumMeetingRooms(s1);
        System.out.println("Minimum meeting rooms required: " + result);
    }
}
