package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.interactor.LoginInteractor;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/9.
 */

public class LoginInteractorImpl implements LoginInteractor<UserInfo> {

    @Inject
    public LoginInteractorImpl(){

    }

    @Override
    public UserInfo getLoginInfo() {
        UserInfo userInfo = new UserInfo();
        return userInfo;

    }
}
