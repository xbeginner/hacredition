package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.DelGradeDetailInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldDebtInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldGradeInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldOrgInfo;
import com.hacredition.xph.hacredition.mvp.entity.SumGradeDetailInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseHoldGradePresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseHoldOrgPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldGradeView;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldOrgQueryView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.BubbleChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.BubbleChartData;
import lecho.lib.hellocharts.model.BubbleValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.BubbleChartView;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by pc on 2017/3/14.
 */

public class HouseHoldOrgGradeFragment extends BaseFragment
    implements HouseHoldOrgQueryView {


    @BindView(R.id.org_grade_bubblechart)
    BubbleChartView orgBubbleChart;

    @Inject
    HouseHoldOrgPresenterImpl orgPresenter;


    @Inject
    Activity orgFragmentActivity;
    @Inject
    @ContextLife("Activity")
    Context activityContext;

    private String mIdcard;

    public void setIdcard(String idcard){
        mIdcard = idcard;
    }


    @Override
    public void initInjector() {
         fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        orgPresenter.getHouseHoldOrgInfos(mIdcard);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_household_org_grade;
    }

    @Override
    protected void addValidation() {

    }


    private void initBubbleChart(final List<HouseHoldOrgInfo> list){
        List<BubbleValue> pointValues = new ArrayList<BubbleValue>();// 节点数据结合
        for(int i=0;i<list.size();i++){
            BubbleValue v=new BubbleValue();//定义气泡
            v.set((float)Math.random()*100,(float)Math.random()*100,list.get(i).getValue());//设置气泡的横纵坐标x、y，z为气泡的半径
            v.setColor(ChartUtils.pickColor());//设置气泡的颜色
            v.setLabel(list.get(i).getOrgName());//设置气泡中显示的文本
            v.setShape(ValueShape.CIRCLE);//设置气泡的形状
            pointValues.add(v);
        }


//        Axis axisY = new Axis().setHasLines(true);// Y轴属性
//        Axis axisX = new Axis();// X轴属性
//        axisY.setName("Y");//设置Y轴显示名称
//        axisX.setName("X");//设置X轴显示名称
//        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();//定义X轴刻度值的数据集合
//        ArrayList<AxisValue> axisValuesY = new ArrayList<AxisValue>();//定义Y轴刻度值的数据集合
//        axisX.setValues(axisValuesX);//为X轴显示的刻度值设置数据集合
//        axisX.setLineColor(Color.BLACK);// 设置X轴轴线颜色
//        axisY.setLineColor(Color.BLACK);// 设置Y轴轴线颜色
//        axisX.setTextColor(Color.RED);// 设置X轴文字颜色
//        axisY.setTextColor(Color.RED);// 设置Y轴文字颜色
//        axisX.setTextSize(14);// 设置X轴文字大小
//        axisX.setTypeface(Typeface.DEFAULT);// 设置文字样式，此处为默认
//        axisX.setHasTiltedLabels(true);// 设置X轴文字向左旋转45度
//        axisX.setHasLines(false);// 是否显示X轴网格线
//        axisY.setHasLines(false);// 是否显示Y轴网格线
//        axisX.setHasSeparationLine(false);// 设置是否有分割线
//        axisX.setInside(false);// 设置X轴文字是否在X轴内部
        BubbleChartData bubbleDate=new BubbleChartData(pointValues);//定义气泡图的数据对象
        bubbleDate.setBubbleScale(1f);//设置气泡的比例大小
        bubbleDate.setHasLabelsOnlyForSelected(false);//设置文本只有当点击时显示
        bubbleDate.setMinBubbleRadius(1);//设置气泡的最小半径
        bubbleDate.setValueLabelsTextColor(Color.BLACK);// 设置数据文字颜色
        bubbleDate.setValueLabelTextSize(15);// 设置数据文字大小
        bubbleDate.setValueLabelTypeface(Typeface.MONOSPACE);// 设置数据文字样式
        bubbleDate.setHasLabels(true);
        bubbleDate.setValueLabelBackgroundEnabled(false);
        //bubbleDate.setValueLabelBackgroundAuto(true);
//        bubbleDate.setAxisYLeft(axisY);// 将Y轴属性设置到左边
//        bubbleDate.setAxisXBottom(axisX);// 将X轴属性设置到底部
//        bubbleDate.setAxisYRight(axisYRight);//设置右边显示的轴
//        bubbleDate.setAxisXTop(axisXTop);//设置顶部显示的轴
        orgBubbleChart.setBubbleChartData(bubbleDate);//将数据设置给气泡图
        orgBubbleChart.setZoomEnabled(false);//设置是否支持缩放
        orgBubbleChart.setOnValueTouchListener(new BubbleChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, BubbleValue bubbleValue) {
                final TextView et = new TextView(activityContext);
                et.setText(list.get(i).getInfo());
                new AlertDialog.Builder(activityContext)
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }

            @Override
            public void onValueDeselected() {

            }
        });//为图表设置值得触摸事件
        orgBubbleChart.setInteractive(true);// 用户是否对其可以进行互动
        orgBubbleChart.setValueSelectionEnabled(true);//设置值选中后进行显示
    }

    @Override
    public void showOrgBubbles(List<HouseHoldOrgInfo> list) {
        initBubbleChart(list);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg() {
        Toast.makeText(activityContext,"数据解析错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initPresenter() {
         mPresenter = orgPresenter;
         mPresenter.attachView(this);
    }
}
