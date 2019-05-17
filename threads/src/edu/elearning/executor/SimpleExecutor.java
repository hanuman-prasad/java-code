package edu.elearning.executor;

import java.util.concurrent.Executor;

public class SimpleExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }

    public static void main(String[] args) {
        Executor executor = new SimpleExecutor();
        executor.execute(() -> System.out.println("simplest executor of all time.."));
    }
}
