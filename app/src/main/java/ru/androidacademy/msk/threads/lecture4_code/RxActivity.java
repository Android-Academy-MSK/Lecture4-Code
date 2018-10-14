package ru.androidacademy.msk.threads.lecture4_code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.androidacademy.msk.threads.lecture4_code.utils.Utils;

/**
 * @author aleien on 14.10.18.
 */
public class RxActivity extends AppCompatActivity {

    private static final String TAG = RxActivity.class.getSimpleName();

    @Nullable private Disposable disposable;
    @Nullable private ProgressBar progressBar;
    @Nullable private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadable);

        progressBar = this.findViewById(R.id.progress);
        textView = this.findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }

        final long timeToWait = 2000L;
        disposable = Observable.fromCallable(() -> Utils.imitateLoading(timeToWait))
                               .subscribeOn(Schedulers.io())
                               .observeOn(AndroidSchedulers.mainThread())
                               // look at this as lambda, but even shorter
                               .subscribe(this::updateUI,
                                          error -> Log.e(TAG, error.getMessage(), error)
                               );
    }

    private void updateUI(Long timeWaited) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        if (textView != null) {
            textView.setText(this.getString(R.string.string_done, timeWaited));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressBar = null;
        textView = null;
    }

    public static void startActivity(Activity activity) {
        Intent activityIntent = new Intent(activity, RxActivity.class);
        activity.startActivity(activityIntent);
    }

}
