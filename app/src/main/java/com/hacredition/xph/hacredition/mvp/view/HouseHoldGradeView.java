package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.mvp.entity.HouseHoldDebtInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldGradeInfo;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by pc on 2017/3/14.
 */

public interface HouseHoldGradeView extends BaseView {

    void initColumnChart(HouseHoldGradeInfo info);

    void initDebtPirChart(List<HouseHoldDebtInfo> list);

    void initFondPirChart(List<HouseHoldFondInfo> list);

}
