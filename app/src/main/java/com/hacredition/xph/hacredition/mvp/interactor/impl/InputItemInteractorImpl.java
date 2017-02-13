package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.InputItemDao;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.interactor.InputItemInteractor;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;
import com.hacredition.xph.hacredition.utils.BaseRunnable;

import java.util.Date;
import java.util.List;
import java.util.concurrent.RunnableFuture;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2017/2/13.
 */

public class InputItemInteractorImpl implements InputItemInteractor<List<InputItem>> {

    @Inject
    public InputItemInteractorImpl(){

    }

    @Override
    public void getInputItemsByUserId(final RequestCallBack<List<InputItem>> listener, int userId) {
        RetrofitManager manager = RetrofitManager.getInstance();
        manager.getInputItems(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<InputItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<InputItem> inputItems) {
                        saveInputItemsToDB(inputItems);
                        listener.success(inputItems);
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


    private void saveInputItemsToDB(List<InputItem> inputItems){
        DaoSession session = App.getmDaoSession();
        session.startAsyncSession().runInTx(new InputIntemDBRunnbale(inputItems,session));
    }

    public void clearInputItemsFromDB(){
        DaoSession session = App.getmDaoSession();
        InputItemDao dao = session.getInputItemDao();
        dao.deleteAll();
    }


    /**
     * 用于异步保存数据的Runnable类
     */
    class InputIntemDBRunnbale extends BaseRunnable<List<InputItem>,DaoSession>{

        public InputIntemDBRunnbale(List<InputItem> list, DaoSession daoSession) {
            super(list, daoSession);
        }

        @Override
        protected void runInRunnable() {
            for(InputItem i:mObject){
                mDaoSession.getInputItemDao().insert(i);
            }
        }
    }
}
