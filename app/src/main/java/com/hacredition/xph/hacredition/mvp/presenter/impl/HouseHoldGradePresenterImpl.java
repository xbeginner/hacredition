package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.QueryCallback;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldDebtInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldGradeInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.HouseHoldGradeInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.HouseHoldGradePresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldGradeView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/14.
 */

public class HouseHoldGradePresenterImpl extends BasePresenterImpl<HouseHoldGradeView,Object>
    implements HouseHoldGradePresenter
    ,QueryCallback{

    private HouseHoldGradeInteractorImpl mInteractor;

    @Inject
    public HouseHoldGradePresenterImpl(HouseHoldGradeInteractorImpl interactor){
        mInteractor = interactor;
    }

    @Override
    public void getHouseHoldGradeInfo(String idcard) {
        mInteractor.getHouseHoldGradeInfo(this,idcard);
    }

    @Override
    public void getHouseHoldFondInfo(String idcard) {
        mInteractor.getHouseHoldFondInfo(this,idcard);
    }

    @Override
    public void getHouseHoldDebtInfo(String idcard) {
        mInteractor.getHouseHoldDebtInfo(this,idcard);
    }



    @Override
    public void queryHouseHoldGradeSuccessfully(HouseHoldGradeInfo info) {
         mView.initColumnChart(info);
    }

    @Override
    public void queryHouseHoldFondInfoSuccessfully(List<HouseHoldFondInfo> list) {
        mView.initFondPirChart(list);
    }

    @Override
    public void queryHouseHoldDebtInfoSuccessfully(List<HouseHoldDebtInfo> list) {
         mView.initDebtPirChart(list);
    }

    @Override
    public void queryFially() {
         mView.showMsg();
    }
}
