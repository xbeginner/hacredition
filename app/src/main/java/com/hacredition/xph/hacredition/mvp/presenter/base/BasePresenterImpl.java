package com.hacredition.xph.hacredition.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

/**
 * Created by pc on 2017/1/16.
 */

public class BasePresenterImpl<T extends BaseView,E> implements BasePresenter,RequestCallBack<E> {

    protected T myView;

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void attachView(@NonNull BaseView view) {
        myView = (T) view;
    }

    @Override
    public void beforeRequest() {
        myView.showProgress();
    }

    @Override
    public void success(E data) {
        myView.hideProgress();
    }

    @Override
    public void onError(String errorMsg) {
        myView.hideProgress();
        myView.showMsg(errorMsg);
    }
}
