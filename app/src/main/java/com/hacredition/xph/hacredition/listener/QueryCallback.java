package com.hacredition.xph.hacredition.listener;

import com.hacredition.xph.hacredition.mvp.entity.HouseHoldDebtInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldGradeInfo;

import java.util.List;

/**
 * Created by pc on 2017/2/21.
 */

public interface QueryCallback {

    void queryHouseHoldGradeSuccessfully(HouseHoldGradeInfo info);

    void queryHouseHoldFondInfoSuccessfully(List<HouseHoldFondInfo> list);

    void queryHouseHoldDebtInfoSuccessfully(List<HouseHoldDebtInfo> list);

    void queryFially();

}
