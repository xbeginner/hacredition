package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.RequestCallBack;

import rx.Subscription;

/**
 * Created by xikai on 2017/2/3.
 */

public interface NewsDetailInteractor<T> {

    void loadNewsDetail(RequestCallBack<T> listener, int newsId);

}
