package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.entity.BudgetItem;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfo;
import com.hacredition.xph.hacredition.mvp.entity.OutputInfo;
import com.hacredition.xph.hacredition.mvp.interactor.BudgetQueryInteractor;

import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2017/3/21.
 */

public class BudgetQueryInteractorImpl implements BudgetQueryInteractor {

    private Observable mObservable;

    @Inject
    public BudgetQueryInteractorImpl(){

    }



    @Override
    public void getBudgetQueryInfo(final RequestCallBack callBack, final String idcard) {
        BudgetInfo info = initInfo(0);
        callBack.success(info);
    }

    @Override
    public void getBudgetQueryInfoByTime(final RequestCallBack callBack,String idcard,int year,int month) {
        System.out.println(year);
        System.out.println(month);
    }

    @Override
    public void getBudgetQueryInfoByType(final RequestCallBack callBack, final String idcard,final int type) {
        BudgetInfo info = initInfo(type);
        callBack.success(info);
    }

    /**
     * 0 全部
     * 1 收入信息
     * 2 支出信息
     * @param type
     */
    private BudgetInfo initInfo(int type){

        float incomeSum = 0;
        float outputSum = 0;
        float sum = 0;
        List<BudgetItem> items = new ArrayList<BudgetItem>();

        BudgetInfo info = new BudgetInfo();

        DaoSession session = App.getmDaoSession();
        List<IncomeInfo> incomeInfos = session.getIncomeInfoDao().loadAll();
        List<OutputInfo> outputInfos = session.getOutputInfoDao().loadAll();

            for(IncomeInfo i:incomeInfos){
                if(type!=2) {
                    BudgetItem item = new BudgetItem();
                    item.setInfo(i.getIncomeInfo());
                    item.setLog(0);
                    item.setSum(i.getIncomeSum());
                    item.setTime(Date.valueOf(i.getIncomeTime()));
                    item.setType(i.getIncomeType());
                    items.add(item);
                }
                incomeSum += i.getIncomeSum();
            }



            for(OutputInfo i:outputInfos){
                if(type!=1) {
                    BudgetItem item = new BudgetItem();
                    item.setInfo(i.getOutputInfo());
                    item.setLog(1);
                    item.setSum(i.getOutputSum());
                    item.setTime(Date.valueOf(i.getOutputTime()));
                    item.setType(i.getOutputType());
                    items.add(item);
                }
                outputSum += i.getOutputSum();
        }
        sum = incomeSum-outputSum;
        info.setSum(sum);
        info.setIncomeSum(incomeSum);
        info.setOutputSum(outputSum);
        info.setBudgetItemList(items);
        return info;
    }
}
