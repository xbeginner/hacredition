package com.hacredition.xph.hacredition.di.component;

import android.app.Activity;
import android.content.Context;

import com.hacredition.xph.hacredition.di.module.FragmentModule;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.di.scope.PerFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.EntrepreneurshipFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.FiscalSpendFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HonourInfoFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HouseInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.InputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.MachineInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.NewsFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OperationalEntityFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OwnerShipFragment;

import dagger.Component;
import dagger.Provides;

/**
 * Created by pc on 2017/1/9.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent{

    @ContextLife("Activity")
    Context getActivitContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(NewsFragment newsFragment);

    void inject(InputFragment inputFragment);

    void inject(HouseInfoInputFragment houseInfoInputFragment);

    void inject(FiscalSpendFragment fiscalSpendFragment);

    void inject(OperationalEntityFragment operationalEntityFragment);

    void inject(EntrepreneurshipFragment entrepreneurshipFragment);

    void inject(OwnerShipFragment ownerShipFragment);

    void inject(HonourInfoFragment honourInfoFragment);

    void inject(MachineInfoInputFragment machineInfoInputFragment);
}
