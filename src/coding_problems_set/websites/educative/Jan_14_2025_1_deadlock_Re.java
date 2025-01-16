package coding_problems_set.websites.educative;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Jan_14_2025_1_deadlock_Re {
    public static void escapeDeadlock() {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            try {
                if (lock1.tryLock(10, TimeUnit.SECONDS)) {
                    System.out.println("Thread 1: Holding lock 1...");
                    try {
                        Thread.sleep(1000); // Simulating some work
                        if (lock2.tryLock(10, TimeUnit.SECONDS)) {
                            System.out.println("Thread 1: Holding lock 1 & 2...");
                        } else {
                            System.out.println("Thread 1: Unable to acquire lock 2");
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
    }
}
