package edu.elearning.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionExample extends RecursiveAction {

    private String workload = "";
    private static final int THRESHOLD = 4;


    public RecursiveActionExample(String workload) {
        this.workload = workload;
    }


    public static void main(String[] args) {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        RecursiveActionExample recursiveAction = new RecursiveActionExample("abc");
        commonPool.execute(recursiveAction);

    }


    @Override
    protected void compute() {
        if (workload.length() > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
            processing(workload);
        }
    }

    private List<RecursiveActionExample> createSubtasks() {
        List<RecursiveActionExample> subtasks = new ArrayList<>();

        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());

        subtasks.add(new RecursiveActionExample(partOne));
        subtasks.add(new RecursiveActionExample(partTwo));

        return subtasks;
    }

    private void processing(String work) {
        String result = work.toUpperCase();
        System.out.println("This result - (" + result + ") - was processed by "
                + Thread.currentThread().getName());
    }
}