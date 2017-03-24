package com.hacredition.xph.hacredition.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.listener.OnItemClickListener;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.entity.BudgetItem;
import com.hacredition.xph.hacredition.mvp.entity.QueryItem;
import com.hacredition.xph.hacredition.mvp.ui.adapter.base.BaseRecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2017/3/22.
 */

public class BudgetItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BudgetItem> mItems;

    //private Context mContext;

    @Inject
    @Singleton
    public BudgetItemAdapter() {
        mItems = new ArrayList<BudgetItem>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent,R.layout.budget_item_layout);
        BudgetItemViewHolder holder = new BudgetItemViewHolder(view);
        return holder;
    }

    private View getView(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setItemValues((BudgetItemViewHolder)holder,position);
        setItemAppearAnimation(holder,position,R.anim.anim_bottom_in);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    private void setItemValues(BudgetItemAdapter.BudgetItemViewHolder holder, int position) {
        BudgetItem item = mItems.get(position);
        holder.typeText.setText(item.getType());
        holder.valueText.setText(String.valueOf(item.getSum()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        holder.timeText.setText(format.format(item.getTime()));
    }


    private void setItemAppearAnimation(RecyclerView.ViewHolder holder, int position, @AnimRes int anim){
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),anim);
        holder.itemView.startAnimation(animation);
    }


    class BudgetItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.budget_item_type_text)
        TextView typeText;
        @BindView(R.id.budget_item_value_text)
        TextView valueText;
        @BindView(R.id.budget_item_time_text)
        TextView timeText;


        public BudgetItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public void setList(List<BudgetItem> list){
        mItems = list;
    }
}
