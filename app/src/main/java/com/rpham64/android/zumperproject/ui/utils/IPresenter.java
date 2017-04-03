package com.rpham64.android.zumperproject.ui.utils;

import com.rpham64.android.zumperproject.network.RestClient;

import rx.Subscription;

/**
 * Created by Rudolf on 4/2/2017.
 */

public interface IPresenter<T> {
    void attachView(T mvpView);

    void detachView();

    void onResume();
    void onPause();
    void onDestroy();

    void addSubscription(Subscription sub);

    RestClient getRestClient();
    T getView();
}
