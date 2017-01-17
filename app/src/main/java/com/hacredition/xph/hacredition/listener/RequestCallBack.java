package com.hacredition.xph.hacredition.listener;

/**
 * Created by pc on 2017/1/16.
 */

public interface RequestCallBack<T> {
    void beforeRequest();

    void success(T data);

    void onError(String errorMsg);
}
