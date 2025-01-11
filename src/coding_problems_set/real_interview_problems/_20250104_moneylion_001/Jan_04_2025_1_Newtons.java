package coding_problems_set.real_interview_problems._20250104_moneylion_001;

import java.util.*;
import java.util.stream.Collectors;

public class Jan_04_2025_1_Newtons {
    public static void main(String[] args) {
        List<Long> list = Arrays.asList(3L, 2L, 1L);
        threeWaysSort(list);
        convertList(list);
        int[] newtons = new int[] {10000000, 100000000, 1000000000};
        System.out.println(smashTheBricks(0, Arrays.stream(newtons).boxed().collect(Collectors.toList())));
    }

    private static void convertList(List<Long> list) {
        long[] arr = {1L, 2L, 3L};
        List<Long> list1 = Arrays.stream(arr).boxed().toList();
        arr = list.stream().mapToLong(Long::longValue).toArray();
    }

    private static void threeWaysSort(List<Long> list) {
        // Way1 Collections.sort()
        Collections.sort(list);
        Collections.sort(list, Collections.reverseOrder());
        // Way2 List.sort()
        list.sort(Long::compareTo);
        list.sort(Comparator.naturalOrder());
        list.sort(Comparator.reverseOrder());
        // Way3 Stream -> Return new list
        List<Long> longList = list.stream().sorted().toList();
        List<Long> sortedListDesc = list.stream().sorted(Comparator.reverseOrder()).toList();
    }

    public static List<List<Long>> smashTheBricks(int bigHits, List<Integer> newtons) {
        int n = newtons.size();
        PriorityQueue<long[]> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(b[1], a[1]));
        for (int i = 0; i < n; i++) {
            maxHeap.add(new long[] {i + 1, newtons.get(i)});
        }

        long[] bigHammerUsage;
        int i = 0;
        if (bigHits == 0) {
            bigHammerUsage = new long[] {-1};
        } else {
            bigHammerUsage = new long[Math.min(n, bigHits)];
            for (int j = 0; j < bigHits && !maxHeap.isEmpty(); j++) {
                bigHammerUsage[i++] = maxHeap.poll()[0];
            }
            Arrays.sort(bigHammerUsage);
        }


        long[] smallHammerUsage;
        long count = bigHits == 0 ? 0 : bigHammerUsage.length;
        i = 0;
        if (maxHeap.isEmpty()) {
            smallHammerUsage = new long[] {-1};
        } else{
            smallHammerUsage = new long[n - bigHits];
            while (!maxHeap.isEmpty()) {
                long[] x = maxHeap.poll();
                count += x[1];
                smallHammerUsage[i++] = x[0];
            }
            Arrays.sort(smallHammerUsage);
        }

        List<List<Long>> result = new ArrayList<>();
        List<Long> cnt = new ArrayList<>(); cnt.add(count);
        result.add(cnt);
        result.add(new ArrayList<>(Arrays.stream(bigHammerUsage).boxed().collect(Collectors.toList())));
        result.add(new ArrayList<>(Arrays.stream(smallHammerUsage).boxed().collect(Collectors.toList())));
        return result;
    }
}
