package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

/**
 * Created by pc on 2017/2/9.
 */

public interface LoginView extends BaseView {

    void login();


    void showLoginErrorInfo(String msg);



}
