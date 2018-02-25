package com.example.No21Concurrency;

/**
 * Created by BigFaceBear on 2018.02.22
 */

public class C21_2_2No1_Yield {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new YieldRunnable()).start();
        }

    }

    static class YieldRunnable implements Runnable {
        static int count = 0;
        int index;

        public YieldRunnable() {
            index = count++;
            System.out.println("No." + index + " began");
        }

        @Override
        public void run() {
            System.out.println("No." + index + " is running");
            Thread.yield();
            Thread.yield();
            Thread.yield();
            System.out.println("No." + index + " stopped");
        }


    }
}
