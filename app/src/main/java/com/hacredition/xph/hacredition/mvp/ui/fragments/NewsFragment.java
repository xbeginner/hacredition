package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.common.LoadNewsType;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.mvp.presenter.impl.NewsPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.adapter.NewsRecyclerAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.NewsView;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;
import com.hacredition.xph.hacredition.utils.NetUtil;
import com.hacredition.xph.hacredition.utils.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class NewsFragment extends BaseFragment implements NewsView
        ,SwipeRefreshLayout.OnRefreshListener
        , NewsRecyclerAdapter.OnNewsItemClickListener{




    @BindView(R.id.news_recyclerview_id)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view_id)
    TextView emptyTextView;
    @BindView(R.id.progress_bar_id)
    ProgressBar progressBar;
    @BindView(R.id.swipe_refresh_layout_id)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    NewsRecyclerAdapter newsRecyclerAdapter;
    @Inject
    Activity newsFragmentActivity;
    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    NewsPresenterImpl newsPresenter;


    /**
     * 实现注入
     */
    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initSwipeRefreshLayout();
        initRecyclerView();
        initPresenter();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getIntArray(R.array.gplus_colors)
        );

    }


    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(newsFragmentActivity,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition >= totalItemCount - 1) {
                    newsRecyclerAdapter.showFooter();
                    newsPresenter.loadMore();
                }
            }

        });


        recyclerView.setAdapter(newsRecyclerAdapter);
        newsRecyclerAdapter.setOnItemClickListener(this);
        recyclerView.addItemDecoration(new RecyclerItemDecoration(2,2,getResources().getColor(R.color.dividerColor)));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }


    @Override
    public void showProgress() {
        newsFragmentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideProgress() {
        newsFragmentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void initPresenter() {
        mPresenter = newsPresenter;
        mPresenter.attachView(this);
        mPresenter.onCreate();
    }

    @Override
    public void onRefresh() {
       if(NetUtil.isNetworkAvailable()){
           //网络刷新数据
           newsPresenter.refreshMore();
       }else{
           NetUtil.isNetworkErrThenShowMsg();
           swipeRefreshLayout.setRefreshing(false);
       }
    }

    /**
     * 根据要求对List进行操作
     * @param newsSummaryList
     * @param loadType  对应的操作
     */
    @Override
    public void setNewsList(List<NewsSummary> newsSummaryList, @LoadNewsType.checker int loadType) {
        switch (loadType){
            case LoadNewsType.TYPE_INIT_SUCCESS:{
                newsRecyclerAdapter.setList(newsSummaryList);
                break;
            }
            case LoadNewsType.TYPE_REFRESH_SUCCESS:{
                newsRecyclerAdapter.setList(newsSummaryList);
                swipeRefreshLayout.setRefreshing(false);
                newsRecyclerAdapter.notifyDataSetChanged();
                break;
            }
            case LoadNewsType.TYPE_LOAD_MORE_SUCCESS:{
                newsRecyclerAdapter.hideFooter();
                swipeRefreshLayout.setRefreshing(false);
                if(newsSummaryList==null||newsSummaryList.size()==0){
                    Toast.makeText(activityContext,"没有更多了",Toast.LENGTH_SHORT).show();
                    recyclerView.scrollToPosition(newsRecyclerAdapter.getItemCount() - 1);

                }else {
                    newsRecyclerAdapter.addMore(newsSummaryList);
                }
                break;
            }
        }
        checkIsEmpty(newsSummaryList);
    }



    @Override
    public void onItemClick(View view, int newsId, boolean hasImg) {
        if(hasImg){
            goToImgNewsDetailActivity(newsId);
        }else{
            goToNewsDetailActivity(newsId);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }


    private void checkIsEmpty(List<NewsSummary> newsSummary) {
        if (newsSummary == null && newsRecyclerAdapter.getList() == null) {
            recyclerView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        }
    }



}
