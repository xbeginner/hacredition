package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;

/**
 * Created by pc on 2017/2/9.
 */

public interface LoginInteractor<T> {

    void getLoginInfo(final RequestCallBack listener,String username, String password);

    void saveUserInfoToDB(UserInfo userInfo);

}
