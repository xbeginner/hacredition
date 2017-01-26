package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.interactor.NewsInteractor;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;
import com.hacredition.xph.hacredition.utils.MyUtils;

import rx.Subscriber;
import rx.Subscription;
import rx.Observable;
import java.util.List;

import javax.inject.Inject;

import rx.functions.Func1;

/**
 * 用于Rx的处理
 * Created by pc on 2017/1/16.
 */

public class NewsInteractorImpl implements NewsInteractor<List<NewsSummary>> {
    @Inject
    public NewsInteractorImpl() {

    }
    @Override
    public Subscription loadNewsFromNet(final RequestCallBack<List<NewsSummary>> listener, int lastNewsId) {
        RetrofitManager manager = RetrofitManager.getInstance();
        Subscription subscription = manager.getNewsListObservable(lastNewsId).subscribe(new Subscriber<List<NewsSummary>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                 listener.onError("net work error");
            }

            @Override
            public void onNext(List<NewsSummary> newsSummaries) {
                 listener.success(newsSummaries);
            }
        });
        return subscription;
    }


    @Override
    public Subscription loadNewsFromDB(int startPage,int limit) {
        return null;
    }
}
