package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by pc on 2017/3/8.
 */

public interface HouseHoldBasicQueryView extends BaseView{

     void showHouseHoldBasicInfo(HouseHoldBasicInfo info);

     void queryHouseHoldBasicInfo(String idcard,String type);

     void updateHouseHoldBasicInfo(List<BaseAdapterItem> list);

}
