package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.DBOprationRequestCallback;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;

import java.util.List;

import rx.Subscription;

/**
 * Created by pc on 2017/1/16.
 */

public interface NewsInteractor<T> {

    void loadNewsFromNet(RequestCallBack<T> listener, int lastNewsId);

    List<NewsSummary> loadNewsFromDB(int start,int limit);

    public void saveNewsToDB(List<NewsSummary> items,final DBOprationRequestCallback listener);

}
