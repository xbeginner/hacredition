package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.listener.QueryCallback;
import com.hacredition.xph.hacredition.mvp.entity.DelGradeDetailInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldDebtInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldGradeInfo;
import com.hacredition.xph.hacredition.mvp.entity.SumGradeDetailInfo;
import com.hacredition.xph.hacredition.mvp.interactor.HouseHoldGradeInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/14.
 */

public class HouseHoldGradeInteractorImpl implements HouseHoldGradeInteractor {

    @Inject
    public HouseHoldGradeInteractorImpl(){

    }

    @Override
    public void getHouseHoldGradeInfo(QueryCallback callBack, String idcard) {
        HouseHoldGradeInfo info = new HouseHoldGradeInfo();

        info.setName("name");
        info.setGrade("102.0");
        info.setSumGrade("20.0");
        info.setDelGrade("30.0");

        List<DelGradeDetailInfo> delList = new ArrayList<DelGradeDetailInfo>();
        DelGradeDetailInfo info1 = new DelGradeDetailInfo();
        info1.setDetail("贷款逾期");
        info1.setGrade(5);
        info1.setItemName("itemName1");
        DelGradeDetailInfo info2 = new DelGradeDetailInfo();
        info2.setDetail("没有参加保险");
        info2.setGrade(6);
        info2.setItemName("itemName2");
        delList.add(info1);
        delList.add(info2);

        List<SumGradeDetailInfo> sumList = new ArrayList<SumGradeDetailInfo>();
        SumGradeDetailInfo info3 = new SumGradeDetailInfo();
        info3.setDetail("收入超过XX");
        info3.setGrade(5);
        info3.setItemName("itemName1");
        SumGradeDetailInfo info4 = new SumGradeDetailInfo();
        info4.setDetail("按时交纳电费");
        info4.setGrade(6);
        info4.setItemName("itemName2");
        sumList.add(info3);
        sumList.add(info4);

        info.setDelGradeDetailInfoList(delList);
        info.setSumGradeDetailInfoList(sumList);

        callBack.queryHouseHoldGradeSuccessfully(info);
    }

    @Override
    public void getHouseHoldFondInfo(QueryCallback callBack, String idcard) {
            List<HouseHoldFondInfo> list = new ArrayList<HouseHoldFondInfo>();
            for(int i=1;i<4;i++){
                HouseHoldFondInfo info = new HouseHoldFondInfo();
                info.setItem("资产"+i);
                info.setValue(i);
                info.setInfo("资产"+i);
                list.add(info);
            }
            callBack.queryHouseHoldFondInfoSuccessfully(list);
    }

    @Override
    public void getHouseHoldDebtInfo(QueryCallback callBack, String idcard) {
        List<HouseHoldDebtInfo> list = new ArrayList<HouseHoldDebtInfo>();
        for(int i=1;i<5;i++){
            HouseHoldDebtInfo info = new HouseHoldDebtInfo();
            info.setItem("负债"+i);
            info.setValue(i);
            info.setInfo("负债"+i);
            list.add(info);
        }
            callBack.queryHouseHoldDebtInfoSuccessfully(list);
    }
}
