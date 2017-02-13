package com.hacredition.xph.hacredition.mvp.interactor;

import com.hacredition.xph.hacredition.listener.RequestCallBack;

/**
 * Created by pc on 2017/2/13.
 */

public interface InputItemInteractor<T> {

    void  getInputItemsByUserId(final RequestCallBack<T> listener,int userId);

}
