package edu.elearning.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * always unlock in finally block to avoid deadlock
 */
public class ReentrantLockInterruptExample {

    private static final long MAX = 100L;
    private static final Lock lock = new ReentrantLock();

    private static int counter;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(ReentrantLockInterruptExample::inc);
        Thread t2 = new Thread(ReentrantLockInterruptExample::dec);

        t1.setName("Increment Thread");
        t2.setName("Decrement Thread");

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();

        for (int i = 0; i < 10; i++) {

            sleep(5);
            t1.interrupt();
            printMessage(" isInterrupted : " + t1.isInterrupted());
        }


        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        System.out.println("Time elapsed : " + (end - start));

        System.out.println("Counter value : " + counter);


    }


    private static void inc() {

        for (long i = 0; i < MAX; i++) {
            printMessage("counter value is : " + counter);
            boolean gotLock = false;
            try {
                gotLock = lock.tryLock(10, TimeUnit.SECONDS);
                counter++;

//                sleep();
            } catch (InterruptedException e) {
                printInterruptMessage("while taking lock");
            } finally {
                if (gotLock) {
                    lock.unlock();
                }
            }
        }
    }

    private static void dec() {
        for (long i = 0; i < MAX; i++) {
            printMessage("counter value is : " + counter);
            lock.lock();
            try {
                counter--;
                sleep();
            } finally {
                lock.unlock();
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            printInterruptMessage("in sleep method");
        }
    }

    private static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            printInterruptMessage("in sleep method");
        }
    }

    private static String threadName() {
        return Thread.currentThread().getName();
    }

    private static void printMessage(String msg) {
        System.out.println(threadName() + " " + msg);
    }

    private static void printInterruptMessage(String msg) {
        printMessage("got interrupted " + msg + ". Interrupt status : " + Thread.currentThread().isInterrupted());
    }

}
