package com.hacredition.xph.hacredition.mvp.ui.activity;



import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;
import com.hacredition.xph.hacredition.mvp.ui.fragments.EntrepreneurshipFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.FiscalSpendFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HouseInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OperationalEntityFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OwnerShipFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class InputComponentActivity extends BaseActivity  {

    private String fragmentName;

    private static final Map<String,Integer> maps = new HashMap<String,Integer>(){
        {
             put("HouseHoldInput",1);
             put("FiscalSpendingInput",2);
             put("OperationalEntityInput",3);
             put("EntrepreneurshipInput",4);
             put("OwnerShipInput",5);
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
        initFragment(maps.get(fragmentName),fragmentTransaction);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragmentName = getIntent().getStringExtra("fragmentName");
        super.onCreate(savedInstanceState);
    }


    void initFragment(int type,FragmentTransaction fragmentTransaction){
       switch (type){
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
       }

    }



}
