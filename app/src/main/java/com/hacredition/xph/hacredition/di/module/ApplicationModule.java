package com.hacredition.xph.hacredition.di.module;

import android.app.Application;
import android.content.Context;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2017/1/9.
 * 提供Application级别的注入
 * 可以提供一个Application的Context
 */

@Module
public class ApplicationModule {

    private App mApplication;

    public ApplicationModule(App application){
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext(){
        return mApplication.getApplicationContext();
    }

}
