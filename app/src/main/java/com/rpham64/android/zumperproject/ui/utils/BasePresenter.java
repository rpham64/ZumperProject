package com.rpham64.android.zumperproject.ui.utils;

import com.rpham64.android.zumperproject.ApplicationController;
import com.rpham64.android.zumperproject.network.RestClient;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class BasePresenter<T> implements IPresenter<T> {

    private T mView;

    public BasePresenter() {

    }

    @Override
    public void attachView(T mvpView) {
        mView = mvpView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public RestClient getRestClient() {
        return ApplicationController.getInstance().getRestClient();
    }

    @Override
    public T getView() {
        return mView;
    }
}
