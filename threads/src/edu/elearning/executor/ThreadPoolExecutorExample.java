package edu.elearning.executor;

import edu.elearning.common.Tasks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

        Thread[] tasks = Tasks.getTasks(10);
        for (Runnable r : tasks) {
            executor.execute(r);
        }
    }
}
