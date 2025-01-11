package coding_problems_set.real_interview_problems._20250106_ebay_001;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Jan_06_2025_2_min_meeting_room {
    public int minMeetingRoom(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        minHeap.add(intervals[0][1]);
        int min = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(intervals[i][1]);
            min = Math.max(min, minHeap.size());
        }
        return min;
    }
}
