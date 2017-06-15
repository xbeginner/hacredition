package com.hacredition.xph.hacredition.mvp.ui.activity.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;

import com.hacredition.xph.hacredition.di.component.ActivityComponent;

import com.hacredition.xph.hacredition.di.component.DaggerActivityComponent;
import com.hacredition.xph.hacredition.di.module.ActivityModule;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;
import com.hacredition.xph.hacredition.utils.Constant;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.hacredition.xph.hacredition.utils.NetUtil;
import com.hacredition.xph.hacredition.utils.PreUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Collection;

import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

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

    protected NavigationTabBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果没有网络连接弹出提示
        NetUtil.isNetworkErrThenShowMsg();
        initActivityComponent();
        int layoutId = getLayoutId();
        setContentView(layoutId);

        initInjector();
        ButterKnife.bind(this);
        initToolBar();

        initViews();

        if (mPresenter != null) {
            mPresenter.onCreate();
        }

        //设定statusBar颜色，4.4以上
        setStatusBarTranslucent();
    }



    // TODO:适配4.4
    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setStatusBarTranslucent() {
        TypedArray array = getTheme().obtainStyledAttributes(new int[] {
                android.R.attr.colorPrimary,
                android.R.attr.colorBackground,
        });
        int backgroundColor = array.getColor(0, 0xFF00FF);
        //如果为5.0以上
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(backgroundColor);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&Build.VERSION.SDK_INT<21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            if(PreUtil.isNight()){
                tintManager.setStatusBarTintColor(Color.parseColor("#212121"));
            }else{
                tintManager.setStatusBarTintColor(Color.parseColor("#FF5252"));
            }
        }

    }


    public void setNightOrDayMode(int type) {
          if(type==0){
              PreUtil.setDay();
              setTheme(Constant.RESOURCES_DAYTHEME);
          }
          if(type==1){
              PreUtil.setNight();
              setTheme(Constant.RESOURCES_NIGHTTHEME);

          }
        this.finish();
        this.startActivity(new Intent(this, this.getClass()));
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }




    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_action_day:

                    PreUtil.setDay();
                    setTheme(Constant.RESOURCES_DAYTHEME);
                this.finish();
                this.startActivity(new Intent(this, this.getClass()));

                return true;

            case R.id.menu_action_night:

                PreUtil.setNight();
                setTheme(Constant.RESOURCES_NIGHTTHEME);


                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
