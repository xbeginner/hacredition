package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldOrgInfo;
import com.hacredition.xph.hacredition.mvp.interactor.HouseHoldOrgInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/16.
 */

public class HouseHoldOrgInteractorImpl implements HouseHoldOrgInteractor {

    @Inject
    public HouseHoldOrgInteractorImpl(){

    }

    @Override
    public void getHouseHoldOrgInfo(RequestCallBack callBack,String idcard) {
        List<HouseHoldOrgInfo> list = new ArrayList<HouseHoldOrgInfo>();
        for(int i=1;i<6;i++){
            HouseHoldOrgInfo info = new HouseHoldOrgInfo();
            info.setInfo("银行评价"+i);
            info.setValue(i);
            info.setOrgName("银行"+i);
            list.add(info);
        }
        callBack.success(list);
    }

}
