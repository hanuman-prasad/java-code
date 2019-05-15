package edu.elearning.locks;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static edu.elearning.Utils.printMessage;
import static edu.elearning.Utils.sleep;

public class ReentrantRnWLockExample {

    private static final int MAX = 100000;

    private static Map<Integer, Integer> map = new HashMap<>();

    private static ReentrantReadWriteLock readWriteLock;

    private static Lock readLock;
    private static Lock writeLock;
    private static int c;

    private static Thread[] rt = new Thread[1];
    private static Thread[] wt = new Thread[10];

    public static void main(String[] args) throws InterruptedException {

        readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();


        for (int i = 0; i < rt.length; i++) {
            rt[i] = new Thread(ReentrantRnWLockExample::read);
            rt[i].setName("ReadThread-" + (i + 1));
            rt[i].start();
        }

        for (int i = 0; i < wt.length; i++) {
            wt[i] = new Thread(ReentrantRnWLockExample::write);
            wt[i].setName("WriteThread-" + (i + 1));
            wt[i].start();
        }


        for (Thread t : rt) {
            t.join();
        }

        for (Thread t : wt) {
            t.join();
        }

        System.out.println("c = " + c);
    }


    private static void read() {
        for (int i = 0; i < MAX; i++) {

            readLock.lock();
            printMessage("value : " + c);
            try {
                for (Thread t : wt) {
                    System.out.println(t.getName() + " : " + t.getState());
                }
                System.out.println(Thread.currentThread().getName() + " : " + Thread.currentThread().getState());
                sleep(5);
            } finally {
                readLock.unlock();
            }

        }
    }

    private static void write() {
        for (int i = 0; i < MAX; i++) {

            writeLock.lock();
            try {
                c++;
            } finally {
                writeLock.unlock();
            }
        }
    }
}
