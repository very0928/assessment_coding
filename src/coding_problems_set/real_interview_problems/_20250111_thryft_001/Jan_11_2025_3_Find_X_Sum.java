package coding_problems_set.real_interview_problems._20250111_thryft_001;

import java.util.*;
import java.util.function.BiConsumer;

public class Jan_11_2025_3_Find_X_Sum {
        // A pair to hold (frequency, value)
        static class Pair {
            int freq, val;
            Pair(int freq, int val) {
                this.freq = freq;
                this.val = val;
            }
        }

        // Comparator that sorts (freq, val) in ascending order first by freq, then by val.
        // The "largest" (by freq, then value) will be the last entry in ascending order.
        static class PairComparator implements Comparator<Pair> {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.freq != p2.freq) {
                    return Integer.compare(p1.freq, p2.freq);
                }
                // Tie-break by value
                return Integer.compare(p1.val, p2.val);
            }
        }

        // Helper to add or remove a key from a TreeMap<Pair, Integer>.
        private void treeMapAdd(TreeMap<Pair, Integer> map, Pair key, int addCount) {
            map.put(key, map.getOrDefault(key, 0) + addCount);
            if (map.get(key) == 0) {
                map.remove(key);
            }
        }

        private void treeMapRemove(TreeMap<Pair, Integer> map, Pair key, int removeCount) {
            if (!map.containsKey(key)) return;
            int newCount = map.get(key) - removeCount;
            if (newCount <= 0) {
                map.remove(key);
            } else {
                map.put(key, newCount);
            }
        }

        // Get the first (smallest) key from TreeMap in ascending order
        private Pair getFirstKey(TreeMap<Pair, Integer> map) {
            return map.firstKey();
        }

        // Get the last (largest) key from TreeMap in ascending order
        private Pair getLastKey(TreeMap<Pair, Integer> map) {
            return map.lastKey();
        }

        public long[] findXSum(int[] nums, int k, int x) {
            int n = nums.length;
            long[] answer = new long[n - k + 1];

            // freqMap[e] = current frequency of e in the window
            final Map<Integer, Integer> freqMap = new HashMap<>();

            // We'll keep two TreeMaps:
            // topSet: up to x largest pairs by (freq, val)
            // bottomSet: the rest
            final TreeMap<Pair, Integer> topSet = new TreeMap<>(new PairComparator());
            final TreeMap<Pair, Integer> bottomSet = new TreeMap<>(new PairComparator());

            // Use an array of length 1 to store sumTop so we can modify it inside lambdas
            final long[] sumTop = new long[1];  // sumTop[0] = sum of freq(e)*e for topSet

            // Rebalance method: ensure topSet has size x (or total distinct < x)
            final Runnable rebalance = () -> {
                // Move largest from bottomSet -> topSet if topSet.size() < x
                while (!bottomSet.isEmpty() && topSet.size() < x) {
                    Pair largestInBottom = getLastKey(bottomSet);
                    int count = bottomSet.get(largestInBottom);
                    treeMapRemove(bottomSet, largestInBottom, 1);

                    // Move exactly 1 instance to topSet
                    treeMapAdd(topSet, largestInBottom, 1);
                    sumTop[0] += (long) largestInBottom.freq * largestInBottom.val;

                    // If count > 1, put the remainder back
                    if (count > 1) {
                        treeMapAdd(bottomSet, largestInBottom, count - 1);
                        break;
                    }
                }

                // If topSet is too big, move smallest from topSet -> bottomSet
                while (topSet.size() > x) {
                    Pair smallestInTop = getFirstKey(topSet);
                    int count = topSet.get(smallestInTop);
                    treeMapRemove(topSet, smallestInTop, 1);
                    sumTop[0] -= (long) smallestInTop.freq * smallestInTop.val;

                    // Move exactly 1 instance to bottomSet
                    treeMapAdd(bottomSet, smallestInTop, 1);

                    // If count > 1, put the remainder back
                    if (count > 1) {
                        treeMapAdd(topSet, smallestInTop, count - 1);
                        break;
                    }
                }
            };

            // Remove old (freq, val) from whichever set it might be in
            final BiConsumer<Integer, Integer> removeFreqVal = (f, v) -> {
                if (f == 0) return; // no old pair to remove
                Pair p = new Pair(f, v);
                if (topSet.containsKey(p)) {
                    treeMapRemove(topSet, p, 1);
                    sumTop[0] -= (long) f * v;
                } else {
                    treeMapRemove(bottomSet, p, 1);
                }
            };

            // Insert new or updated (freq, val) into bottomSet by default, then rebalance
            final BiConsumer<Integer, Integer> insertFreqVal = (f, v) -> {
                if (f == 0) return;
                Pair p = new Pair(f, v);
                treeMapAdd(bottomSet, p, 1);
                rebalance.run();
            };

            // Build the first window [0..k-1]
            for (int i = 0; i < k; i++) {
                int val = nums[i];
                int oldFreq = freqMap.getOrDefault(val, 0);
                removeFreqVal.accept(oldFreq, val);

                int newFreq = oldFreq + 1;
                freqMap.put(val, newFreq);
                insertFreqVal.accept(newFreq, val);
            }
            // After building the first window, sumTop[0] is the x-sum for it
            answer[0] = sumTop[0];

            // Slide the window
            for (int i = k; i < n; i++) {
                int e_in = nums[i];
                int e_out = nums[i - k];

                // Remove e_out
                int oldFreqOut = freqMap.get(e_out);
                removeFreqVal.accept(oldFreqOut, e_out);
                int newFreqOut = oldFreqOut - 1;
                if (newFreqOut == 0) {
                    freqMap.remove(e_out);
                } else {
                    freqMap.put(e_out, newFreqOut);
                    insertFreqVal.accept(newFreqOut, e_out);
                }

                // Add e_in
                int oldFreqIn = freqMap.getOrDefault(e_in, 0);
                removeFreqVal.accept(oldFreqIn, e_in);
                int newFreqIn = oldFreqIn + 1;
                freqMap.put(e_in, newFreqIn);
                insertFreqVal.accept(newFreqIn, e_in);

                // Now sumTop[0] holds the x-sum of subarray [i - k + 1 .. i]
                answer[i - k + 1] = sumTop[0];
            }

            return answer;
        }


    /********************************************************************
         *                 TEST HARNESS / MAIN (for local testing)          *
         ********************************************************************/
        public static void main(String[] args) {
            Jan_11_2025_3_Find_X_Sum sol = new Jan_11_2025_3_Find_X_Sum();

            // 1) Example from the user:
            // nums = [1,1,2,2,3,4,2,3], k=6, x=2
            // expected => [6,10,12]
            {
                int[] nums = {1,1,2,2,3,4,2,3};
                int k = 6, x = 2;
                long[] out = sol.findXSum(nums, k, x);
                System.out.println("Test #1 => " + Arrays.toString(out));
                // Should be [6,10,12]
            }

            // 2) A smaller test to check correctness:
            // nums = [2,2,3], k=3, x=1
            // entire window is [2,2,3], freq(2)=2, freq(3)=1, top1 => 2 => sum=2+2=4
            {
                int[] nums = {2,2,3};
                int k = 3, x = 1;
                long[] out = sol.findXSum(nums, k, x);
                System.out.println("Test #2 => " + Arrays.toString(out));
                // expected => [4]
            }

            // 3) If x = k => then the x-sum is sum of entire subarray
            // e.g. nums=[1,2,3], k=3, x=3 => subarray is [1,2,3], sum=6
            {
                int[] nums = {1,2,3};
                int k = 3, x = 3;
                long[] out = sol.findXSum(nums, k, x);
                System.out.println("Test #3 => " + Arrays.toString(out));
                // expected => [6]
            }

            // 4) If all elements are the same => e.g. nums=[5,5,5,5], k=2, x=2
            // subwindows => [5,5], [5,5], [5,5]
            // freq(5)=2 => top2 is just 5 => sum=5+5=10
            // output => [10,10,10]
            {
                int[] nums = {5,5,5,5};
                int k = 2, x = 2;
                long[] out = sol.findXSum(nums, k, x);
                System.out.println("Test #4 => " + Arrays.toString(out));
                // expected => [10,10,10]
            }

            // 5) Another check:
            // nums = [1,2,2,1], k=3, x=1
            // subarrays of size 3:
            //   #1: [1,2,2] => freq(1)=1, freq(2)=2 => top1 => (2 => freq=2) => sum=2+2=4
            //   #2: [2,2,1] => freq(2)=2, freq(1)=1 => top1 => sum=2+2=4
            // expected => [4,4]
            {
                int[] nums = {1,2,2,1};
                int k = 3, x = 1;
                long[] out = sol.findXSum(nums, k, x);
                System.out.println("Test #5 => " + Arrays.toString(out));
                // expected => [4,4]
            }
        }
}
