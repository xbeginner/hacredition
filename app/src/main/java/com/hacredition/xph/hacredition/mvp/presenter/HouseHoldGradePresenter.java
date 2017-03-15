package com.hacredition.xph.hacredition.mvp.presenter;

/**
 * Created by pc on 2017/3/14.
 */

public interface HouseHoldGradePresenter {

    void getHouseHoldGradeInfo(String idcard);

    //资产
    void getHouseHoldFondInfo(String idcard);

    //负债
    void getHouseHoldDebtInfo(String idcard);
}
