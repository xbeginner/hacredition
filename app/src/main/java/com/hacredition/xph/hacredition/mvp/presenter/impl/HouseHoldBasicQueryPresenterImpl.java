package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.HouseHoldBasicQueryInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.HouseHoldBasicQueryPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldBasicQueryView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/8.
 */

public class HouseHoldBasicQueryPresenterImpl extends BasePresenterImpl<HouseHoldBasicQueryView,HouseHoldBasicInfo>
        implements HouseHoldBasicQueryPresenter
        ,RequestCallBack<HouseHoldBasicInfo> {

    private HouseHoldBasicQueryInteractorImpl interactorImpl;

    @Inject
    public HouseHoldBasicQueryPresenterImpl(HouseHoldBasicQueryInteractorImpl impl){
        interactorImpl = impl;
    }

    @Override
    public HouseHoldBasicInfo getHouseHoldBasicInfo(String idcard) {
        System.out.println("presenter:getHouseHoldBasicInfo");
        return interactorImpl.getHouseHoldBasicInfo(idcard);
    }

    @Override
    public void success(HouseHoldBasicInfo info) {
        mView.showHouseHoldBasicInfo(info);
    }
}
