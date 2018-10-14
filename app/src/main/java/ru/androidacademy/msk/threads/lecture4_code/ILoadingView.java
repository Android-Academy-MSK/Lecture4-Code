package ru.androidacademy.msk.threads.lecture4_code;

/**
 * @author aleien on 16.10.18.
 */
public interface ILoadingView {
    void showProgress(boolean shouldShow);

    void updateTime(Long waitTime);
}
