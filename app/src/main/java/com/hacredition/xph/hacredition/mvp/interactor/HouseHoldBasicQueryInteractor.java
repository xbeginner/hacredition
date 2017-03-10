package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.QueryBaseItemResultCallback;
import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;

import java.util.List;

/**
 * Created by pc on 2017/2/27.
 */

public interface HouseHoldBasicQueryInteractor {

    HouseHoldBasicInfo getHouseHoldBasicInfo(String idcard);
    void getHouseHoldOtherInfo(final QueryBaseItemResultCallback callbacks,String idcard, int type);

}
