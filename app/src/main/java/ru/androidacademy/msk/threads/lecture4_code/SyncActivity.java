package ru.androidacademy.msk.threads.lecture4_code;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author aleien on 18.10.18.
 */
public class SyncActivity extends AppCompatActivity {

    private static final Object lock = new Object();
    private static boolean isLeft = true;

    @Nullable private Thread leftThread;
    @Nullable private Thread rightThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadable);
    }

    @Override
    protected void onStart() {
        super.onStart();

        leftThread = new Thread(new LeftLeg());
        rightThread = new Thread(new RightLeg());

        leftThread.start();
        rightThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (leftThread != null) leftThread.interrupt();
        if (rightThread != null) rightThread.interrupt();

        leftThread = null;
        rightThread = null;
    }

    private static class LeftLeg implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                synchronized (lock) {
                    if (isLeft) {
                        System.out.println("Left step");
                        isLeft = false;
                    }
                }
            }
        }
    }

    private static class RightLeg implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                synchronized (lock) {
                    if (!isLeft) {
                        System.out.println("Right step");
                        isLeft = true;
                    }
                }
            }
        }
    }
}
