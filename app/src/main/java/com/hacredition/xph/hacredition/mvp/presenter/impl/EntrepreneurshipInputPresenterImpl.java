package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.Entrepreneurship;
import com.hacredition.xph.hacredition.mvp.entity.OperationalEntity;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.EntrepreneurshipInputInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.OperationalEntityInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class EntrepreneurshipInputPresenterImpl extends BasePresenterImpl<InputInfoView<Entrepreneurship>,Entrepreneurship>
            implements InputInfoPresenter<Entrepreneurship>,SaveCallback{

    private InputInfoInteractorImpl mInputInfoImpl;

    @Inject
    public EntrepreneurshipInputPresenterImpl(InputInfoInteractorImpl inputInfoImpl){
            this.mInputInfoImpl = inputInfoImpl;
    }

    @Override
    public void saveInputInfo(Entrepreneurship entrepreneurship) {
        EntrepreneurshipInputInfo info = new EntrepreneurshipInputInfo(entrepreneurship);
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
