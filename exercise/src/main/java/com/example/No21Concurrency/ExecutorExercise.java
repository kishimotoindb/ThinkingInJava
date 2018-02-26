package com.example.No21Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by BigFaceBear on 2018.02.26
 */

public class ExecutorExercise {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();

//        for (int i = 0; i < 10; i++) {
//            es.execute(new MyRunnable());
//        }

        Future<String> future = es.submit(new MyCallable());
        es.shutdown();
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyRunnable implements Runnable {
        int mIndex;
        static int sNumber;

        public MyRunnable() {
            mIndex = sNumber++;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.print(mIndex + "(" + i + ")    ");
            }
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "MyCallable";
        }
    }


}
