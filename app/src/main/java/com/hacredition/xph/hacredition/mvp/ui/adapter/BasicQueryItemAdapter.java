package com.hacredition.xph.hacredition.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2017/3/8.
 */

public class BasicQueryItemAdapter extends BaseAdapter {


    List<BaseAdapterItem> list = new ArrayList<BaseAdapterItem>();
    private LayoutInflater mInflater;

    public BasicQueryItemAdapter(List<BaseAdapterItem> list ,Context context){
        Collections.sort(list);
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setList(List<BaseAdapterItem> list){

        for(BaseAdapterItem i:list){
            System.out.println(i.getName());
            System.out.println(i.getShowSplitLine());
        }

        Collections.sort(list);
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.household_basic_query_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        BaseAdapterItem item = list.get(position);
        if(item!=null){
            holder.nameTextView.setText(item.getName());
            holder.valueTextView.setText(item.getValue());
            if(item.getShowSplitLine()==false){
                holder.view.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

    public final class ViewHolder {
        @BindView(R.id.basic_query_item_name)
        TextView nameTextView;
        @BindView(R.id.basic_query_item_value)
        TextView valueTextView;
        @BindView(R.id.split_line_id)
        View view;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
