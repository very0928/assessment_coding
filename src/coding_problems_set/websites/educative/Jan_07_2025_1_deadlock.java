package coding_problems_set.websites.educative;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Jan_07_2025_1_deadlock {
    private static volatile Jan_07_2025_1_deadlock instance;
    private Jan_07_2025_1_deadlock() {}

    // Double-Checked Locking -- 实现线程安全的单例类
    public static Jan_07_2025_1_deadlock getInstance() {
        if (instance == null) {
            synchronized (Jan_07_2025_1_deadlock.class) {
                if (instance == null) {
                    instance = new Jan_07_2025_1_deadlock();
                }
            }
        }
        return instance;
    }
    // 1. deadlock example
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();
    public void deadScenario() {
        Thread thread1 = new Thread(() -> {
            synchronized (LOCK_1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (LOCK_2) {
                    System.out.println("Thread 1; Holding lock 1 & 2...");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (LOCK_2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (LOCK_1) {
                    System.out.println("Thread 2: Holding lock 2 & 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    // 2. Using timeout mechanism
    public void usingTimeout() {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            try {
                if (lock1.tryLock(10, TimeUnit.SECONDS)) {
                    System.out.println("Thread 1: Holding lock 1...");
                    try {
                        if (lock2.tryLock(10, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("Thread 2: holding lock 2...");
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                if (lock2.tryLock(10, TimeUnit.SECONDS)) {
                    System.out.println("Thread 2: Holding lock 2...");
                    try {
                        if (lock1.tryLock(10, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("Thread 2: Holding lock 1...");
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
    }

    // 3. Using higher-level concurrency utilities
    public void usingConcurrencyUtilities() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);

        executor.submit(() -> {
            try {
                sem1.acquire();
                System.out.println("Thread 1: Acquired sem1");
                Thread.sleep(1000);
                sem2.acquire();
                System.out.println("Thread 1: Acquired sem2");
                sem2.release();
                sem1.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                sem2.acquire();
                System.out.println("Thread 2: Acquired sem2");
                Thread.sleep(1000);
                sem1.acquire();
                System.out.println("Thread 2: Acquired sem1");
                sem1.release();
                sem2.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }

    // 4. Deadlock detection
    public void deadlockDetectionAndRecovery() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK_1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_2) {
                    System.out.println("Thread 1: Holding locks 1 & 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_1) {
                    System.out.println("Thread 2: Holding locks 2 & 1");
                }
            }
        });

        t1.start();
        t2.start();

        // Deadlock detection
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
                    long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
                    if (deadlockedThreads != null) {
                        System.out.println("Deadlock detected: " + Arrays.toString(deadlockedThreads));
                        t1.interrupt();
                        t2.interrupt();
                        System.out.println("Deadlock released");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // ForkJoinPool
    private static void convertList(List<Long> list) {
        long[] arr = {1L, 2L, 3L};
        List<Long> list1 = Arrays.stream(arr).boxed().toList();
        arr = list.stream().mapToLong(Long::longValue).toArray();
    }

    private static void threeWaysSortList(List<Long> list) {
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

    public static void main(String[] args) {
        List<String> safelist = Collections.synchronizedList(new ArrayList<String>());
        List<String> concurrentList = new CopyOnWriteArrayList<>(); // "snapshot" style
        Map<String, String> concurrentMap = new ConcurrentHashMap<>();
        Map<String, String> concurrentMap_1 = new ConcurrentSkipListMap<>();
        Set<String> concurrentSet = new ConcurrentSkipListSet<>();
        FutureTask<String> futureTask = new FutureTask(
                () -> {
                    String result = "";
                    for (int i = 0; i < 1000000; i++) {
                        result += i;
                    }
                    return result;
                }
        );
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureTask);

    }
}
