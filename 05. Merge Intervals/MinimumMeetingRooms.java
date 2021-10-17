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
        return -1;
    }

    public static void main(String[] args) {
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
    }
}
