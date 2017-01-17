package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.RequestCallBack;

import org.reactivestreams.Subscription;

/**
 * Created by pc on 2017/1/16.
 */

public interface NewsInteractor<T> {

    Subscription loadNews(RequestCallBack<T> listener, String type, String id, int startPage);
}
