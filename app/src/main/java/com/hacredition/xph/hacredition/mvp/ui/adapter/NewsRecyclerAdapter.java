package com.hacredition.xph.hacredition.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.listener.OnItemClickListener;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.ui.adapter.base.BaseRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2017/1/17.
 */

public class NewsRecyclerAdapter extends BaseRecyclerViewAdapter<NewsSummary>{

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM = 1;
    public static final int TYPE_FOOTER = 2;

    public interface OnNewsItemClickListener extends OnItemClickListener{
        void onItemClick(View view,int position,boolean hasImg);
    }

    @Inject
    public NewsRecyclerAdapter() {
        super(null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view;
        switch (viewType){
            case TYPE_ITEM:
                view = getView(parent,R.layout.news_item);
                final NewsItemViewHolder holder = new NewsItemViewHolder(view);
                setItemOnClickEvent(holder,false);
                return holder;
            case TYPE_PHOTO_ITEM:
                view = getView(parent,R.layout.news_item_img);
                final NewsImgItemViewHolder imgHolder = new NewsImgItemViewHolder(view);
                setItemOnClickEvent(imgHolder,true);
                return imgHolder;
            case TYPE_FOOTER:
                view = getView(parent,R.layout.news_item_footer);
                return new NewsFooterViewHolder(view);
            default:
                throw new RuntimeException("there is no type that matches the type " +
                        viewType + " + make sure your using types correctly");
        }
    }


    private void setItemOnClickEvent(final RecyclerView.ViewHolder holder, final boolean hasImg) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     ((OnNewsItemClickListener) mOnItemClickListener).onItemClick(v, mList.get(holder.getLayoutPosition()).getNewsId(), hasImg);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof NewsItemViewHolder){
            setNewsItemValues((NewsItemViewHolder)holder,position);
        }
        if(holder instanceof NewsImgItemViewHolder){
            setNewsImgItemValues((NewsImgItemViewHolder)holder,position);
        }
        setItemAppearAnimation(holder,position,R.anim.anim_bottom_in);
    }

    @Override
    public int getItemViewType(int position) {
        //如果已经到数据的最后一条了则设为Footer
        if(isFooterShow&&isFooterPosition(position)){
            return TYPE_FOOTER;
        }else if(mList.get(position).isHasImg()){
            return TYPE_PHOTO_ITEM;
        }else{
            return TYPE_ITEM;
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (isShowingAnimation(holder)) {
            holder.itemView.clearAnimation();
        }
    }


    private boolean isShowingAnimation(RecyclerView.ViewHolder holder) {
        return holder.itemView.getAnimation() != null && holder.itemView
                .getAnimation().hasStarted();
    }




    class NewsItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.news_title_id)
        TextView newsTitle;
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

        public NewsFooterViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }




    private void setNewsItemValues(NewsItemViewHolder holder, int position) {
        NewsSummary summary = mList.get(position);
        holder.newsTime.setText(summary.getTime());
        holder.newsTitle.setText(summary.getTitle());

    }

    private void setNewsImgItemValues(NewsImgItemViewHolder holder, int position) {
        NewsSummary summary = mList.get(position);
        holder.newsImgTime.setText(summary.getTime());
        holder.newsImgTitle.setText(summary.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(summary.getImgSrc())
                .placeholder(R.drawable.ic_placeholder)
                .fitCenter()
                .into(holder.newsImg);
    }


    public void showFooter() {
        isFooterShow = true;
        //重新调用getItemCount获取size+1
        notifyItemInserted(getItemCount());
    }

    public void hideFooter() {
        isFooterShow = false;
        notifyItemRemoved(getItemCount());
    }

    private boolean isFooterPosition(int position) {
        return (getItemCount() - 1) == position;
    }

}
