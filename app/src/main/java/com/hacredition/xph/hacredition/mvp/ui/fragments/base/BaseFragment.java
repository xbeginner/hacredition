package com.hacredition.xph.hacredition.mvp.ui.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.di.component.DaggerFragmentComponent;
import com.hacredition.xph.hacredition.di.component.FragmentComponent;
import com.hacredition.xph.hacredition.di.module.FragmentModule;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.squareup.leakcanary.RefWatcher;

import org.reactivestreams.Subscription;

import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by pc on 2017/1/16.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    public FragmentComponent fragmentComponent;

    protected T mPresenter;

    private View mFragmentView;

    protected Subscription mSubscription;


    public abstract void initInjector();

    public abstract void initViews(View view);

    public abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((App)getActivity().getApplication()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
        initInjector();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         if(mFragmentView==null){
             mFragmentView = inflater.inflate(getLayoutId(),container,false);
             ButterKnife.bind(this,mFragmentView);
             initViews(mFragmentView);
         }
        return mFragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);

        if(mPresenter!=null){
            mPresenter.onDestory();
        }

    }
}
