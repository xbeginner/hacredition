package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.mvp.entity.HouseHoldOrgInfo;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by pc on 2017/3/16.
 */

public interface HouseHoldOrgQueryView extends BaseView{

    void showOrgBubbles(List<HouseHoldOrgInfo> list);

}
