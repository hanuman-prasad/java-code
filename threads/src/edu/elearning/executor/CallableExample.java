package edu.elearning.executor;

import java.util.Random;
import java.util.concurrent.*;

public class CallableExample implements Callable<String> {

    private final String str;

    public CallableExample(String str) {
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        return str.toLowerCase();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();
        int strLen = 20;
        char[] chars = new char[strLen];

        for (int i = 0; i < strLen; i++) {

            chars[i] = (char)(random.nextInt(26) + 65);
        }

        String str = new String(chars);
        CallableExample callableExample = new CallableExample(str);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(callableExample);

        String s = submit.get();

        System.out.println("Old - " + str);
        System.out.println("New - " + s);

        executorService.shutdown();

    }
}
