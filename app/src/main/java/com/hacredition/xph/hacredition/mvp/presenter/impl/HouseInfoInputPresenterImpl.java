package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class HouseInfoInputPresenterImpl extends BasePresenterImpl<InputInfoView<HouseInfo>,HouseInfo>
            implements InputInfoPresenter<HouseInfo>{

    @Inject
    public HouseInfoInputPresenterImpl(){

    }

    @Override
    public void saveInputInfo(HouseInfo houseInfo) {

    }
}
