package com.example.No21Concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by BigFaceBear on 2018.02.24
 */

public class BlockingQueueTransition {

    public static void main(String[] args) {
        new ProductThread().start();
        new ConsumeThread().start();
    }

    private static BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);

    private static class ProductThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    String e = i + "";
                    bq.put(e);
                    System.out.println("put " + e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ConsumeThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    String take = bq.take();
                    System.out.println("take " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

