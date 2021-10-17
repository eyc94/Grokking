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

    /*
     * This code is fairly straightforward. The only thing to look out for is that
     * we need to keep track of which employee the interval belongs to. So, we make
     * another class called EmployeeInterval. This holds the interval, the employee
     * index, the interval index in the List of the employee's schedule.
     * 
     * So, we create a Min Heap that is ordered by ascending interval start times.
     * It will hold EmployeeIntervals.
     * 
     * We add the first intervals of every employee into the Min Heap as an
     * EmployeeInterval object.
     * 
     * The idea is to continuously take EmployeeIntervals out and compare it to the
     * previously seen EmployeeInterval. So, remove the first EmployeeInterval in
     * Min Heap and store in a pointer indicating current interval.
     * 
     * If the previous end time is < the current interval's start time, we know
     * there is no overlap. We can create a free interval and add to results. The
     * free interval is the previous's end and current's start time. Update the
     * previousInterval to point to current interval for next iteration.
     * 
     * If the previous end time is >= the current interval's start time, we know
     * there is an overlap. We don't really do much because there is no free time
     * here. What we want is to set up previousInterval for the next iteration. If
     * the previous interval's end time is >= than the current interval's end time,
     * we keep the previous pointer the same. This is because the previous interval
     * has a chance of overlapping with the next iteration's interval.
     * 
     * If the previous interval's end time is < the current interval's end time, we
     * want to use the current interval as the previousInterval for the next
     * iteration. So, update it.
     * 
     * Lastly, we want to add the next interval the employee worked for the employee
     * that we removed at the beginning. To do this, we grab the List containing the
     * Intervals of that employee. (This is where using EmployeeInterval's
     * properties is good).
     * 
     * Once we get the list, we check to see if the interval's index reached the end
     * of the list. (That is, if there is more intervals to add). If there is, we
     * create a new EmployeeInterval object and place it in the Min Heap. Remember
     * to increment the intervalIndex property.
     * 
     * Once the Min Heap is empty, the while loop ends. Return the results.
     * 
     * Time Complexity: O(N log K) where N is the number of intervals and K is the
     * number of employees. This is because we iterate through all N intervals. On
     * each N intervals, we do remove or add employees which takes O(log K) time.
     * 
     * Space Complexity: O(K). Min Heap will not have more than 'K' elements.
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
