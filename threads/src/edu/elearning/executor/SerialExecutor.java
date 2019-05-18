package edu.elearning.executor;

import edu.elearning.common.Utils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public synchronized void execute(final Runnable r) {
        tasks.offer(() -> {
            try {
                r.run();
            } finally {
                scheduleNext();
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }


    public static void main(String[] args) {
        Runnable[] runnables = new Runnable[10];
        for (int i = 0; i < runnables.length; i++) {
            runnables[i] = () -> print();//System.out.println(Thread.currentThread().getName());
        }

        SerialExecutor executor = new SerialExecutor(Executors.newFixedThreadPool(10));

        Arrays.stream(runnables).forEach(r -> executor.execute(r));
//        executor.shutdown();
    }

    private static void print() {
        Utils.sleep(3);
        Utils.printMessage("going to sleep..");
        Utils.sleep(3);
    }
}