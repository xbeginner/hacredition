package com.hacredition.xph.hacredition.mvp.presenter.impl;

import com.hacredition.xph.hacredition.listener.QueryCallback;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.entity.CarInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldDebtInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldGradeInfo;
import com.hacredition.xph.hacredition.mvp.inputInterface.impl.CarInfoInputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.impl.BudgetQueryInteractorImpl;
import com.hacredition.xph.hacredition.mvp.interactor.impl.InputInfoInteractorImpl;
import com.hacredition.xph.hacredition.mvp.presenter.BudgetQueryPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.InputInfoPresenter;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenterImpl;
import com.hacredition.xph.hacredition.mvp.view.BudgetQueryView;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/2/16.
 */

public class BudgetQueryPresenterImpl extends BasePresenterImpl<BudgetQueryView,BudgetInfo>
            implements BudgetQueryPresenter,RequestCallBack<BudgetInfo>{


    private BudgetQueryInteractorImpl mInteractor;


   @Inject
   public BudgetQueryPresenterImpl(BudgetQueryInteractorImpl budgetQueryInteractor){
       mInteractor = budgetQueryInteractor;
   }

    @Override
    public void success(BudgetInfo budgetInfo) {
         mView.showBudgetInfos(budgetInfo);
    }


    @Override
    public void onError() {
        mView.showMsg();
    }


    @Override
    public void getBudgetInfo(String idcard) {
        mInteractor.getBudgetQueryInfo(this,idcard);
    }

    @Override
    public void getBudgetInfoByTime(String idcard,int year,int month) {
        mInteractor.getBudgetQueryInfoByTime(this,idcard,year,month);
    }

    @Override
    public void getBudgetInfoByType(String idcard, int type) {
        mInteractor.getBudgetQueryInfoByType(this,idcard,type);
    }
}
