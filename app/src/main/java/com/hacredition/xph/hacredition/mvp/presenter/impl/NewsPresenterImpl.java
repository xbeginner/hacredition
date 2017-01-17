package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.interactor.NewsInteractor;
import com.hacredition.xph.hacredition.mvp.interactor.impl.NewsInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.NewsPresenter;
import com.hacredition.xph.hacredition.mvp.view.NewsView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/1/16.
 */

public class NewsPresenterImpl extends BasePresenterImpl<NewsView,List<NewsSummary>> implements NewsPresenter,RequestCallBack<List<NewsSummary>> {

    private NewsInteractor<List<NewsSummary>> mNewsInteractor;

   @Inject
   public NewsPresenterImpl(NewsInteractorImpl newsInteractorImpl ){
       this.mNewsInteractor = newsInteractorImpl;

   }

    @Override
    public void refreshMore() {

    }

    @Override
    public void loadMore() {

    }
}
