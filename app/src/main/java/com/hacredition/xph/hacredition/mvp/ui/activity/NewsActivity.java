package com.hacredition.xph.hacredition.mvp.ui.activity;

import android.os.Bundle;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

public class NewsActivity extends BaseActivity implements BaseView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {

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
}
