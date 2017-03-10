package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.QueryBaseItemResultCallback;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.HouseHoldBasicQueryInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.HouseHoldBasicQueryPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldBasicQueryView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/8.
 */

public class HouseHoldBasicQueryPresenterImpl extends BasePresenterImpl<HouseHoldBasicQueryView,HouseHoldBasicInfo>
        implements HouseHoldBasicQueryPresenter
        ,RequestCallBack<HouseHoldBasicInfo>
        ,QueryBaseItemResultCallback {

    private HouseHoldBasicQueryInteractorImpl interactorImpl;

    @Inject
    public HouseHoldBasicQueryPresenterImpl(HouseHoldBasicQueryInteractorImpl impl){
        interactorImpl = impl;
    }

    @Override
    public HouseHoldBasicInfo getHouseHoldBasicInfo(String idcard) {
        return interactorImpl.getHouseHoldBasicInfo(idcard);
    }

    @Override
    public void getHouseHoldOtherInfo(String idcard,int type) {
        interactorImpl.getHouseHoldOtherInfo(this,idcard,type);
    }

    @Override
    public void success(HouseHoldBasicInfo info) {
        mView.showHouseHoldBasicInfo(info);
    }

    @Override
    public void querySuccessfully(List<BaseAdapterItem> items) {
        mView.updateHouseHoldBasicInfo(items);
    }

    @Override
    public void queryFailly() {
        mView.showMsg();
    }
}
