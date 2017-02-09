package com.hacredition.xph.hacredition.mvp.presenter;

import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;

/**
 * Created by pc on 2017/2/9.
 */

public interface LoginPresenter extends BasePresenter {

    void login(String username,String password);

}
