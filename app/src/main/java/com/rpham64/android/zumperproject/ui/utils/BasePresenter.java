package com.rpham64.android.zumperproject.ui.utils;

import com.rpham64.android.zumperproject.ApplicationController;
import com.rpham64.android.zumperproject.network.RestClient;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class BasePresenter<T> implements IPresenter<T> {

    private T mView;
    private CompositeSubscription mSubs = new CompositeSubscription();

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

        if (mSubs != null) mSubs.unsubscribe();
    }

    @Override
    public void addSubscription(Subscription sub) {
        mSubs.add(sub);
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
