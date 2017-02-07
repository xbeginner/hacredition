package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.interactor.NewsDetailInteractor;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by xikai on 2017/2/3.
 */

public class NewsDetailInteractorImpl implements NewsDetailInteractor<NewsDetail> {

    @Inject
    public NewsDetailInteractorImpl() {

    }
    @Override
    public void loadNewsDetail(final RequestCallBack listener, int newsId) {

//        //利用网络加载数据
        RetrofitManager manager = RetrofitManager.getInstance();
          manager.getNewsDetail(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsDetail value) {
                         listener.success(value);
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
}