package com.hacredition.xph.hacredition.mvp.presenter;

import java.util.Date;

/**
 * Created by pc on 2017/3/21.
 */

public interface BudgetQueryPresenter {

    void getBudgetInfo();

    void getBudgetInfoByTime(Date time);

}
