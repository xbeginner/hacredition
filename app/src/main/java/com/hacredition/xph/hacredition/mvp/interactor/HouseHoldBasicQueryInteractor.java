package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;

/**
 * Created by pc on 2017/2/27.
 */

public interface HouseHoldBasicQueryInteractor {

    HouseHoldBasicInfo getHouseHoldBasicInfo(String idcard);

}
