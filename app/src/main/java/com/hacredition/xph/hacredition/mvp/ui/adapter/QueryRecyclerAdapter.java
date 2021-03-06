package com.hacredition.xph.hacredition.mvp.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.listener.OnItemClickListener;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.QueryItem;
import com.hacredition.xph.hacredition.mvp.ui.adapter.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2017/1/17.
 */

public class QueryRecyclerAdapter extends BaseRecyclerViewAdapter<QueryItem>{

    private List<Integer> heights;

    public interface OnQueryItemClickListener extends OnItemClickListener{
        void onItemClick(View view, String ActivityName);
    }

    @Inject
    public QueryRecyclerAdapter() {
        super(null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent,R.layout.query_item);
        final String[] colors = parent.getContext().getResources().getStringArray(R.array.nbt_color);
        final QueryItemViewHolder holder = new QueryItemViewHolder(view,colors);
        setItemOnClickEvent(holder);
        return holder;
    }


    private void setItemOnClickEvent(final RecyclerView.ViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     ((OnQueryItemClickListener) mOnItemClickListener).onItemClick(v, mList.get(holder.getLayoutPosition()).getQueryItemFragmentName());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setQueryItemValues((QueryItemViewHolder)holder,position);
        setItemAppearAnimation(holder,position,R.anim.anim_bottom_in);
        getRandomHeight();
        ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams();
        params.height = heights.get(position);//把随机的高度赋予item布局
        holder.itemView.setLayoutParams(params);//把params设置给item布局
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






    class QueryItemViewHolder extends RecyclerView.ViewHolder{

//        @BindView(R.id.query_image_id)
//        ImageView queryImg;
        @BindView(R.id.query_title_id)
        TextView queryTitleText;
//        @BindView(R.id.query_info_id)
//        TextView queryInfoText;


        public QueryItemViewHolder(View view,String[] colors){
            super(view);
            ButterKnife.bind(this,view);
            Random random = new Random();
            view.setBackgroundColor( Color.parseColor(colors[random.nextInt(5)]));
        }
    }


    private void setQueryItemValues(QueryItemViewHolder holder, int position) {
        QueryItem item = mList.get(position);
        holder.queryTitleText.setText(item.getQueryItemTitle());
//        holder.queryInfoText.setText(item.getQueryItemInfo());
//        Glide.with(holder.itemView.getContext())
//                .load(item.getQueryItemSrc())
//                .placeholder(R.drawable.no_pic)
//                .fitCenter()
//                .into(holder.queryImg);
    }


    private void getRandomHeight(){//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            heights.add((int)(200+Math.random()*400));
        }
    }
}
