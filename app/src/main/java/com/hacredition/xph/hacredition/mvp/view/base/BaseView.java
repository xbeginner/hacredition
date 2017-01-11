package com.hacredition.xph.hacredition.mvp.view.base;

import android.support.annotation.NonNull;

import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;

/**
 * Created by pc on 2017/1/9.
 */

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showMsg(String message);
    

}
