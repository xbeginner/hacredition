package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.listener.RequestCallBack;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.CarInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CarInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseHoldBasicQueryPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.QueryComponentActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.BasicQueryItemAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldBasicQueryView;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.LineGridView;
import com.hacredition.xph.hacredition.utils.MyHashMaps;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


public class HouseHoldBasicQueryFragment extends BaseFragment
        implements HouseHoldBasicQueryView
        ,QueryComponentActivity.QueryFlingEventListener {

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity queryFragmentActivity;

    @BindView(R.id.empty_data_id)
    TextView emptyDataText;


    @Inject
    HouseHoldBasicQueryPresenterImpl presenter;

    @BindView(R.id.household_basic_gridview)
    LineGridView gridView;

    private static String mIdCard;

    private BasicQueryItemAdapter mAdapter;

    public void setIdCard(String idcard) {
        mIdCard = idcard;
    }

    private static long downTime;    //获取鼠标按下时的时间
    private static long upTime;     //获取鼠标松开时的时间


    private List<List<BaseAdapterItem>> itemsList = new ArrayList<List<BaseAdapterItem>>();

    private void addList(List<BaseAdapterItem> list){
        itemsList.add(list);
    }

    private void removeList(List<BaseAdapterItem> list){
        itemsList.remove(list);
    }


    private int position = 0;


    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_household_basic_query;
    }

    @Override
    protected void addValidation() {

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg() {

    }

    @Override
    public void initPresenter() {
        mPresenter = presenter;
        mPresenter.attachView(this);
        showHouseHoldBasicInfo(presenter.getHouseHoldBasicInfo(mIdCard));
    }


    @Override
    public void showHouseHoldBasicInfo(HouseHoldBasicInfo info) {
        List<BaseAdapterItem> list = new ArrayList<BaseAdapterItem>();
        try {
            Class clazz = info.getClass();
            //Class clazz = Class.forName("com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo");
            Field[] fieldlist = clazz.getDeclaredFields();
            for (int i = 0; i < fieldlist.length; i++) {
                Field f = fieldlist[i];
                BaseAdapterItem item = new BaseAdapterItem();
                String fieldName = f.getName();
                item.setName(MyHashMaps.HOUSEHOLDBASICINFOMAP.get(fieldName));
                Method m = clazz.getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length()));
                String s = String.valueOf(m.invoke(info));
                item.setValue(s);
                item.setShowSplitLine(true);
                item.setSortId(MyHashMaps.HOUSEHOLDBASICSORTMAP.get(fieldName));
                if (!s.equals("null")) {
                    list.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter = new BasicQueryItemAdapter(list, activityContext);
        gridView.setAdapter(mAdapter);
        addList(list);
    }

    @Override
    public void queryHouseHoldBasicInfo(String idcard, String type) {
        presenter.getHouseHoldOtherInfo(idcard, MyHashMaps.HOUSEHOLDQUERYTYPES.get(type));
    }

    @Override
    public void updateHouseHoldBasicInfo(List<BaseAdapterItem> list) {
        if (list != null && list.size() > 0) {
            emptyDataText.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            mAdapter.setList(list);
            addList(list);
            position+=1;
        } else {
            emptyDataText.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
            addList(null);
            position+=1;
        }
    }


    @Override
    public void onLeftFlingEvent() {

        final Spinner spinner = new Spinner(activityContext);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext, R.array.householdinfoquery, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        new AlertDialog.Builder(activityContext)
                .setTitle("请选择查看内容")
                .setView(spinner)
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        queryHouseHoldBasicInfo(mIdCard, (String) spinner.getSelectedItem());
                    }
                }).show();
    }

    @Override
    public void onRightFlingEvent() {
        if(position>=1){
            System.out.println("postion:"+position);
            List<BaseAdapterItem> list = itemsList.get(position-1);
            itemsList.remove(position);
            updateHouseHoldBasicInfo(list);
            position-=2;
        }
//        else{
//            List<BaseAdapterItem> list = itemsList.get(0);
//            updateHouseHoldBasicInfo(list);
//        }

    }



}
