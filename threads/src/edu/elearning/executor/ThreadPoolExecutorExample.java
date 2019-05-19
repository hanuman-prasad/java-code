package edu.elearning.executor;

import edu.elearning.common.Tasks;

import java.util.concurrent.*;

import static edu.elearning.common.Utils.sleep;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutorThreadFactory(), new ThreadPoolExecutorRejectedExecutionHandler());


        Thread[] tasks = Tasks.getTasks(20);
        for (int i = 0; i < tasks.length; i++) {

            Runnable r = tasks[i];
            System.out.println("submitting task : " +(i+1));
            executor.execute(r);
//                 sleep(1);
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

            System.out.println(((Thread)r).getName() + " rejecting because of queue size");
        }
    }
}
