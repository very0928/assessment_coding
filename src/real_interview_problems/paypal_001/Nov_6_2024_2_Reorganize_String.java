package real_interview_problems.paypal_001;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * Return any possible rearrangement of s or return "" if not possible.
 * <a href="https://leetcode.com/problems/reorganize-string/">Solution</a>
 */
public class Nov_6_2024_2_Reorganize_String {
    public String reorganizeString(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(counter.entrySet());

        StringBuilder sb = new StringBuilder();
        Map.Entry<Character, Integer> previous = null;
        while (!maxHeap.isEmpty() || previous != null) {
            if (maxHeap.isEmpty()) {
                return "";
            }
            Map.Entry<Character, Integer> current = maxHeap.poll();
            sb.append(current.getKey());
            current.setValue(current.getValue() - 1);

            if (previous != null && previous.getValue() > 0) {
                maxHeap.add(previous);
            }

            previous = current.getValue() == 0 ? null : current;
        }

        return sb.toString();
    }

    public String reorganizeString_1(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        maxHeap.addAll(freqMap.keySet());

        StringBuilder res = new StringBuilder();
        while (maxHeap.size() >= 2) {
            char char1 = maxHeap.poll();
            char char2 = maxHeap.poll();

            res.append(char1);
            res.append(char2);

            freqMap.put(char1, freqMap.get(char1) - 1);
            freqMap.put(char2, freqMap.get(char2) - 1);

            if (freqMap.get(char1) > 0) maxHeap.add(char1);
            if (freqMap.get(char2) > 0) maxHeap.add(char2);
        }

        if (!maxHeap.isEmpty()) {
            char ch = maxHeap.poll();
            if (freqMap.get(ch) > 1) return "";
            res.append(ch);
        }

        return res.toString();
    }

    public String reorganizeString_2(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        maxHeap.addAll(freqMap.keySet());

        if (freqMap.get(maxHeap.peek()) > (s.length() + 1) / 2) {
            return "";
        }

        char[] result = new char[s.length()];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            for (int j = 0; j < freqMap.get(c); j++) {
                if (i >= s.length()) i = 1;
                result[i] = c;
                i += 2;
            }
        }

        return new String(result);
    }


    public static void main(String[] args) {
        Nov_6_2024_2_Reorganize_String reorganize_string = new Nov_6_2024_2_Reorganize_String();
        System.out.println("PriorityQueue approach: " + reorganize_string.reorganizeString("aaabbcc"));
        System.out.println("PriorityQueue approach: " + reorganize_string.reorganizeString_2("aaabbcc"));
        System.out.println("Array approach: " + reorganize_string.reorganizeString_2("aaabbcc"));
    }
}
