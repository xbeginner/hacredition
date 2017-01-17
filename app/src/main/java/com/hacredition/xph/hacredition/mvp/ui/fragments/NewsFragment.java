package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.NewsView;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.List;

import butterknife.BindView;


public class NewsFragment extends BaseFragment implements NewsView {




    @BindView(R.id.news_recyclerview_id)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view_id)
    TextView textView;
    @BindView(R.id.progress_bar_id)
    ProgressBar progressBar;
    @BindView(R.id.swipe_refresh_layout_id)
    SwipeRefreshLayout swipeRefreshLayout;



    /**
     * 实现注入
     */
    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initViewPager(List<String> newsTitles) {

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

    @Override
    public void initPresenter(BasePresenter presenter) {

    }
}
