package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.interactor.NewsInteractor;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

/**
 * 用于Rx的处理
 * Created by pc on 2017/1/16.
 */

public class NewsInteractorImpl implements NewsInteractor<List<NewsSummary>> {
    @Inject
    public NewsInteractorImpl() {
    }
    @Override
    public Subscription loadNews(RequestCallBack<List<NewsSummary>> listener, String type, String id, int startPage) {
        return null;
    }
}
