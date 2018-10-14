package ru.androidacademy.msk.threads.lecture4_code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.androidacademy.msk.threads.lecture4_code.background.BackgroundRunnable;
import ru.androidacademy.msk.threads.lecture4_code.utils.Utils;

/**
 * @author aleien on 14.10.18.
 */
public class ThreadsActivity extends AppCompatActivity implements ILoadingView {

    @Nullable private Thread thread;
    @Nullable private ProgressBar progressBar;
    @Nullable private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadable);

        progressBar = findViewById(R.id.progress);
        textView = findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();

        thread = new Thread(new BackgroundRunnable(new Handler(), this));
        thread.start();

        showProgress(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (thread != null) {
            thread.interrupt();
        }
        thread = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressBar = null;
        textView = null;
    }


    @Override
    public void showProgress(boolean shouldShow) {
        Utils.setVisible(progressBar, shouldShow);
        Utils.setVisible(textView, !shouldShow);
    }

    @Override
    public void updateTime(Long waitTime) {
        if (textView != null) {
            textView.setText(getString(R.string.string_done, waitTime));
        }
    }

    public static void startActivity(Activity activity) {
        Intent activityIntent = new Intent(activity, ThreadsActivity.class);
        activity.startActivity(activityIntent);
    }
}
