package edu.elearning.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * always unlock in finally block to avoid deadlock
 */
public class ReentrantLockExample {

    private static final long MAX = 100000000L;
    private static final Lock lock = new ReentrantLock();

    private static int counter;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(ReentrantLockExample::inc);
        Thread t2 = new Thread(ReentrantLockExample::dec);

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        System.out.println("Time elapsed : " + (end - start));

        System.out.println("Counter value : " + counter);


    }


    private static void inc() {
        for (long i = 0; i < MAX; i++) {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }
    }

    private static void dec() {
        for (long i = 0; i < MAX; i++) {
            lock.lock();
//            if(!lock.tryLock()) continue;
            try {
                counter--;
            } finally {
                lock.unlock();
            }
        }
    }

}
