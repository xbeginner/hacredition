package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.DaoSession;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.QueryItem;
import com.hacredition.xph.hacredition.mvp.interactor.InputItemInteractor;
import com.hacredition.xph.hacredition.mvp.interactor.QueryItemInteractor;
import com.hacredition.xph.hacredition.repository.network.RetrofitManager;
import com.hacredition.xph.hacredition.utils.BaseRunnable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2017/2/13.
 */

public class QueryItemInteractorImpl implements QueryItemInteractor<List<QueryItem>> {

    @Inject
    public QueryItemInteractorImpl(){

    }

    @Override
    public void getQueryItemsByUserId(final RequestCallBack<List<QueryItem>> listener, int userId) {
        RetrofitManager manager = RetrofitManager.getInstance();
        manager.getQueryItems(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<QueryItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<QueryItem> queryItems) {
                        listener.success(queryItems);
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

}
