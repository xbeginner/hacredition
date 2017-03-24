package com.hacredition.xph.hacredition.mvp.presenter;

import java.util.Date;

/**
 * Created by pc on 2017/3/21.
 */

public interface BudgetQueryPresenter {

    void getBudgetInfo(String idcard);

    void getBudgetInfoByTime(String idcard,Date time);

    void getBudgetInfoByType(String idcard,int type);

}
