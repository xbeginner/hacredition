package com.hacredition.xph.hacredition.mvp.ui.activity;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
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
import com.hacredition.xph.hacredition.mvp.ui.fragments.HouseHoldGradeFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HouseHoldOrgGradeFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.HouseInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.InsuranceInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.MachineInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.MortgageInfoInputFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OperationalEntityFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.OwnerShipFragment;
import com.hacredition.xph.hacredition.mvp.ui.fragments.PoliceInfoFragment;
import com.hacredition.xph.hacredition.utils.DimenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

public class QueryComponentActivity extends BaseActivity
    implements GestureDetector.OnGestureListener{

    @BindView(R.id.back_image_view_id)
    AppCompatImageView backImageView;

    @BindView(R.id.query_component_layout)
    FrameLayout layout;

    private String fragmentName;

    private String queryIdcard;

    private GestureDetector gestureDetector ;

    private int minDistance = 200;
    private int minVelocity = 0;


    public interface QueryFlingEventListener{
        public void onLeftFlingEvent();
        public void onRightFlingEvent();
    }

    QueryFlingEventListener mListeners ;




    private static final Map<String,Integer> maps = new HashMap<String,Integer>(){
        {
             put("HouseHoldQuery",1);
             put("HouseHoldGradeQuery",2);
             put("HouseHoldOrgQuery",3);
             put("MyHouseHoldQuery",4);
             put("MyHouseHoldGradeQuery",5);
        }
    };

//    private void initMinWidth(){
//        DisplayMetrics metric = new DisplayMetrics();
//        WindowManager wm = this.getWindowManager();
//        wm.getDefaultDisplay().getMetrics(metric);
//        int width = metric.widthPixels;
//        minDistance = width*3/4;
//    }



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
        gestureDetector = new GestureDetector(this,this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        int type = maps.get(fragmentName)==null?0:maps.get(fragmentName);
        initFragment(type,fragmentTransaction);
        //initMinWidth();
        backImageView.setVisibility(View.VISIBLE);
        backImageView.setClickable(true);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryComponentActivity.this.finish();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(DimenUtil.px2dip(event.getY())>60&&fragmentName.equals("HouseHoldQuery")){
            return gestureDetector.onTouchEvent(event);
        }else{
            return super.dispatchTouchEvent(event);
        }
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
               break;
           }
           case 1:
           case 4:{

               HouseHoldBasicQueryFragment fragment = new HouseHoldBasicQueryFragment();
               fragment.setIdCard(queryIdcard);
               fragmentTransaction.add(R.id.query_component_layout,fragment);
               fragmentTransaction.commit();
               mListeners = fragment;
               break;
           }
           case 2:
           case 5:{
               HouseHoldGradeFragment fragment = new HouseHoldGradeFragment();
               fragment.setIdCard(queryIdcard);
               fragmentTransaction.add(R.id.query_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
           case 3:{
               HouseHoldOrgGradeFragment fragment = new HouseHoldOrgGradeFragment();
               fragment.setIdcard(queryIdcard);
               fragmentTransaction.add(R.id.query_component_layout,fragment);
               fragmentTransaction.commit();
               break;
           }
       }
    }


    /**
     * 按下屏幕触发
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {

        return false;
    }

    /**
     * 按下时间较长且没有拖动
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    /**
     * 轻触屏幕
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    /**
     * 滑屏 ，最终都会调用onFling， onDown-----》onScroll----》onScroll----》onScroll----》………----->onFling
     * @param motionEvent
     * @param motionEvent1
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
        return false;
    }

    /**
     * 长按
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    /**
     * 按下屏幕后快速滑动后松开
     * @param motionEvent
     * @param motionEvent1
     * @param velocityX x轴上的移动速度
     * @param velocityY y轴上的移动速度
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
        if(mListeners!=null){
            if (motionEvent.getX() - motionEvent1.getX() > minDistance && Math.abs(velocityX) > minVelocity) {
                mListeners.onLeftFlingEvent();
            } else if (motionEvent1.getX() - motionEvent.getX() > minDistance && Math.abs(velocityX) > minVelocity) {
                mListeners.onRightFlingEvent();
            }

        }
        return false;
    }




}
