package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.QueryCallback;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldDebtInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldGradeInfo;

/**
 * Created by pc on 2017/3/14.
 */

public interface HouseHoldGradeInteractor {

    public void getHouseHoldGradeInfo(QueryCallback callBack, String idcard);

    public void getHouseHoldFondInfo(QueryCallback callBack, String idcard);

    public void getHouseHoldDebtInfo(QueryCallback callBack, String idcard);

}
