package com.hacredition.xph.hacredition.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.Collection;

/**
 * Created by pc on 2017/1/9.
 */

public interface BasePresenter  {

    void onCreate();

    void onDestory();

    void attachView(@NonNull BaseView view);

}
