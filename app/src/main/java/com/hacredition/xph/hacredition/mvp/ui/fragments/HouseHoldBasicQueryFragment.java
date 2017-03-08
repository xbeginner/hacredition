package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.hacredition.xph.hacredition.mvp.ui.adapter.BasicQueryItemAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldBasicQueryView;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyHashMaps;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class HouseHoldBasicQueryFragment extends BaseFragment
        implements HouseHoldBasicQueryView {

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity inputFragmentActivity;

    @Inject
    HouseHoldBasicQueryPresenterImpl presenter;

    @BindView(R.id.household_basic_gridview)
    GridView gridView;

    private static String mIdCard;

    private BasicQueryItemAdapter mAdapter;

    public void setIdCard(String idcard){
        mIdCard = idcard;
    }


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
        System.out.println("showHouseHoldBasicInfo");
        List<BaseAdapterItem> list = new ArrayList<BaseAdapterItem>();
        try{
            Class clazz = info.getClass();
            //Class clazz = Class.forName("com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo");
            Field[] fieldlist = clazz.getDeclaredFields();
            for(int i=0;i<fieldlist.length;i++){
                Field f = fieldlist[i];
                BaseAdapterItem item = new BaseAdapterItem();
                item.setName(MyHashMaps.HOUSEHOLDBASICINFOMAP.get(f.getName()));
                String fieldName = f.getName();
                Method m = clazz.getDeclaredMethod("get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length()));
                String s = String.valueOf(m.invoke(info));
                item.setValue(s);
                list.add(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mAdapter = new BasicQueryItemAdapter(list,activityContext);
        gridView.setAdapter(mAdapter);
    }



}
