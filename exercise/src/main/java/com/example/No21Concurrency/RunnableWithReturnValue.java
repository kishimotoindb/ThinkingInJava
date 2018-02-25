package com.example.No21Concurrency;

/**
 * Created by BigFaceBear on 2018.02.23
 */

public class RunnableWithReturnValue {

    public static void main(String[] args) {
        Thread tr = new Thread(new MyRunnable(new Listener() {
            @Override
            public void onResult() {
                System.out.println("onResult");
                System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
            }
        }));
        System.out.println("Thread.currentThread().isAlive() = " + tr.isAlive());

        tr.run();
        tr.start();


//        try {
//            Thread.sleep(1L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        while (true) {
//            if(!tr.isAlive()){
//                tr.start();
//            }
//        }

    }

    static class MyRunnable implements Runnable {

        Listener mListener;

        MyRunnable(Listener listener) {
            mListener = listener;
        }

        @Override
        public void run() {
            System.out.println("run");
            mListener.onResult();
        }
    }

    interface Listener {
        void onResult();
    }

    interface E extends Listener {
        void onResult();
    }
}
