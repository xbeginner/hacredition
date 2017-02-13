package com.hacredition.xph.hacredition.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.MyViewPagerAdatper;
import com.hacredition.xph.hacredition.mvp.ui.fragments.InputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.NewsFragment;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
        BottomNavigationBar.OnTabSelectedListener
        ,ViewPager.OnPageChangeListener {


    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.viewpager_id)  ViewPager viewPager;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    @ContextLife("Application")
    Context applicationContext;

    public static final int LOGIN_SUCCESS_CODE=0;

    public static final int LOGIN_FAIL_CODE=1;

    private InputFragment inputFragment;

    public static UserInfo mUserInfo;

    public static boolean inputItemInited = false;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector() {
       mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        //设置ViewPager
        List<Fragment> fragmentList = initFragmentList();
        List<String> titles = initFragmentTitles();
        viewPager.setAdapter(new MyViewPagerAdatper(getSupportFragmentManager(),titles,fragmentList));
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initBottomBar();


    }


    private void initBottomBar(){
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar_id);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home_page, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.pen_book, "录入"))
                .addItem(new BottomNavigationItem(R.drawable.graphique_statistics, "统计"))
                .addItem(new BottomNavigationItem(R.drawable.config_set, "设置"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }



    /**
     * 将Fragement加入list
     * @return
     */
    private List<Fragment> initFragmentList(){
        List<Fragment> viewList = new ArrayList<Fragment>();
        NewsFragment newsFragment = new NewsFragment();
        inputFragment = new InputFragment();
        viewList.add(newsFragment);
        viewList.add(inputFragment);
        return viewList;
    }


    private List<String> initFragmentTitles(){
        List<String> titles = new ArrayList<String>();
        for(int i=0;i<4;i++){
            titles.add(i+"");
        }
        return titles;
    }


    @Override
    public void onTabSelected(int position) {
       viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position){
            case 1:
                if(App.hasLogin==false){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent,LOGIN_SUCCESS_CODE);
                }else{
                    if(!inputItemInited){
                        inputFragment.showInputItems();
                    }
                }
                break;
            case 2:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (resultCode){
            case LOGIN_SUCCESS_CODE:{
                App.hasLogin = true;
                inputFragment.hideProgress();
                UserInfo userInfo = (UserInfo)intent.getSerializableExtra("userInfo");
                mUserInfo = userInfo;
                break;
            }
            case LOGIN_FAIL_CODE:{
                App.hasLogin = false;
                inputFragment.hideProgress();
                break;
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
         bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
