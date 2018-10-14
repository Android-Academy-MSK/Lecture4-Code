package ru.androidacademy.msk.threads.lecture4_code;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import ru.androidacademy.msk.threads.lecture4_code.utils.Utils;

/**
 * @author aleien on 14.10.18.
 */
public class AsyncTaskActivity extends AppCompatActivity implements ILoadingView {

    @Nullable private AsyncTask<Long, Void, Long> asyncTask;
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

        showProgress(true);

        asyncTask = new LoadingAsyncTask(this);
        asyncTask.execute(1000L);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (asyncTask != null) {
            asyncTask.cancel(true);
            asyncTask = null;
        }
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
        Intent activityIntent = new Intent(activity, AsyncTaskActivity.class);
        activity.startActivity(activityIntent);
    }

    private static class LoadingAsyncTask extends AsyncTask<Long, Void, Long> {
        @NonNull private final WeakReference<ILoadingView> loadingRef;

        LoadingAsyncTask(ILoadingView loadingView) {
            loadingRef = new WeakReference<>(loadingView);
        }

        @Override
        protected Long doInBackground(Long... waitTime) {
            return Utils.imitateLoading(waitTime[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            ILoadingView loadingView = loadingRef.get();
            if (loadingView != null) {
                loadingView.showProgress(false);
                loadingView.updateTime(aLong);
            }
        }
    }

}
