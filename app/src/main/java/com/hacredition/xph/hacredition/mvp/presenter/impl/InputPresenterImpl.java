package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputItemInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputPresenter;
import com.hacredition.xph.hacredition.mvp.ui.activity.MainActivity;
import com.hacredition.xph.hacredition.mvp.view.InputView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by xikai on 2017/2/9.
 */

public class InputPresenterImpl extends BasePresenterImpl<InputView,List<InputItem>>
        implements InputPresenter,RequestCallBack<List<InputItem>> {


    private UserInfo userInfo;

    private InputItemInteractorImpl mInputItemInteractor;

    @Inject
    public InputPresenterImpl(InputItemInteractorImpl inputItemInteractor){
        mInputItemInteractor = inputItemInteractor;
    }


    @Override
    public void setInputItems(UserInfo userInfo) {
         mInputItemInteractor.getInputItemsByUserId(this,userInfo.getUserId());
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }

    @Override
    public void success(List<InputItem> list) {
        mView.setInputItem(list);
    }

    @Override
    public void onError() {
       mView.showMsg();
    }
}
