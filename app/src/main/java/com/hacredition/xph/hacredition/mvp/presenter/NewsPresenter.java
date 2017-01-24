package com.hacredition.xph.hacredition.mvp.presenter;

import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;

import java.util.List;

/**
 * Created by pc on 2017/1/16.
 */

public interface NewsPresenter extends BasePresenter {

    //上拉刷新
    void loadMore();

    //下拉加载更多
    void refreshMore();



}
