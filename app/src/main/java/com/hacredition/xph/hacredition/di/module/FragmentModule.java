package com.hacredition.xph.hacredition.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2017/1/9.
 */


@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment){
        this.mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideActivityContext(){
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Activity provideActivity(){
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment(){
        return mFragment;
    }
}
