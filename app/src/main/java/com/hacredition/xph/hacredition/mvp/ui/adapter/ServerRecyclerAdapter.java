package com.hacredition.xph.hacredition.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.listener.OnItemClickListener;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.ServerItem;
import com.hacredition.xph.hacredition.mvp.ui.adapter.base.BaseRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2017/1/17.
 */

public class ServerRecyclerAdapter extends BaseRecyclerViewAdapter<ServerItem>{



    public interface OnServerItemClickListener extends OnItemClickListener{
        void onItemClick(View view, String serverItemName);
    }

    @Inject
    public ServerRecyclerAdapter() {
        super(null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent,R.layout.server_item_layout);
        final ServerItemViewHolder holder = new ServerItemViewHolder(view);
        setItemOnClickEvent(holder);
        return holder;
    }


    private void setItemOnClickEvent(final RecyclerView.ViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     ((OnServerItemClickListener) mOnItemClickListener).onItemClick(v, mList.get(holder.getLayoutPosition()).getItemName());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setServerItemValues((ServerItemViewHolder)holder,position);
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






    class ServerItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.server_item_img)
        ImageView serverImg;
        @BindView(R.id.server_item_text)
        TextView serverText;



        public ServerItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }


    private void setServerItemValues(ServerItemViewHolder holder, int position) {
        ServerItem item = mList.get(position);
        holder.serverText.setText(item.getInfo());
        Glide.with(holder.itemView.getContext())
                .load(item.getImgSrc())
                .placeholder(R.drawable.no_pic)
                .fitCenter()
                .into(holder.serverImg);
    }

}
