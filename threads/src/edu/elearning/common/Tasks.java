package edu.elearning.common;

import static edu.elearning.common.Utils.printMessage;
import static edu.elearning.common.Utils.sleep;

public class Tasks implements Runnable{
    private static int i=0;
    private String taskName;
    public Tasks(){
        taskName = "Runnable task - " +  ++i;
    }

    public static Runnable[] getTasks(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater then zero");
        }


        Runnable[] runnables = new Runnable[size];
        for (int i = 0; i < runnables.length; i++) {
            runnables[i] = new Tasks();
//            runnables[i].setName("Task--" + (i+1));
        }

        return runnables;
    }

    public void run() {
        printMessage(" going to sleep..");
        sleep(5);
        printMessage(" exiting..");
    }

    @Override
    public String toString() {
        return taskName;
    }
}
