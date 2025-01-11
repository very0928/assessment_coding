package coding_problems_set.real_interview_problems._20250106_ebay_001;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Jan_06_2025_3_interview {
    private Jan_06_2025_3_interview instance;
    private Jan_06_2025_3_interview() {}
    public synchronized Jan_06_2025_3_interview getInstance() {
        if (instance == null) {
            instance = new Jan_06_2025_3_interview();
        }
        return instance;
    }

    private Queue<Integer> q1 = new ArrayDeque<>();
    private Queue<Integer> q2 = new ArrayDeque<>();
    public void addNum(int num){
        synchronized (q1) {
            synchronized (q2) {
                q1.add(num);
                q2.add(num);
            }
        }
    }
    public void addNumReverse(int num) {
        synchronized (q2) {
            synchronized (q1){
                q2.add(num);
                q1.add(num);
            }
        }
    }

    public void deadlock() throws InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            addNum(10);
        });
        executorService.execute(() -> {
            addNumReverse(1000);
        });

        executorService.shutdown();
    }
}
