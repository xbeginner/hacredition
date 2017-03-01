package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.google.common.collect.ObjectArrays;
import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.inputInterface.InputInfoInterface;
import com.hacredition.xph.hacredition.mvp.interactor.InputInfoInteractor;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/27.
 */

public class InputInfoInteractorImpl implements InputInfoInteractor {

    @Inject
    public InputInfoInteractorImpl(){

    }

    private InputInfoInterface inputInfoInterface;

    public void setInputInfoInterface(InputInfoInterface inputInfoInterface){
        this.inputInfoInterface = inputInfoInterface;
    }


    @Override
    public void saveInputInfo(SaveCallback saveCallback) {
        inputInfoInterface.save(saveCallback);
    }
}
