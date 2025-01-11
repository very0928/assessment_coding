package coding_problems_set.real_interview_problems._20241108_paypal_002;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * There are n chocolates and the weight of the chocolates is given as an array of integers weights.
 * Every day, one can pick one chocolate, eat half of it, and put the remaining half back.
 * Find the minimum possible total weight of the remaining chocolates after a days.
 * Note that one can eat the same chocolate multiple times.
 * The weight of the part eaten can be calculated as floor(weights/12)
 */
public class Nov_8_2024_2_Minimum_Total_Weight_of_Chocolate {
    public static void main(String[] args) {
        List<Integer> weights = new ArrayList<>(Arrays.asList(10, 20, 30));
        int a = 2;
        int result = findMinWeight(weights, a);
        System.out.println("Minimum possible total weight of remaining chocolates: " + result);

    }
    public static int findMinWeight(List<Integer> weights, int d) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.addAll(weights);

        for (int i = 0; i < d && !maxHeap.isEmpty(); i++) {
            int heaviest = maxHeap.poll(); // 获取并移除当前最大值
            int remainWeight = heaviest - heaviest / 2; // 吃掉一半后剩余的重量
            maxHeap.add(remainWeight); // 将剩余部分重新加入堆
        }

        return maxHeap.stream().mapToInt(Integer::intValue).sum(); // 计算总和
    }

}
