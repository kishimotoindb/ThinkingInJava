package com.example.No21Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by BigFaceBear on 2018.02.26
 */

public class ExecutorExercise {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            es.execute(new MyRunnable());
        }
        es.shutdown();
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
                System.out.println(mIndex + "(" + i + ")");
            }
        }
    }

}
