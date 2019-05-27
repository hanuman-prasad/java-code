package edu.elearning.executor;

import static edu.elearning.common.Utils.sleep;

public class UncaughtHandler {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runner());

        t.start();

        t.setUncaughtExceptionHandler(new HandleThreadTermination());

        t.join();

        System.out.println("state : " + t.getState());
        System.out.println("is alive : " + t.isAlive());

        System.out.println("main thread exiting..");

    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            System.out.println(Integer.parseInt("111"));
            System.out.println(Integer.parseInt("-222"));
            sleep(5);
            System.out.println(Integer.parseInt("000333"));
            System.out.println(Integer.parseInt("444t"));
            System.out.println(Integer.parseInt("555"));

        }
    }

    static class HandleThreadTermination implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + " terminated due to : " + e.getMessage());
        }
    }
}
