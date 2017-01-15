package com.hacredition.xph.hacredition.mvp.ui.activity.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;

import com.hacredition.xph.hacredition.di.component.ActivityComponent;

import com.hacredition.xph.hacredition.di.component.DaggerActivityComponent;
import com.hacredition.xph.hacredition.di.module.ActivityModule;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.hacredition.xph.hacredition.utils.NetUtil;

import butterknife.ButterKnife;

/**
 * 定义一个基础的Activity，包含一些公用方法
 *
 * Created by pc on 2017/1/9.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected ActivityComponent mActivityComponent;

    private boolean mIsAddedView;

    private WindowManager mWindowManager = null;

    private View mNightView = null;

    protected T mPresenter;

    /**
     * 子类需实现方法
     * @param
     */

    public abstract int getLayoutId();

    public abstract void initInjector();

    public abstract void initViews();

     protected  Toolbar toolbar;

    protected BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果没有网络连接弹出提示
        NetUtil.isNetworkErrThenShowMsg();
        initActivityComponent();
        setNightOrDayMode();
        int layoutId = getLayoutId();
        setContentView(layoutId);


        initInjector();
        ButterKnife.bind(this);
        initToolBar();
        initBottomBar();
        //设定statusBar颜色，4.4以上
        setStatusBarTranslucent();
        initViews();

        if (mPresenter != null) {
            mPresenter.onCreate();
        }

    }



    // TODO:适配4.4
    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setStatusBarTranslucent() {
        //如果为5.0以上
        if(Build.VERSION.SDK_INT>=21){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&Build.VERSION.SDK_INT<21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        toolbar.setClipToPadding(true);
      //  toolbar.setFitsSystemWindows(true);
    }


    private void setNightOrDayMode() {
        if (MyUtils.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            initNightView();
            mNightView.setBackgroundResource(R.color.night_mask);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initNightView() {
        if (mIsAddedView) {
            return;
        }
        // 增加夜间模式蒙板
        WindowManager.LayoutParams nightViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mNightView = new View(this);
        mWindowManager.addView(mNightView, nightViewParam);
        mIsAddedView = true;
    }


    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }




    private void initBottomBar(){
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar_id);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home_page, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.pen_book, "录入"))
                .addItem(new BottomNavigationItem(R.drawable.graphique_statistics, "统计"))
                .addItem(new BottomNavigationItem(R.drawable.config_set, "设置"))
                .initialise();
    }
}
