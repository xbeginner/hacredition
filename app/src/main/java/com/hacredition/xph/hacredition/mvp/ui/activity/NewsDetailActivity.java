package com.hacredition.xph.hacredition.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.NewsDetail;
import com.hacredition.xph.hacredition.mvp.presenter.impl.NewsDetailPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;
import com.hacredition.xph.hacredition.mvp.view.NewsDetailView;

import javax.inject.Inject;

import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity implements NewsDetailView{

    @BindView(R.id.back_image_view_id)
    AppCompatImageView backImage;

    @BindView(R.id.news_detail_title_text)
    TextView newsDetailTitleText;

    @BindView(R.id.news_detail_body_text)
    TextView newsDetailContentText;

    @BindView(R.id.news_detail_time_text)
    TextView newsDetailTimeText;

    @BindView(R.id.news_detail_progress)
    ProgressBar progressBar;

    @BindView(R.id.news_detail_imgview_id)
    AppCompatImageView newsDetailImage;

    @Inject
    NewsDetailPresenterImpl mNewsDetailPresenter;

    @Inject
    Activity mActivity;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    private int newsId;

    private boolean hasImg;



    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initInjector() {
      mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        hasImg = bundle.getBoolean("hasImg");
        newsId = bundle.getInt("newsId");
        backImage.setVisibility(View.VISIBLE);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsDetailPresenter.onDestory();
                mActivity.finish();
            }
        });
        initPresenter();
    }

    @Override
    public void showNewsDetail(NewsDetail newsDetail) {
        if(hasImg){
            newsDetailImage.setVisibility(View.VISIBLE);
            Glide.with(activityContext)
                    .load(newsDetail.getImgSrc())
                    .placeholder(R.drawable.no_pic)
                    .fitCenter()
                    .into(newsDetailImage);
        }
        newsDetailTitleText.setText(newsDetail.getTitle());
        newsDetailTimeText.setText(newsDetail.getTime());
        newsDetailContentText.setText(newsDetail.getContent());
        hideProgress();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
         progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMsg() {
        hideProgress();
        Toast.makeText(activityContext,R.string.data_error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void initPresenter() {
        mNewsDetailPresenter.attachView(this);
        mNewsDetailPresenter.showNewsDetail(newsId);
    }
}
