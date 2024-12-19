package ds_algorithm.slicewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode第1100题 "Find K-Length Substrings With No Repeated Characters"
 */
public class Dec_18_2024_1_num_K_Len_Substr_Repeats {
    public static void main(String[] args) {
        Dec_18_2024_1_num_K_Len_Substr_Repeats sol = new Dec_18_2024_1_num_K_Len_Substr_Repeats();
        System.out.println(sol.numKLenSubstrRepeats("createfunonyoka", 4));
        System.out.println(sol.numKLenSubstrRepeats("havefunonleetcode", 5));
    }

    /**
     * @param s String
     * @param k k
     * @return 统计字符串中长度为 k 且不含重复字符的子串的个数
     */
    public int numKLenSubstrRepeats(String s, int k) {
        int winStart = 0;
        int totalNum = 0;
        Map<Character, Integer> cnt = new HashMap<>();
        for (int winEnd = 0; winEnd < s.length(); winEnd++) {
            char c = s.charAt(winEnd);
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
            // 当窗口长度达到k时检查是否无重复字符
            if (cnt.get(c) == 1 && (winEnd - winStart + 1) == k) {
                // 窗口中所有字符都频次为1，则这是一个无重复字符的子串
                totalNum ++;
                // 移动left，缩小窗口，继续寻找下一个子串
                char cc = s.charAt(winStart++);
                cnt.put(cc, cnt.get(cc) - 1);
            }
            // 如果某个字符出现次数大于1，说明有重复
            // 不断移动left直到该字符频次恢复为1以下（即无重复）
            while(cnt.get(c) > 1) {
                char cc = s.charAt(winStart++);
                cnt.put(cc, cnt.get(cc) - 1);
            }
        }
        return totalNum;
    }
}
