package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.Entrepreneurship;
import com.hacredition.xph.hacredition.mvp.entity.OwnerShip;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.EntrepreneurshipInputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.OwnerShipInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class OnwerShipInputPresenterImpl extends BasePresenterImpl<InputInfoView<OwnerShip>,OwnerShip>
            implements InputInfoPresenter<OwnerShip>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public OnwerShipInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(OwnerShip ownerShip) {
        OwnerShipInputInfo info = new OwnerShipInputInfo(ownerShip);
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
