package com.hacredition.xph.hacredition.di.component;

import android.content.Context;

import com.hacredition.xph.hacredition.di.module.ServiceModule;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.di.scope.PerService;

import dagger.Component;

/**
 * Created by pc on 2017/1/9.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {


    @ContextLife("Service")
    Context getServiceContext();

    @ContextLife("Application")
    Context getApplicationContext();

}
