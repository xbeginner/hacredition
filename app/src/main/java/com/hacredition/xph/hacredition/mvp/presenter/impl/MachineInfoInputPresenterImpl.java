package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.entity.MachineInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.HouseInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.MachineInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class MachineInfoInputPresenterImpl extends BasePresenterImpl<InputInfoView<MachineInfo>,MachineInfo>
            implements InputInfoPresenter<MachineInfo>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public MachineInfoInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(MachineInfo machineInfo) {
        MachineInputInfo info = new MachineInputInfo(machineInfo);
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
