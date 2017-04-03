package com.rpham64.android.zumperproject.ui.utils;

import com.rpham64.android.zumperproject.network.RestClient;

/**
 * Created by Rudolf on 4/2/2017.
 */

public interface IPresenter<T> {
    void attachView(T mvpView);

    void detachView();

    void onResume();
    void onPause();
    void onDestroy();

    RestClient getRestClient();
    T getView();
}
