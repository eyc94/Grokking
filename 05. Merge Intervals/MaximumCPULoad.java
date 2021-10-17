/*
--- PROBLEM CHALLENGE 2 ---

We are given a list of jobs. Each job has a Start time, an End time, and a CPU load when it
is running. Our goal is to find the maximum CPU load at any time if all the jobs are running
on the same machine.

--- EXAMPLES ---

- EX 1 -
Jobs:           [[1, 4, 3], [2, 5, 4], [7, 9, 6]]
Output:         7
Explanation:    Since [1, 4, 3] and [2, 5, 4] overlap, their maximum CPU load (3 + 4 = 7) will
                be when both the jobs are running at the same time i.e., during the time interval [2, 4].

- EX 2 -
Jobs:           [[6, 7, 10], [2, 4, 11], [8, 12, 15]]
Output:         15
Explanation:    None of the jobs overlap, therefore we will take the maximum load of any job which is 15.

- EX 3 -
Jobs:           [[1, 4, 2], [2, 4, 1], [3, 6, 5]]
Output:         8
Explanation:    Maximum Cpu load will be 8 as all jobs overlap during the time interval [3, 4].
*/

import java.util.*;

public class MaximumCPULoad {

    // This is the Job class.
    public static class Job {
        int start;
        int end;
        int cpuLoad;

        public Job(int start, int end, int cpuLoad) {
            this.start = start;
            this.end = end;
            this.cpuLoad = cpuLoad;
        }
    }

    public static int findMaxCPULoad(List<Job> jobs) {
        // If there are no jobs or jobs points to nothing, there is no cpu load.
        if (jobs == null || jobs.size() == 0) {
            return 0;
        }

        // Sort the jobs by start time.
        Collections.sort(jobs, (a, b) -> Integer.compare(a.start, b.start));

        // Create a Min Heap that has capacity of job's size.
        // Sorts by increasing end time.
        // Our Min Heap holds the current jobs under load.
        PriorityQueue<Job> minHeap = new PriorityQueue<>(jobs.size(), (a, b) -> Integer.compare(a.end, b.end));

        // Keep track of the max load so far. Return this in the end.
        int maxLoad = 0;
        // Keep track of the current load that our Min Heap is holding.
        int currentLoad = 0;
        // Iterate through our jobs.
        for (Job job : jobs) {
            // We need to remove jobs that are finished (job in Min Heap whose end time is
            // less than the start time of new job to be added). Basically, our Min Heap
            // contains overlapping jobs. Finished jobs are removed.
            // Before removing, subtract the removed job's cpu load from the current min
            // heap load.
            while (!minHeap.isEmpty() && minHeap.peek().end < job.start) {
                currentLoad -= minHeap.peek().cpuLoad;
                minHeap.poll();
            }

            // Our Min Heap now has no jobs that finishes before the current job to add.
            // Add the job to the Min Heap.
            minHeap.offer(job);
            // Add the cpu load of that job to the current Min Heap load.
            currentLoad += job.cpuLoad;
            // Update max load if greater than current max load.
            maxLoad = Math.max(maxLoad, currentLoad);
        }
        return maxLoad;
    }

    public static void main(String[] args) {
        List<Job> s1 = new ArrayList<>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
        System.out.println("Max CPU load at any time: " + findMaxCPULoad(s1));

        s1 = new ArrayList<>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
        System.out.println("Max CPU load at any time: " + findMaxCPULoad(s1));

        s1 = new ArrayList<>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
        System.out.println("Max CPU load at any time: " + findMaxCPULoad(s1));
    }
}
