package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.ServerItem;
import com.hacredition.xph.hacredition.mvp.interactor.impl.ServerInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.ServerPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.ServerView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/24.
 */

public class ServerPresenterImpl extends BasePresenterImpl<ServerView,List<ServerItem>>
implements ServerPresenter,RequestCallBack<List<ServerItem>> {

    private ServerInteractorImpl mIteractorImpl;

    @Inject
    public ServerPresenterImpl(ServerInteractorImpl iteractorImpl){
        mIteractorImpl = iteractorImpl;
    }

    @Override
    public void showServerItems() {
        mIteractorImpl.showServerItems(this);
    }

    @Override
    public void success(List<ServerItem> serverItems) {
        mView.showServers(serverItems);
    }

    @Override
    public void onError() {
        mView.showMsg();
    }
}
