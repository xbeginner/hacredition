package com.hacredition.xph.hacredition.mvp.inputInterface.impl;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.Entrepreneurship;
import com.hacredition.xph.hacredition.mvp.entity.MachineInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.InputInfoInterface;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;

import javax.crypto.Mac;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2017/2/27.
 */

public class MachineInputInfo implements InputInfoInterface {

    private MachineInfo machineInfo;

    public MachineInputInfo(MachineInfo machineInfo){
          this.machineInfo = machineInfo;
    }


    @Override
    public void save(final SaveCallback saveCallback) {
         //保存
        RetrofitManager manager = RetrofitManager.getInstance();
        manager.saveMachine(machineInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        saveCallback.saveSuccessfully();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        saveCallback.saveFially();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
