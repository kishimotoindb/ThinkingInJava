package com.fearlessbear.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Thread.join()方法
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread tr = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10*60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            tr.start();
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
