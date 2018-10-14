package ru.androidacademy.msk.threads.lecture4_code.background;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import ru.androidacademy.msk.threads.lecture4_code.ILoadingView;


/**
 * @author aleien on 14.10.18.
 */
public class UIRunnable implements Runnable {
    @NonNull private final WeakReference<ILoadingView> loadingRef;
    private final Long waitTime;

    public UIRunnable(@NonNull ILoadingView loadingView, Long waitTime) {
        loadingRef = new WeakReference<>(loadingView);
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        ILoadingView loadingView = loadingRef.get();
        if (loadingView != null) {
            loadingView.showProgress(false);
            loadingView.updateTime(waitTime);
        }
    }
}
