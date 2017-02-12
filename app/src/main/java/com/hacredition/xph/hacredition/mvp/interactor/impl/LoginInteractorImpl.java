package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.interactor.LoginInteractor;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2017/2/9.
 */

public class LoginInteractorImpl implements LoginInteractor<UserInfo> {

    @Inject
    public LoginInteractorImpl(){

    }

    @Override
    public void getLoginInfo(final RequestCallBack listener,String username, String password) {
        //利用网络加载数据
        RetrofitManager manager = RetrofitManager.getInstance();
        manager.getUserInfo(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        listener.success(userInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void saveUserInfoToDB(UserInfo userInfo) {
        DaoSession session = App.getmDaoSession();
        session.getUserInfoDao().deleteAll();
        userInfo.setLastLoginTime(new Date());
        session.getUserInfoDao().insert(userInfo);
    }
}
