package edu.elearning.locks;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static edu.elearning.Utils.printMessage;

public class ReentrantLockWithCondition {

    private static final int MAX = 20;
    Stack<Integer> stack = new Stack<>();
    int CAPACITY = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockWithCondition lock = new ReentrantLockWithCondition();
        Thread pushThread = new Thread(lock::pushToStack);
        Thread popThread = new Thread(lock::popFromStack);

        pushThread.setName("PushThread");
        popThread.setName("PopThread");

        popThread.start();
        pushThread.start();

        pushThread.join();
        popThread.join();

    }

    public void pushToStack() {

        for (int i = 0; i < MAX; i++) {


            try {
                lock.lock();
                while (stack.size() == CAPACITY) {
                    stackFullCondition.await();
                }
                stack.push(i);
                printMessage(" pushing : " + i);
                stackEmptyCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void popFromStack() {

        for (int i = 0; i < MAX; i++) {
            try {
                lock.lock();
                while (stack.size() == 0) {
                    stackEmptyCondition.await();
                }
                printMessage(" poping : " + stack.pop());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                stackFullCondition.signalAll();
                lock.unlock();
            }
        }
    }
}
