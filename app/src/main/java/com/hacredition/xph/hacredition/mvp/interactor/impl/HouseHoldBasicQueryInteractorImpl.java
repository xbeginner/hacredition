package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.listener.QueryBaseItemResultCallback;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.interactor.HouseHoldBasicQueryInteractor;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2017/3/8.
 */

public class HouseHoldBasicQueryInteractorImpl implements HouseHoldBasicQueryInteractor
       {

    private static List<BaseAdapterItem> mList;

    @Inject
    public HouseHoldBasicQueryInteractorImpl(){

    }

    @Override
    public HouseHoldBasicInfo getHouseHoldBasicInfo(String idcard) {
        HouseHoldBasicInfo info = new HouseHoldBasicInfo();
        info.setChushengriqi("1990-01-01");
        info.setCongyenianxian(5);
        info.setFubingyi("否");
        info.setHunyinzhuangkuang("已婚");
        info.setSfzno("410909827363");
        info.setXingming("张三");
        return info;
    }



    @Override
    public void getHouseHoldOtherInfo(final QueryBaseItemResultCallback callbacks, String idcard, int type){
        RetrofitManager manager = RetrofitManager.getInstance();
        manager.getHouseHoldOtherInfoItems(idcard,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BaseAdapterItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<BaseAdapterItem> list) {
                        callbacks.querySuccessfully(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
