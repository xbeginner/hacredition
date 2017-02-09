package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.mvp.entity.UserInfo;

/**
 * Created by pc on 2017/2/9.
 */

public interface LoginInteractor<T> {

    UserInfo getLoginInfo();

}
