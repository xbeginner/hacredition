package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.listener.DBOprationRequestCallback;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummaryDao;
import com.hacredition.xph.hacredition.mvp.interactor.NewsInteractor;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;
import com.hacredition.xph.hacredition.utils.BaseRunnable;
import com.hacredition.xph.hacredition.utils.MyUtils;


import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import rx.Subscription;
import rx.Observable;
import java.util.List;

import javax.inject.Inject;

import rx.functions.Func1;


/**
 * 用于Rx的处理
 * Created by pc on 2017/1/16.
 */

public class NewsInteractorImpl implements NewsInteractor<List<NewsSummary>> {
    @Inject
    public NewsInteractorImpl() {

    }
    @Override
    public void loadNewsFromNet(final RequestCallBack<List<NewsSummary>> listener, int lastNewsId) {
        RetrofitManager manager = RetrofitManager.getInstance();
        manager.getNewsListObservable(lastNewsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
                .subscribe(new Observer<List<NewsSummary>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<NewsSummary> list) {
                         listener.success(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                          listener.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public List<NewsSummary> loadNewsFromDB(int startPage,int limit) {
        DaoSession session = App.getmDaoSession();
        return session.getNewsSummaryDao().queryBuilder()
                .orderDesc(NewsSummaryDao.Properties.NewsId)
                .offset(startPage)
                .limit(limit)
                .list();
    }

    @Override
    public void saveNewsToDB(List<NewsSummary> items,final DBOprationRequestCallback listener){
        DaoSession session = App.getmDaoSession();
        AsyncSession asyncSession = session.startAsyncSession();
        asyncSession.setListener(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                listener.operationSuccessfully();
            }
        });
        asyncSession.runInTx(new NewsDBRunnbale(items,session));
    }


    class NewsDBRunnbale extends BaseRunnable<List<NewsSummary>,DaoSession> {

        public NewsDBRunnbale(List<NewsSummary> list, DaoSession daoSession) {
            super(list, daoSession);
        }

        @Override
        protected void runInRunnable() {
            for(NewsSummary n:mObject){
                //保存进数据库
                int i = mDaoSession.getNewsSummaryDao().queryBuilder().where(NewsSummaryDao.Properties.NewsId.eq(n.getNewsId())).list().size();
                if(i==0) {
                    mDaoSession.getNewsSummaryDao().insert(n);
                }
            }
        }
    }

}
