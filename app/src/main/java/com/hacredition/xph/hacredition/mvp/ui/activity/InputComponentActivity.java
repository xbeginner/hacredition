package com.hacredition.xph.hacredition.mvp.ui.activity;



import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.FrameLayout;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.mvp.entity.CreditInfo;
import com.hacredition.xph.hacredition.mvp.entity.InsuranceInfo;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;
import com.hacredition.xph.hacredition.mvp.ui.fragments.CarInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.CourtInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.CreditInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.EntrepreneurshipFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.FiscalSpendFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.GuaranteeInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HonourInfoFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HouseInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.IncomeInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.InsuranceInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.MachineInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.MortgageInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OperationalEntityFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OutputInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OwnerShipFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.PoliceInfoFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class InputComponentActivity extends BaseActivity  {

    private String fragmentName;

    @BindView(R.id.back_image_view_id)
    AppCompatImageView backImageView;

    private static final Map<String,Integer> maps = new HashMap<String,Integer>(){
        {
             put("HouseHoldInput",1);
             put("FiscalSpendingInput",2);
             put("OperationalEntityInput",3);
             put("EntrepreneurshipInput",4);
             put("OwnerShipInput",5);
             put("HonourInfoInput",6);
             put("MachineInfoInput",7);
             put("PoliceInfoInput",8);
             put("CreditInfoInput",9);
             put("GuaranteeInfoInput",10);
             put("MortgageInfoInput",11);
             put("CourtInfoInput",12);
             put("CarInfoInput",13);
             put("InsuranceInfoInput",14);
             put("IncomeInfoInput",15);
             put("OutputInfoInput",16);
        }
    };





    @Override
    public int getLayoutId() {
        return R.layout.activity_input_component;
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragmentName = getIntent().getStringExtra("fragmentName");
        super.onCreate(savedInstanceState);
        backImageView.setVisibility(View.VISIBLE);
        backImageView.setClickable(true);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputComponentActivity.this.finish();
            }
        });
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
                               InputComponentActivity.this.finish();
                           }
                       })
                       .show();

           }
           case 1:{
               HouseInfoInputFragment fragment = new HouseInfoInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 2:{
               FiscalSpendFragment fragment = new FiscalSpendFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 3:{
               OperationalEntityFragment fragment = new OperationalEntityFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 4:{
               EntrepreneurshipFragment fragment = new EntrepreneurshipFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 5:{
               OwnerShipFragment fragment = new OwnerShipFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 6:{
               HonourInfoFragment fragment = new HonourInfoFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 7:{
               MachineInfoInputFragment fragment = new MachineInfoInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 8:{
               PoliceInfoFragment fragment = new PoliceInfoFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 9:{
               CreditInfoInputFragment fragment = new CreditInfoInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 10:{
               GuaranteeInfoInputFragment fragment = new GuaranteeInfoInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 11:{
               MortgageInfoInputFragment fragment = new MortgageInfoInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 12:{
               CourtInfoInputFragment fragment = new CourtInfoInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 13:{
               CarInfoInputFragment fragment = new CarInfoInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 14:{
               InsuranceInfoInputFragment fragment = new InsuranceInfoInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 15:{
               IncomeInputFragment fragment = new IncomeInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 16:{
               OutputInputFragment fragment = new OutputInputFragment();
               fragmentTransaction.add(R.id.input_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
       }
    }



}
