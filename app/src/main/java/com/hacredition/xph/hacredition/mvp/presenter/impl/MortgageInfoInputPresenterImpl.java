package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.CreditInfo;
import com.hacredition.xph.hacredition.mvp.entity.MortgageInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.CreditInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.MortgageInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class MortgageInfoInputPresenterImpl extends BasePresenterImpl<InputInfoView<MortgageInfo>,MortgageInfo>
            implements InputInfoPresenter<MortgageInfo>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public MortgageInfoInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(MortgageInfo mortgageInfo) {
        MortgageInfoInputInfo info = new MortgageInfoInputInfo(mortgageInfo);
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
