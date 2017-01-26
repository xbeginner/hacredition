package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.RequestCallBack;

import rx.Subscription;

/**
 * Created by pc on 2017/1/16.
 */

public interface NewsInteractor<T> {

    Subscription loadNewsFromNet(RequestCallBack<T> listener, int lastNewsId);

    Subscription loadNewsFromDB(int start,int limit);
}
