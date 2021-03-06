package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfo;
import com.hacredition.xph.hacredition.mvp.entity.OutputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.IncomeInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.OutputInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class OutputInfoInputPresenterImpl extends BasePresenterImpl<InputInfoView<OutputInfo>,OutputInfo>
            implements InputInfoPresenter<OutputInfo>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public OutputInfoInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(OutputInfo outputInfo) {
        System.out.println("outputInfo:"+outputInfo.getOutputInfo());
        OutputInfoInputInfo info = new OutputInfoInputInfo(outputInfo);
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
