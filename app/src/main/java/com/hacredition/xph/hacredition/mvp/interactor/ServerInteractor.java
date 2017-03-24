package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.ServerItem;

import java.util.List;

/**
 * Created by pc on 2017/3/24.
 */

public interface ServerInteractor {

    void showServerItems(RequestCallBack<List<ServerItem>> callBack);

}
