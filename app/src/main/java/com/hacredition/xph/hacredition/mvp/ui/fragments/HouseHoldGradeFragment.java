package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.DelGradeDetailInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldDebtInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldGradeInfo;
import com.hacredition.xph.hacredition.mvp.entity.SumGradeDetailInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseHoldGradePresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.QueryComponentActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.NewsRecyclerAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldGradeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by pc on 2017/3/14.
 */

public class HouseHoldGradeFragment extends BaseFragment
    implements HouseHoldGradeView {


    @BindView(R.id.household_grade_all)
    TextView allGradeText;

    @BindView(R.id.household_grade_sum)
    TextView sumGradeText;

    @BindView(R.id.household_grade_del)
    TextView delGradeText;

    @BindView(R.id.add_grade_columnview)
    ColumnChartView addColumnChartView;

    @BindView(R.id.del_grade_columnview)
    ColumnChartView delColumnChartView;

    //资产
    @BindView(R.id.income_piechartview)
    PieChartView incomePieChartView;

    //负债
    @BindView(R.id.spend_piechartview)
    PieChartView spendPieChartView;



    @Inject
    Activity gradeFragmentActivity;
    @Inject
    @ContextLife("Activity")
    Context activityContext;


    @Inject
    HouseHoldGradePresenterImpl gradePresenter;

    private String mIdCard;

    public void setIdCard(String idCard){
        mIdCard = idCard;
    }

    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        showHouseHoldGradeInfo(mIdCard);
        showHouseHoldFondInfo(mIdCard);
        showHouseHoldDebtInfo(mIdCard);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_household_grade;
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
        Toast.makeText(activityContext,"数据解析错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initPresenter() {
        mPresenter = gradePresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void initColumnChart(HouseHoldGradeInfo info){


        allGradeText.setText(info.getGrade());
        sumGradeText.setText("加分:"+info.getSumGrade());
        delGradeText.setText("减分:"+info.getDelGrade());

        final List<SumGradeDetailInfo> sumInfos = info.getSumGradeDetailInfoList();
        final List<DelGradeDetailInfo> delInfos = info.getDelGradeDetailInfoList();

        //初始化加分项
        String[] sums = new String[sumInfos.size()];
        float[] sumGrades = new float[sumInfos.size()];
        for(int i = 0;i<sumInfos.size();i++){
            sums[i] = sumInfos.get(i).getItemName();
            sumGrades[i] = sumInfos.get(i).getGrade();
        }

        //初始化减分项
        String[] dels = new String[delInfos.size()];
        float[] delGrades = new float[delInfos.size()];
        for(int i = 0;i<delInfos.size();i++){
            dels[i] = delInfos.get(i).getItemName();
            delGrades[i] = delInfos.get(i).getGrade();
        }

        int numSubcolumns = 1;
        int sumColumns = sums.length;
        int delColumns = dels.length;

        //初始化加分柱状图
        //AxisValue包括数值和label，用于边界显示
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        //SubcolumnValue指柱状图每一个柱状的性质，作为参数传给Column,column加入List<Column>
        //再作为参数传给ColumnChartData，该类是整个柱状图的展示类
        List<SubcolumnValue> values;
        for (int i = 0; i < sumColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue(sumGrades[j],
                        ChartUtils.pickColor()));
            }
            // 点击柱状图就展示数据量
            axisValues.add(new AxisValue(i).setLabel(sums[i]));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }
        ColumnChartData columnData = new ColumnChartData(columns);
        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true)
                .setTextColor(Color.BLACK));
        columnData.setAxisYLeft(new Axis().setHasLines(true)
                .setTextColor(Color.BLACK).setMaxLabelChars(2));
        addColumnChartView.setColumnChartData(columnData);
        addColumnChartView.setValueSelectionEnabled(true);
        addColumnChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        addColumnChartView.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {
                final TextView et = new TextView(activityContext);
                et.setText(sumInfos.get(i).getDetail());
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
        });


        //初始化减分柱状图
        List<AxisValue> axisDelValues = new ArrayList<AxisValue>();
        List<Column> columnsDel = new ArrayList<Column>();
        List<SubcolumnValue> delValues;
        for (int i = 0; i < delColumns; ++i) {
            delValues = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                delValues.add(new SubcolumnValue(delGrades[j],
                        ChartUtils.pickColor()));
            }
            // 点击柱状图就展示数据量
            axisDelValues.add(new AxisValue(i).setLabel(dels[i]));
            columnsDel.add(new Column(delValues).setHasLabelsOnlyForSelected(true));
        }
        ColumnChartData columnDelData = new ColumnChartData(columnsDel);
        columnDelData.setAxisXBottom(new Axis(axisDelValues).setHasLines(true)
                .setTextColor(Color.BLACK));
        columnDelData.setAxisYLeft(new Axis().setHasLines(true)
                .setTextColor(Color.BLACK).setMaxLabelChars(2));
        delColumnChartView.setColumnChartData(columnDelData);
        delColumnChartView.setValueSelectionEnabled(true);
        delColumnChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        delColumnChartView.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {
                final TextView et = new TextView(activityContext);
                et.setText(delInfos.get(i).getDetail());
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
        });
    }



    @Override
    public void initFondPirChart(List<HouseHoldFondInfo> list){
        int numValues = list.size();

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i)
        {
            SliceValue sliceValue = new SliceValue(
                    list.get(i).getValue(), ChartUtils.pickColor());
            System.out.println(i+":"+ChartUtils.pickColor());
            sliceValue.setLabel(list.get(i).getItem());//设置label
            values.add(sliceValue);
        }

        PieChartData pieChardata = new PieChartData(values);
        pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);//显示标签
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pieChardata.setCenterText1("资产");//环形中间的文字1
        pieChardata.setCenterText1Color(Color.BLACK);//文字颜色
        pieChardata.setCenterText1FontSize(14);//文字大小

