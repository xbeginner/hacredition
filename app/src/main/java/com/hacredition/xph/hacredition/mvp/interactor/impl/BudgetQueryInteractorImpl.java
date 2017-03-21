package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.mvp.interactor.BudgetQueryInteractor;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by pc on 2017/3/21.
 */

public class BudgetQueryInteractorImpl implements BudgetQueryInteractor {
    @Override
    public void getBudgetQueryInfo() {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {

            }
        });
    }

    @Override
    public void getBudgetQueryInfoByTime(Date time) {

    }
}
