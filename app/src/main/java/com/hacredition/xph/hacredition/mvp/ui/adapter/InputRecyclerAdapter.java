package com.hacredition.xph.hacredition.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.listener.OnItemClickListener;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.ui.adapter.base.BaseRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2017/1/17.
 */

public class InputRecyclerAdapter extends BaseRecyclerViewAdapter<InputItem>{



    public interface OnInputItemClickListener extends OnItemClickListener{
        void onItemClick(View view, String ActivityName);
    }

    @Inject
    public InputRecyclerAdapter() {
        super(null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent,R.layout.input_item);
        final InputItemViewHolder holder = new InputItemViewHolder(view);
        setItemOnClickEvent(holder);
        return holder;
    }


    private void setItemOnClickEvent(final RecyclerView.ViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     ((OnInputItemClickListener) mOnItemClickListener).onItemClick(v, mList.get(holder.getLayoutPosition()).getInputItemFragmentName());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setInputItemValues((InputItemViewHolder)holder,position);
        setItemAppearAnimation(holder,position,R.anim.anim_bottom_in);
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






    class InputItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.input_image_id)
        ImageView inputImg;
        @BindView(R.id.input_title_id)
        TextView inputTitleText;
        @BindView(R.id.input_info_id)
        TextView inputInfoText;


        public InputItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }


    private void setInputItemValues(InputItemViewHolder holder, int position) {
        InputItem item = mList.get(position);
        holder.inputTitleText.setText(item.getInputItemTitle());
        holder.inputInfoText.setText(item.getInputItemInfo());
        Glide.with(holder.itemView.getContext())
                .load(item.getInputItemSrc())
                .placeholder(R.drawable.no_pic)
                .fitCenter()
                .into(holder.inputImg);
    }

}
