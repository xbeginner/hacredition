package com.hacredition.xph.hacredition.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;
import com.hacredition.xph.hacredition.utils.MyUtils;


import java.util.Collection;

import rx.Subscription;

/**
 * Created by pc on 2017/1/16.
 */

public class BasePresenterImpl<T extends BaseView,E> implements BasePresenter,RequestCallBack<E> {

    protected T mView;
    protected Subscription mSubscription;


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestory() {
        MyUtils.cancelSubscription(mSubscription);
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (T) view;
    }

    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void success(E data) {
        mView.hideProgress();
    }

    @Override
    public void onError() {
        mView.hideProgress();
        mView.showMsg();
    }
}
