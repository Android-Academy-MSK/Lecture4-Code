package ru.androidacademy.msk.threads.lecture4_code.utils;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import ru.androidacademy.msk.threads.lecture4_code.BuildConfig;

/**
 * @author aleien on 14.10.18.
 */
public class Utils {
    private Utils() {}

    public static Long imitateLoading(long howLongToWait) {
        try {
            Thread.sleep(howLongToWait);
        } catch (InterruptedException e) {
            if (isDebug()) Log.e("Utils", e.getMessage(), e);
        }

        return howLongToWait;
    }

    public static void setVisible(@Nullable View view, boolean isVisible) {
        int visibility = isVisible ? View.VISIBLE : View.GONE;
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

}
