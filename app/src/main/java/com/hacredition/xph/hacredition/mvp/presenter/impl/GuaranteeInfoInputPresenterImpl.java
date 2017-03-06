package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.CreditInfo;
import com.hacredition.xph.hacredition.mvp.entity.GuaranteeInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.CreditInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.GuaranteeInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class GuaranteeInfoInputPresenterImpl extends BasePresenterImpl<InputInfoView<GuaranteeInfo>,GuaranteeInfo>
            implements InputInfoPresenter<GuaranteeInfo>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public GuaranteeInfoInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(GuaranteeInfo guaranteeInfo) {
        GuaranteeInfoInputInfo info = new GuaranteeInfoInputInfo(guaranteeInfo);
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
