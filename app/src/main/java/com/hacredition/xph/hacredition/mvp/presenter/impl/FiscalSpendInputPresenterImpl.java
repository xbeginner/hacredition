package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.FiscalSpend;
import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.FiscalSpendInputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.HouseInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class FiscalSpendInputPresenterImpl extends BasePresenterImpl<InputInfoView<FiscalSpend>,FiscalSpend>
            implements InputInfoPresenter<FiscalSpend>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public FiscalSpendInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(FiscalSpend fiscalSpend) {
        FiscalSpendInputInfo info = new FiscalSpendInputInfo(fiscalSpend);
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
