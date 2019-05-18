package edu.elearning.common;

import static edu.elearning.common.Utils.*;

public class Tasks {

    public static Thread[] getTasks(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater then zero");
        }


        Thread[] threads = new Thread[size];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(Tasks::run);
            threads[i].setName("Task--"+i);
        }

        return threads;
    }

    private static void run() {
        printMessage(" going to sleep..");
        sleep(1);
        printMessage(" exiting..");
    }
}
