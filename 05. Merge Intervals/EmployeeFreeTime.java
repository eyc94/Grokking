/*
--- PROBLEM CHALLENGE 3 ---

For 'K' employees, we are given a list of intervals representing the working hours of each
employee. Our goal is to find out if there is a free interval that is common to all employees.
You can assume that each list of employee working hours is sorted on the start time.

--- EXAMPLES ---

- EX 1 -
Input:          Employee Working Hours = [[[1, 3], [5, 6]], [[2, 3], [6, 8]]]
Output:         [3, 5]
Explanation:    Both the employees are free between [3, 5]

- EX 2 -
Input:          Employee Working Hours = [[[1, 3], [9, 12]], [[2, 4]], [[6, 8]]]
Output:         [4, 6], [8, 9]
Explanation:    All employees are free between [4, 6] and [8, 9].

- EX 3 -
Input:          Employee Working Hours = [[[1, 3]], [[2, 4]], [[3, 5], [7, 9]]]
Output:         [5, 7]
Explanation:    All employees are free between [5, 7].
*/

import java.util.*;

public class EmployeeFreeTime {

    // This is the Interval class.
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // This is the EmployeeInterval class.
    public static class EmployeeInterval {
        Interval interval; // Interval representing employee's working hours.
        int employeeIndex; // Index of the list containing working hours of this employee.
        int intervalIndex; // Index of the interval in the employee list.

        public EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
            this.interval = interval;
            this.employeeIndex = employeeIndex;
            this.intervalIndex = intervalIndex;
        }
    }

    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();

        // Create a Min Heap that is sorted by ascending start time.
        PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.interval.start, b.interval.start));

        // Insert first interval of each employee to the Min Heap.
        for (int i = 0; i < schedule.size(); i++) {
            minHeap.offer(new EmployeeInterval(schedule.get(i).get(0), i, 0));
        }

        Interval previousInterval = minHeap.peek().interval;
        while (!minHeap.isEmpty()) {
            EmployeeInterval queueTop = minHeap.poll();
            // If previousInterval is not overlapping with the next interval, insert a free
            // interval.
            if (previousInterval.end < queueTop.interval.start) {
                result.add(new Interval(previousInterval.end, queueTop.interval.start));
                previousInterval = queueTop.interval;
            } else { // Overlapping intervals, update the previous interval if needed.
                if (previousInterval.end < queueTop.interval.end) {
                    previousInterval = queueTop.interval;
                }
            }

            // If there are more intervals available for the same employee, add their next
            // interval.
            List<Interval> employeeSchedule = schedule.get(queueTop.employeeIndex);
            if (employeeSchedule.size() > queueTop.intervalIndex + 1) {
                minHeap.offer(new EmployeeInterval(employeeSchedule.get(queueTop.intervalIndex + 1),
                        queueTop.employeeIndex, queueTop.intervalIndex + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Sample 1.
        List<List<Interval>> s1 = new ArrayList<>();
        s1.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        s1.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result = findEmployeeFreeTime(s1);
        System.out.println("Free Intervals: ");
        for (Interval interval : result) {
            System.out.print("[" + interval.start + ", " + interval.end + "]");
        }
        System.out.println();

        // Sample 2.
        s1 = new ArrayList<>();
        s1.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        s1.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        s1.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        result = findEmployeeFreeTime(s1);
        System.out.println("Free Intervals: ");
        for (Interval interval : result) {
            System.out.print("[" + interval.start + ", " + interval.end + "]");
        }
        System.out.println();

        // Sample 3.
        s1 = new ArrayList<>();
        s1.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        s1.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        s1.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = findEmployeeFreeTime(s1);
        System.out.println("Free Intervals: ");
        for (Interval interval : result) {
            System.out.print("[" + interval.start + ", " + interval.end + "]");
        }
        System.out.println();
    }
}
