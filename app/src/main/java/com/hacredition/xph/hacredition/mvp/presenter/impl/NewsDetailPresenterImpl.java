package com.hacredition.xph.hacredition.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.presenter.NewsDetailPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;
import com.hacredition.xph.hacredition.mvp.view.base.NewsDetailView;

import java.util.List;

/**
 * Created by xikai on 2017/2/3.
 */

public class NewsDetailPresenterImpl extends BasePresenterImpl<NewsDetailView,NewsDetail>
        implements NewsDetailPresenter,RequestCallBack<NewsDetail>
{


    @Override
    public void showNewsDetail() {

    }


}
