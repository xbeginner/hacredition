package com.hacredition.xph.hacredition.mvp.ui.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.MyViewPagerAdatper;
import com.hacredition.xph.hacredition.mvp.ui.fragments.NewsFragment;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BaseView,NewsFragment.OnFragmentInteractionListener {


    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.viewpager_id)  ViewPager viewPager;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    @ContextLife("Application")
    Context applicationContext;

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String message) {

    }


    /**
     * 将Fragement加入list
     * @return
     */
    private List<Fragment> initFragmentList(){
        List<Fragment> viewList = new ArrayList<Fragment>();
        NewsFragment newsFragment = NewsFragment.newInstance("1","2");
        viewList.add(newsFragment);
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
    public void onFragmentInteraction(Uri uri) {

    }
}
