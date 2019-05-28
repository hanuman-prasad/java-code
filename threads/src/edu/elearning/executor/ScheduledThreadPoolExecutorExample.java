package edu.elearning.executor;

import edu.elearning.common.Tasks;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorExample
{
    public static void main(String[] args)
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        System.out.println("The time is : " + new Date());

        executor.schedule(new ScheduledTask(), 5 , TimeUnit.SECONDS);
        executor.schedule(new ScheduledTask(), 10 , TimeUnit.SECONDS);

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    static class ScheduledTask implements Runnable
    {
        @Override
        public void run()
        {
            try {
                System.out.println( Thread.currentThread().getName() + " : Time - " + new Date());
            }
            catch (Exception e) {
                System.out.println("Interrupted.." + Thread.currentThread().getName());
            }
        }
    }
}
