package edu.elearning.common;

public class Tasks {

    public static Thread[] getTasks(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater then zero");
        }


        Thread[] threads = new Thread[size];
        for (Thread t : threads) {
            t = new Thread(Tasks::run);
        }

        return threads;
    }

    private static void run() {
        System.out.println(Thread.currentThread().getName() + " running..");
    }
}
