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

    /*
     * The code here is a little tricky and we use a PriorityQueue (minHeap). First,
     * we cannot just iterate through our meetings and find overlaps and merge
     * repeatedly. If our final merged interval has every meeting overlapping, that
     * does not mean we need the maximum amount of rooms possible. We're trying to
     * see if we can reduce the amount.
     * 
     * We need to keep track of the mutual exclusiveness of the overlapping
     * meetings.
     * 
     * We need to keep track of the end times of the meetings that are currently in
     * progress. When we find a meeting that is overlapping with one in session,
     * before we make another room for it, we check to see if a room in progress has
     * a meeting ending before our new meeting starts. Then, we can use this room
     * instead of allocating a new room.
     * 
     * So, we need to keep track of the ending times of all the meetings currently
     * happening. When we try to schedule a new meeting, we see what meetings
     * already ended. Keep these meetings in a data structure that can easily give
     * us the smallest ending time. A Min Heap is perfect.
     * 
     * First, we sort the meetings by start time.
     * 
     * We create a minHeap that has a max capacity of the meetings list size. Our
     * comparison is based on the end times of the meetings. The front is going to
     * have the smallest end time. This minHeap stores meetings that are currently
     * happening in their rooms. At the end, our minHeap size will have a collection
     * of meetings in progress that are overlapping. This size is the number of
     * rooms we need.
     * 
     * Iterate through all of our meetings.
     * 
     * On every iteration, we need to remove meetings that are ending before our new
     * meeting is scheduled to start. So, iterate while minHeap is not empty and
     * when the first meeting in our minHeap ends before or on the start time of the
     * new (to-be-scheduled) meeting.
     * 
     * Note: We use poll() to remove() and offer() to add(). This is because in the
     * case where the queue is empty, poll() will return null where remove() throws
     * an exception. If we cannot add to queue, offer() returns false and add()
     * throws an exception. We use peek() to see the front of the priority queue
     * (the meeting with the smallest end time). Or meeting that is going to finish
     * first.
     * 
     * The idea is that a meeting that's ending soon and ends before the start of
     * the new meeting can have its room be used by the new meeting to be scheduled.
     * 
     * Once our while loop finishes, all meetings not overlapping with the new
     * meeting is gone. We add the new meeting to the priority queue.
     * 
     * We update the minimum rooms required by taking the max of the current value
     * and the size of our queue. Remember, our size represents the number of rooms.
     * 
     * Return the minimum rooms required.
     * 
     * Time Complexity: O(N log N) where N is the total number of meetings. This is
     * because we sort in the beginning. We also need to poll() or offer() meetings.
     * This operation takes O(log N) time. Total complexity is O(N log N + N log N),
     * which is O(N log N) asymptotically.
     * 
     * Space Complexity: O(N) for sorting. Worst case is adding all meetings to
     * minHeap which requires O(N) space.
     */

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

        // Iterate through all meetings.
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
