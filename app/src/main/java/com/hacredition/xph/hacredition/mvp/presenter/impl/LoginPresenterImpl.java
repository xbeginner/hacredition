package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.LoginInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.LoginPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.LoginView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/9.
 */

public class LoginPresenterImpl extends BasePresenterImpl<LoginView,UserInfo>
        implements LoginPresenter,RequestCallBack<UserInfo> {

    private LoginInteractorImpl mLoginInteractorImpl;

    @Inject
    public LoginPresenterImpl(LoginInteractorImpl loginInteractorImpl){
        mLoginInteractorImpl = loginInteractorImpl;
    }

    @Override
    public void login(String username, String password) {
         mLoginInteractorImpl.getLoginInfo(this,username,password);
    }

    @Override
    public void success(UserInfo userInfo) {
         //将userInfo存入缓存
        App.hasLogin = true;
        mView.loginSuccessfully(userInfo.getUserId());
    }

    @Override
    public void onError() {
        App.hasLogin = false;
        mView.showLoginErrorInfo("服务器连接异常");
        super.onError();
    }
}
