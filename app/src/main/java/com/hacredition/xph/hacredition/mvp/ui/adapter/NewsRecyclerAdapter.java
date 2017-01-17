package com.hacredition.xph.hacredition.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2017/1/17.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM = 1;
    public static final int TYPE_FOOTER = 2;

    private List<NewsSummary> summaryList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view;
        switch (viewType){
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
                return new NewsItemViewHolder(view);
            case TYPE_PHOTO_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_img, parent, false);
                return new NewsImgItemViewHolder(view);
            case TYPE_FOOTER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_footer, parent, false);
                return new NewsFooterViewHolder(view);
            default:
                throw new RuntimeException("there is no type that matches the type " +
                        viewType + " + make sure your using types correctly");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsSummary newsSummary = summaryList.get(position);
        switch (getItemViewType(position)){
            case TYPE_ITEM:
                setNewsItemValues((NewsItemViewHolder)holder,newsSummary);
            case TYPE_PHOTO_ITEM:
                setNewsImgItemValues((NewsImgItemViewHolder)holder,newsSummary);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public List<NewsSummary> getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(List<NewsSummary> summaryList) {
        this.summaryList = summaryList;
    }


    class NewsItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.news_title_id)
        TextView newsTitle;
        @BindView(R.id.news_subtitle_id)
        TextView newsSubTitle;
        @BindView(R.id.news_time_id)
        TextView newsTime;
        public NewsItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }


    class NewsImgItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.news_img_title_id)
        TextView newsImgTitle;
        @BindView(R.id.news_img_subtitle_id)
        TextView newsImgSubTitle;
        @BindView(R.id.news_img_time_id)
        TextView newsImgTime;
        @BindView(R.id.news_img_id)
        ImageView newsImg;
        public NewsImgItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }


    class NewsFooterViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.news_footer_progress_id)
        ProgressBar newsFooterProgressBar;
        @BindView(R.id.news_footer_text_id)
        TextView newsFooterText;
        public NewsFooterViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }


    private void setNewsItemValues(NewsItemViewHolder holder, NewsSummary summary) {
        holder.newsTime.setText(summary.getTime());
        holder.newsSubTitle.setText(summary.getSubTitle());
        holder.newsTitle.setText(summary.getTitle());
    }

    private void setNewsImgItemValues(NewsImgItemViewHolder holder, NewsSummary summary) {
        holder.newsImgTime.setText(summary.getTime());
        holder.newsImgSubTitle.setText(summary.getSubTitle());
        holder.newsImgTitle.setText(summary.getTitle());
    }
}
