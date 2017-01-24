package com.hacredition.xph.hacredition.mvp.ui.adapter.base;

import android.support.annotation.AnimRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hacredition.xph.hacredition.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by pc on 2017/1/18.
 */

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected boolean isFooterShow;

    protected List<T> mList;

    protected int mLastPosition = -1;



    public BaseRecyclerViewAdapter(List<T> list){
        mList = list;
    }

    protected OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(mList==null){
            return 0;
        }
        int size = mList.size();
        //在scroll事件中监听，如果最后一个则将该标识设为true，size+1为Footer
        if(isFooterShow){
            size+=1;
        }
        return size;
    }



    protected void setItemAppearAnimation(RecyclerView.ViewHolder holder, int position, @AnimRes int anim){
        if(position>mLastPosition){
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),anim);
            holder.itemView.startAnimation(animation);
            mLastPosition = position;
        }
    }

    protected View getView(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public void add(int position,T item){
        mList.add(position,item);
        notifyItemInserted(position);
    }

    public void addMore(List<T> data){
        int startPosition = mList.size();
        mList.addAll(data);
        notifyItemRangeInserted(startPosition, mList.size());
    }

    public void delete(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> items) {
        mList = items;
    }


}
