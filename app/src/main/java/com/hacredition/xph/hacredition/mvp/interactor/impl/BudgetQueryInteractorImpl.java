package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.entity.BudgetItem;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfo;
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfoDao;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummaryDao;
import com.hacredition.xph.hacredition.mvp.entity.OutputInfo;
import com.hacredition.xph.hacredition.mvp.entity.OutputInfoDao;
import com.hacredition.xph.hacredition.mvp.interactor.BudgetQueryInteractor;


import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
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
    public void getBudgetQueryInfo(RequestCallBack callBack,String idcard) {
        try {
            BudgetInfo info = initInfo(0);
            callBack.success(info);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void getBudgetQueryInfoByTime(final RequestCallBack callBack,String idcard,Date time) {
        String sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(time);
        String[] dates = sdate.split("-");
        int year = Integer.valueOf(dates[0]);
        int month = Integer.valueOf(dates[1])+1;
        BudgetInfo info = initInfoByTime(year,month);
        callBack.success(info);
    }

    @Override
    public void getBudgetQueryInfoByType(final RequestCallBack callBack, final String idcard,final int type) {
        try {
            BudgetInfo info = initInfo(type);
            callBack.success(info);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 0 全部
     * 1 收入信息
     * 2 支出信息
     * @param type
     */
    private BudgetInfo initInfo(int type) {
        try {
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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(i.getIncomeTime());
                    item.setTime(date);
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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(i.getOutputTime());
                    item.setTime(date);
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
            System.out.println(info.getBudgetItemList().size());
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    private BudgetInfo initInfoByTime(int year,int month){
        try {
            DaoSession session = App.getmDaoSession();
            float incomeSum = 0;
            float outputSum = 0;
            float sum = 0;
            String mMonth = month<10?"0"+month:month+"";
            String startDate = year+"-"+mMonth+"-1";
            String endDate = year+"-"+mMonth+"-31";

            List<BudgetItem> items = new ArrayList<BudgetItem>();
            BudgetInfo info = new BudgetInfo();
            List<IncomeInfo> incomeInfos = session.getIncomeInfoDao()
                    .queryBuilder()
                    .where(IncomeInfoDao.Properties.IncomeTime.between(startDate,endDate))
                    .list();
            List<OutputInfo> outputInfos = session.getOutputInfoDao()
                    .queryBuilder()
                    .where(OutputInfoDao.Properties.OutputTime.between(startDate,endDate))
                    .list();

            for(IncomeInfo i:incomeInfos){
                BudgetItem item = new BudgetItem();
                item.setInfo(i.getIncomeInfo());
                item.setLog(0);
                item.setSum(i.getIncomeSum());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(i.getIncomeTime());
                item.setTime(date);
                item.setType(i.getIncomeType());
                items.add(item);
                incomeSum += i.getIncomeSum();
            }

            for(OutputInfo i:outputInfos){
                BudgetItem item = new BudgetItem();
                item.setInfo(i.getOutputInfo());
                item.setLog(1);
                item.setSum(i.getOutputSum());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(i.getOutputTime());
                item.setTime(date);
                item.setType(i.getOutputType());
                items.add(item);
                outputSum += i.getOutputSum();
            }
            sum = incomeSum-outputSum;
            info.setSum(sum);
            info.setIncomeSum(incomeSum);
            info.setOutputSum(outputSum);
            info.setBudgetItemList(items);
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
