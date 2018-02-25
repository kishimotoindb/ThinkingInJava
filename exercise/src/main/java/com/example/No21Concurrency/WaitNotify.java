package com.example.No21Concurrency;

/**
 * Created by BigFaceBear on 2018.02.24
 */

public class WaitNotify {
    public static void main(String[] args) {

        final WaitNotify wn=new WaitNotify();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    wn.withdraw();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    wn.deposit();
                }
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    wn.mixed();
//                }
//            }
//        }).start();

    }

    static boolean deposited = false;

    synchronized void withdraw() {
        if (!deposited) {   //这里写成if是有问题的。在其他线程notify之后，即使deposited仍然是false，还是会发生取款操作。
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println("withdraw");
        deposited = false;
        notifyAll();    //不能notify自己
    }

    synchronized void deposit() {
        if (deposited) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println("deposit");
        deposited = true;
        notifyAll();
    }

//    synchronized void mixed() {
//        notifyAll();
//    }


}
