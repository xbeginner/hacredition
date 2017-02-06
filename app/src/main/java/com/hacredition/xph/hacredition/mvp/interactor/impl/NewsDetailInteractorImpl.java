package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.interactor.NewsDetailInteractor;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by xikai on 2017/2/3.
 */

public class NewsDetailInteractorImpl implements NewsDetailInteractor<NewsDetail> {

    @Inject
    public NewsDetailInteractorImpl() {

    }
    @Override
    public Subscription loadNewsDetail(final RequestCallBack listener, int newsId) {

        //利用网络加载数据
        RetrofitManager manager = RetrofitManager.getInstance();
        return manager.getNewsDetail(newsId)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.onError("net work error");
                    }

                    @Override
                    public void onNext(NewsDetail newsDetail) {
                        listener.success(newsDetail);
                    }
                });
     }
}
