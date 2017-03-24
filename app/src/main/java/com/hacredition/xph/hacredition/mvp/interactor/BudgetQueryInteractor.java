package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.RequestCallBack;

import java.util.Date;

/**
 * Created by pc on 2017/3/21.
 */

public interface BudgetQueryInteractor {

    void getBudgetQueryInfo(final RequestCallBack callBack, String idcard);

    void getBudgetQueryInfoByTime(final RequestCallBack callBack,String idcard,Date time);

    void getBudgetQueryInfoByType(final RequestCallBack callBack,final String idcard,final int type);

}
