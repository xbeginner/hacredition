package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.LoginInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.LoginPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.LoginView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/9.
 */

public class LoginPresenterImpl extends BasePresenterImpl<LoginView,UserInfo> implements LoginPresenter {

    private LoginInteractorImpl mLoginInteractorImpl;

    @Inject
    public LoginPresenterImpl(LoginInteractorImpl loginInteractorImpl){
        mLoginInteractorImpl = loginInteractorImpl;
    }

    @Override
    public void login(String username, String password) {

          if (mLoginInteractorImpl.getLoginInfo()==null) {
              mView.showLoginErrorInfo("错误的用户名");
          }else {
              mView.loginSuccessfully(mLoginInteractorImpl.getLoginInfo());
          }

    }


}
