package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputPresenter;
import com.hacredition.xph.hacredition.mvp.view.InputView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by xikai on 2017/2/9.
 */

public class InputPresenterImpl extends BasePresenterImpl<InputView,String> implements InputPresenter {


    private UserInfo userInfo;

    @Inject
    public InputPresenterImpl(){

    }


    @Override
    public void setInputItems(UserInfo userInfo) {
        List<String> items = userInfo.getInputItems();
        mView.set
    }
}
