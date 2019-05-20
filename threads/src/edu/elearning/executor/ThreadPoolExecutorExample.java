package edu.elearning.executor;

import edu.elearning.common.Tasks;

import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutorThreadFactory(), new ThreadPoolExecutorRejectedExecutionHandler());


        Runnable[] tasks = Tasks.getTasks(20);
        for (int i = 0; i < tasks.length; i++) {

            Runnable r = tasks[i];
            System.out.println("submitting task : " +(i+1));
            executor.execute(r);
            if (i==10) {
                List<Runnable> runnables = executor.shutdownNow();
                System.out.println("-------  " + runnables.size());
            }
        }

        executor.shutdown();
    }


    private static class ThreadPoolExecutorThreadFactory implements ThreadFactory {

        private static int i = 1;

        @Override
        public Thread newThread(Runnable r) {

            Thread thread = new Thread(r);
            thread.setName("Task--" + i++);

            return thread;
        }
    }

    private static class ThreadPoolExecutorRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            String reason = executor.isShutdown() ? "executor is shutdown!" : "queue is full";

            System.out.println((r).toString() + " rejecting because of " + reason);
        }
    }
}
