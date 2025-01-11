package coding_problems_set.websites.educative;

import java.security.Signature;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dec_22_2024_1_deadlock {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        try{
            if (lock1.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                        try {
                            System.out.println("oparetion here");
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
    }

    private static final int N = 10;
    private static class Driver {
        void main() throws InterruptedException {
            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(N);
            for (int i = 0; i < N; i++)
                new Thread(new Worker(startSignal, doneSignal)).start();
            startSignal.countDown();
            doneSignal.await();
        }
    }

    private static class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException e) {

            }
        }

        void doWork() {}
    }


    class SerialExecutor implements Executor {
        final Queue<Runnable> tasks = new LinkedList<>();
        final Executor executor;
        Runnable active;

        SerialExecutor(Executor executor) {
            this.executor = executor;
        }

        protected synchronized void scheduleNext() {
            if ((active = tasks.poll()) != null) {
                executor.execute(active);
            }
        }

         public synchronized void execute(Runnable r) {
            tasks.add(() -> {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            });
            if (active == null) {
                scheduleNext();
            }
         }

         FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
             @Override
             public String call() throws Exception {
                 System.out.println(Thread.currentThread().getName() + ":" + "开始烧开水...");
                 // 模拟烧开水耗时
                 Thread.sleep(2000);
                 System.out.println(Thread.currentThread().getName() + ":"  + "开水已经烧好了...");
                 return "开水";
             }
         });
        Thread thread = new Thread(futureTask);


        // 实现线程安全的单例类，双重检查锁定 Double-Checked Locking
        static class Singleton {
            private static volatile Singleton instance;
            private Singleton() {}
            public static Singleton getInstance() {
                if (instance == null) {
                    synchronized (Singleton.class) {
                        if (instance == null) {
                            instance = new Singleton();
                        }
                    }
                }
                return instance;
            }
        }

         // 高并发场景下的 缓存
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
         // 用于生产者-消费者 模式
        ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();
        // 读多写少的场景
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        static class MessageQueue {
            private final BlockingQueue<String> queue;

            MessageQueue(int capacity) {
                this.queue = new LinkedBlockingQueue<>(capacity);
            }

             public void produce(String msg) throws InterruptedException {
                 queue.put(msg);
             }

             public String consume() throws InterruptedException {
                return queue.take();
             }
        }

        public transient String test;

    }

}
