package edu.elearning.common;

public class Utils {

    public static final long MAX = 100L;

    public static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            printInterruptMessage("in sleep method");
        }
    }

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            printInterruptMessage("in sleep method");
        }
    }

    public static String threadName() {
        return Thread.currentThread().getName();
    }

    public static void printMessage(String msg) {
        System.out.println(threadName() + " " + msg);
    }

    public static void printInterruptMessage(String msg) {
        printMessage("got interrupted " + msg + ". Interrupt status : " + Thread.currentThread().isInterrupted());
    }

}