//        pieChardata.setCenterText2("饼图测试");
//        pieChardata.setCenterText2Color(Color.BLACK);
//        pieChardata.setCenterText2FontSize(18);
        /**这里也可以自定义你的字体   Roboto-Italic.ttf这个就是你的字体库*/
//      Typeface tf = Typeface.createFromAsset(this.getAssets(), "Roboto-Italic.ttf");
//      data.setCenterText1Typeface(tf);

        incomePieChartView.setPieChartData(pieChardata);
        incomePieChartView.setValueSelectionEnabled(true);//选择饼图某一块变大
        incomePieChartView.setAlpha(0.9f);//设置透明度
        incomePieChartView.setCircleFillRatio(1f);//设置饼图大小
    }

    @Override
    public void initDebtPirChart(List<HouseHoldDebtInfo> list){
        int numValues = list.size();

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i)
        {
            SliceValue sliceValue = new SliceValue(
                    list.get(i).getValue(), ChartUtils.pickColor());
            sliceValue.setLabel(list.get(i).getItem());//设置label
            values.add(sliceValue);
        }

        PieChartData pieChardata = new PieChartData(values);
        pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pieChardata.setCenterText1("负债");//环形中间的文字1
        pieChardata.setCenterText1Color(Color.BLACK);//文字颜色
        pieChardata.setCenterText1FontSize(14);//文字大小

//        pieChardata.setCenterText2("饼图测试");
//        pieChardata.setCenterText2Color(Color.BLACK);
//        pieChardata.setCenterText2FontSize(18);
        /**这里也可以自定义你的字体   Roboto-Italic.ttf这个就是你的字体库*/

        spendPieChartView.setPieChartData(pieChardata);
        spendPieChartView.setValueSelectionEnabled(true);//选择饼图某一块变大
        spendPieChartView.setAlpha(0.9f);//设置透明度
        spendPieChartView.setCircleFillRatio(1f);//设置饼图大小
    }


    public void showHouseHoldGradeInfo(String idcard){
         gradePresenter.getHouseHoldGradeInfo(idcard);
    }


    public void showHouseHoldFondInfo(String idcard) {
         gradePresenter.getHouseHoldFondInfo(idcard);
    }


    public void showHouseHoldDebtInfo(String idcard) {
         gradePresenter.getHouseHoldDebtInfo(idcard);
    }


}
