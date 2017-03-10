package com.hacredition.xph.hacredition.mvp.presenter;

import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;

import java.util.List;

/**
 * Created by pc on 2017/3/8.
 */

public interface HouseHoldBasicQueryPresenter {

    HouseHoldBasicInfo getHouseHoldBasicInfo(String idcard);

    void getHouseHoldOtherInfo(String idcard,int type);

}
