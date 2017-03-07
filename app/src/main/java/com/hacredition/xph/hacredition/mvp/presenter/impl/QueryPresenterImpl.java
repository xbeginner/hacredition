package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.QueryItem;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputItemInteractorImpl;
import com.hacredition.xph.hacredition.mvp.interactor.impl.QueryItemInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.QueryPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputView;
import com.hacredition.xph.hacredition.mvp.view.QueryView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by xikai on 2017/2/9.
 */

public class QueryPresenterImpl extends BasePresenterImpl<QueryView,List<QueryItem>>
        implements QueryPresenter,RequestCallBack<List<QueryItem>> {

    private UserInfo userInfo;

    private QueryItemInteractorImpl mQueryItemInteractor;

    @Inject
    public QueryPresenterImpl(QueryItemInteractorImpl queryItemInteractor){
        mQueryItemInteractor = queryItemInteractor;
    }


    @Override
    public void setQueryItems(UserInfo userInfo) {
        mQueryItemInteractor.getQueryItemsByUserId(this, userInfo.getUserId());
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }

    @Override
    public void success(List<QueryItem> list) {
        mView.setQueryItem(list);
    }

    @Override
    public void onError() {
       mView.showMsg();
    }
}
