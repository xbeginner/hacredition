package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
import com.hacredition.xph.hacredition.mvp.interactor.HouseHoldBasicQueryInteractor;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/8.
 */

public class HouseHoldBasicQueryInteractorImpl implements HouseHoldBasicQueryInteractor {

    @Inject
    public HouseHoldBasicQueryInteractorImpl(){

    }

    @Override
    public HouseHoldBasicInfo getHouseHoldBasicInfo(String idcard) {
        System.out.println("inter:HouseHoldBasicInfo");
        HouseHoldBasicInfo info = new HouseHoldBasicInfo();
        info.setChushengriqi("1990-01-01");
        info.setCongyenianxian(5);
        info.setFubingyi("否");
        info.setHunyinzhuangkuang("已婚");
        info.setSfzno("410909827363");
        info.setXingming("张三");
        return info;
    }
}
