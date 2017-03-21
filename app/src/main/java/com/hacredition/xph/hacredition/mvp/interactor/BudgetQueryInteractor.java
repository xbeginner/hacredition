package com.hacredition.xph.hacredition.mvp.interactor;

import java.util.Date;

/**
 * Created by pc on 2017/3/21.
 */

public interface BudgetQueryInteractor {

    void getBudgetQueryInfo();

    void getBudgetQueryInfoByTime(Date time);

}
