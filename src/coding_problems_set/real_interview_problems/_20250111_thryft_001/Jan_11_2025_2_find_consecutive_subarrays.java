package coding_problems_set.real_interview_problems._20250111_thryft_001;

import java.util.*;

public class Jan_11_2025_2_find_consecutive_subarrays {
    // Frequency map and reverse frequency map
    static Map<Integer, Integer> freqMap = new HashMap<>();
    static TreeMap<Integer, TreeSet<Integer>> sortedFreqMap = new TreeMap<>(Collections.reverseOrder());

    public static void main(String[] args) {
        // Test cases
        List<Integer> test1 = Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE,
                Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 3,
                10, 11, 12, 13, 14);
        System.out.println("Test 1: " + findKConsecutiveSubarrays(test1, 5));  // Expected: 2

        List<Integer> test2 = Arrays.asList(10, 2, 3, 5, 7, 8, 9);
        System.out.println("Test 2: " + findKConsecutiveSubarrays(test2, 5));  // Expected: 0

        List<Integer> test3 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Test 3: " + findKConsecutiveSubarrays(test3, 5));  // Expected: 1

        List<Integer> test4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Test 4: " + findKConsecutiveSubarrays(test4, 5));  // Expected: 2
    }

    public static int findKConsecutiveSubarrays(List<Integer> nums, int kkk) {
        if (nums == null || nums.size() < 5) return 0;

        HashSet<Integer> window = new HashSet<>();
        int count = 0;

        for (int i = 0; i <= nums.size() - 5; i++) {
            window.clear();
            for (int j = i; j < i + 5; j++) {
                window.add(nums.get(j));
            }

            if (window.size() == 5) {
                int min = Collections.min(window);
                boolean isConsecutive = true;
                for (int k = 1; k < 5; k++) {
                    if (!window.contains(min + k)) {
                        isConsecutive = false;
                        break;
                    }
                }
                if (isConsecutive) {
                    count++;
                    i += 4;  // Skip to next potential sequence
                }
            }
        }
        return count;
    }

    public static int findKConsecutiveSubarrays_1(List<Integer> nums, int k) {
        if (nums == null || nums.size() < 5) return 0;
        int count = 0;
        int i = 0;
        while (i <= nums.size() - k) {
            boolean isConsecutive = true;
            for (int j = i; j < i + k - 1; j++) {
                if (nums.get(j) == Integer.MAX_VALUE || nums.get(j) + 1 != nums.get(j + 1)) { // -2147483648
                    isConsecutive = false;
                    break;
                }
            }
            if (isConsecutive) {
                count++;
                i += k;
            } else i++;
        }
        return count;
    }

}
