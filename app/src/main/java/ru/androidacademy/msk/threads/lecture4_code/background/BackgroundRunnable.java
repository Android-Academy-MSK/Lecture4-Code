package ru.androidacademy.msk.threads.lecture4_code.background;

import android.os.Handler;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import ru.androidacademy.msk.threads.lecture4_code.ILoadingView;
import ru.androidacademy.msk.threads.lecture4_code.utils.Utils;

/**
 * @author aleien on 14.10.18.
 */
public class BackgroundRunnable implements Runnable {
    @NonNull private final WeakReference<Handler> handlerRef;
    @NonNull private final WeakReference<ILoadingView> loadingRef;

    public BackgroundRunnable(@NonNull Handler handler, @NonNull ILoadingView loadingView) {
        handlerRef = new WeakReference<>(handler);
        loadingRef = new WeakReference<>(loadingView);
    }

    @Override
    public void run() {
        Long waitTime = Utils.imitateLoading(1000);

        if (Thread.interrupted()) return;
        Handler handler = handlerRef.get();
        ILoadingView loadingView = loadingRef.get();

        if (handler != null) {
            handler.post(new UIRunnable(loadingView, waitTime));
        }
    }

}
