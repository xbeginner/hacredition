package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.interactor.NewsDetailInteractor;
import com.hacredition.xph.hacredition.mvp.interactor.impl.NewsDetailInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.NewsDetailPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.NewsDetailView;

import javax.inject.Inject;

/**
 * Created by xikai on 2017/2/3.
 */

public class NewsDetailPresenterImpl extends BasePresenterImpl<NewsDetailView,NewsDetail>
        implements NewsDetailPresenter,RequestCallBack<NewsDetail> {

    private NewsDetailInteractor<NewsDetail> mNewsDetailInteractor;

    @Inject
    public NewsDetailPresenterImpl(NewsDetailInteractorImpl newsDetailInteractorImpl ){
        mNewsDetailInteractor = newsDetailInteractorImpl;
    }


    @Override
    public void showNewsDetail(int newsId) {

        mSubscription = mNewsDetailInteractor.loadNewsDetail(this,newsId);
    }

    @Override
    public void success(NewsDetail newsDetail) {
        mView.showNewsDetail(newsDetail);
    }

    @Override
    public void onError(String errorMsg) {
         mView.showMsg(errorMsg);
    }


}
