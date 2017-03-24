package com.hacredition.xph.hacredition.mvp.interactor.impl;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.ServerItem;
import com.hacredition.xph.hacredition.mvp.interactor.ServerInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pc on 2017/3/24.
 */

public class ServerInteractorImpl implements ServerInteractor {

    @Inject
    public ServerInteractorImpl(){

    }

    @Override
    public void showServerItems(RequestCallBack<List<ServerItem>> callBack) {
        List<ServerItem> serverItems = new ArrayList<ServerItem>();
        ServerItem item = new ServerItem();
        item.setImgSrc("http://192.168.0.82:8080/hacredition/licai.png");
        item.setItemName("BankService");
        item.setInfo("理财信息");
        serverItems.add(item);
        callBack.success(serverItems);
    }
}
