package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.FiscalSpend;
import com.hacredition.xph.hacredition.mvp.entity.OperationalEntity;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.FiscalSpendInputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.OperationalEntityInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class OperationalEntityInputPresenterImpl extends BasePresenterImpl<InputInfoView<OperationalEntity>,OperationalEntity>
            implements InputInfoPresenter<OperationalEntity>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public OperationalEntityInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(OperationalEntity operationalEntity) {
        OperationalEntityInputInfo entityInputInfo = new OperationalEntityInputInfo(operationalEntity);
        mInputInfoImpl.setInputInfoInterface(entityInputInfo);
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
