package com.hacredition.xph.hacredition.mvp.ui.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;
import com.hacredition.xph.hacredition.mvp.ui.fragments.CarInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.CourtInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.CreditInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.EntrepreneurshipFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.FiscalSpendFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.GuaranteeInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HonourInfoFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HouseHoldBasicQueryFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HouseInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.InsuranceInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.MachineInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.MortgageInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OperationalEntityFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OwnerShipFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.PoliceInfoFragment;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

public class QueryComponentActivity extends BaseActivity  {

    @BindView(R.id.back_image_view_id)
    AppCompatImageView backImageView;

    private String fragmentName;

    private String queryIdcard;


    private static final Map<String,Integer> maps = new HashMap<String,Integer>(){
        {
             put("HouseHoldQuery",1);
        }
    };



    @Override
    public int getLayoutId() {
        return R.layout.activity_query_component;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        int type = maps.get(fragmentName)==null?0:maps.get(fragmentName);
        initFragment(type,fragmentTransaction);
        backImageView.setVisibility(View.VISIBLE);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryComponentActivity.this.finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragmentName = getIntent().getStringExtra("queryfragmentName");
        queryIdcard = getIntent().getStringExtra("idcard");
        super.onCreate(savedInstanceState);
    }


    void initFragment(int type,FragmentTransaction fragmentTransaction){
       switch (type){
           case 0:{
               new AlertDialog.Builder(this)
                       .setTitle("提示")
                       .setMessage("抱歉,该功能正在建设中")
                       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               QueryComponentActivity.this.finish();
                           }
                       })
                       .show();
           }
           case 1:{
               HouseHoldBasicQueryFragment fragment = new HouseHoldBasicQueryFragment();
               fragment.setIdCard(queryIdcard);
               fragmentTransaction.add(R.id.query_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
       }
    }



}
