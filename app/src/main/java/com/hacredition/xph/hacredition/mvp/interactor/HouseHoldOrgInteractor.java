package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.RequestCallBack;

/**
 * Created by pc on 2017/3/16.
 */

public interface HouseHoldOrgInteractor {

    void getHouseHoldOrgInfo(RequestCallBack callBack,String idcard);

}
