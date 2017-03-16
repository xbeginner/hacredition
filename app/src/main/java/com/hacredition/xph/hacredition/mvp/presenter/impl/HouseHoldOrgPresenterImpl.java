package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.QueryCallback;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.CarInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldOrgInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.HouseHoldOrgInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.HouseHoldOrgPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldOrgQueryView;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/16.
 */

public class HouseHoldOrgPresenterImpl extends BasePresenterImpl<HouseHoldOrgQueryView,List<HouseHoldOrgInfo>>
        implements HouseHoldOrgPresenter,RequestCallBack<List<HouseHoldOrgInfo>> {

    private HouseHoldOrgInteractorImpl mInteractor;

    @Inject
    public HouseHoldOrgPresenterImpl(HouseHoldOrgInteractorImpl interactor){
        mInteractor = interactor;
    }

    @Override
    public void beforeRequest() {

    }

    @Override
    public void success(List<HouseHoldOrgInfo> infos) {
        mView.showOrgBubbles(infos);
    }

    @Override
    public void onError() {
         mView.showMsg();
    }

    @Override
    public void getHouseHoldOrgInfos(String idcard) {
          mInteractor.getHouseHoldOrgInfo(this,idcard);
    }
}
