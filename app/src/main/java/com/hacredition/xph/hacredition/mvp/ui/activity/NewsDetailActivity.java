package com.hacredition.xph.hacredition.mvp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.TextView;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;

import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.news_detail_imgview_id)
    AppCompatImageView imageView;

    @BindView(R.id.news_detail_titleview_id)
    TextView newsDetailTextView;

    @BindView(R.id.news_detail_webview_id)
    WebView webView;

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
         int i =  getIntent().getIntExtra("newsId",0);
         newsDetailTextView.setText(i+"");
    }


}
