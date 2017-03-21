package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

/**
 * Created by pc on 2017/3/21.
 */

public interface BudgetQueryView extends BaseView{

    void showBudgetInfos(BudgetInfo budgetInfo);


}
