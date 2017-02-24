package com.hacredition.xph.hacredition.mvp.presenter;

import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;

/**
 * Created by pc on 2017/2/16.
 */

public interface InputInfoPresenter<T> extends BasePresenter {

    void saveInputInfo(T t);

}
