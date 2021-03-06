package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.HouseInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputItemInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class HouseInfoInputPresenterImpl extends BasePresenterImpl<InputInfoView<HouseInfo>,HouseInfo>
            implements InputInfoPresenter<HouseInfo>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public HouseInfoInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(HouseInfo houseInfo) {
        HouseInfoInputInfo info = new HouseInfoInputInfo(houseInfo);
        mInputInfoImpl.setInputInfoInterface(info);
        mInputInfoImpl.saveInputInfo(this);
    }

    @Override
    public void saveSuccessfully() {
        mView.saveSuccessfully();
    }

    @Override
    public void saveFially() {
        mView.saveFailly();
    }


}
