package com.hacredition.xph.hacredition.utils;

/**
 * Created by pc on 2017/2/13.
 */

public abstract class BaseRunnable<T,E> implements Runnable {

    protected T mObject;

    protected E mDaoSession;

    public BaseRunnable(T object,E daoSession){
        mObject = object;
        mDaoSession = daoSession;
    }

    @Override
    public void run() {
        runInRunnable();
    }


    protected abstract void runInRunnable();
}
