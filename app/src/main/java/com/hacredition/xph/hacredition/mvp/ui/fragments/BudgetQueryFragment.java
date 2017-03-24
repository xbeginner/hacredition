package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.MainThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.entity.BudgetItem;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldFondInfo;
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfo;
import com.hacredition.xph.hacredition.mvp.entity.OutputInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.BudgetQueryPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseHoldBasicQueryPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.MainActivity;
import com.hacredition.xph.hacredition.mvp.ui.activity.QueryComponentActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.BasicQueryItemAdapter;
import com.hacredition.xph.hacredition.mvp.ui.adapter.BudgetItemAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.BudgetQueryView;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldBasicQueryView;
import com.hacredition.xph.hacredition.utils.LineGridView;
import com.hacredition.xph.hacredition.utils.MonPickDialog;
import com.hacredition.xph.hacredition.utils.MyHashMaps;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;


public class BudgetQueryFragment extends BaseFragment
    implements BudgetQueryView
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener{

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity queryFragmentActivity;

    @Inject
    BudgetQueryPresenterImpl budgetPresenter;

    @BindView(R.id.household_budget_sum)
    TextView budgetSumText;

    @BindView(R.id.household_budget_income)
    TextView budgetIncomeText;

    @BindView(R.id.household_budget_output)
    TextView budgetOutputText;

    @BindView(R.id.budget_recyclerview_id)
    RecyclerView recyclerView;

    @BindView(R.id.budget_income_button)
    Button incomeButton;

    @BindView(R.id.budget_output_button)
    Button outputButton;

    @BindView(R.id.time_select_image)
    ImageButton timeSelectImage;

    @BindView(R.id.graph_select_image)
    ImageButton graphOpenImage;


    @Inject
    BudgetItemAdapter mAdapter;

   private BudgetInfo mInfo;


    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();

        recyclerView.setLayoutManager(new LinearLayoutManager(queryFragmentActivity,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        budgetPresenter.getBudgetInfo(MainActivity.mUserInfo.getIdcard());
        graphOpenImage.setOnClickListener(this);
        timeSelectImage.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_budget_query;
    }

    @Override
    protected void addValidation() {

    }

    @Override
    public void showBudgetInfos(BudgetInfo budgetInfo) {
        budgetIncomeText.setText(String.valueOf(budgetInfo.getIncomeSum()));
        budgetOutputText.setText(String.valueOf(budgetInfo.getOutputSum()));
        budgetSumText.setText(String.valueOf(budgetInfo.getSum()));
        mAdapter.setList(budgetInfo.getBudgetItemList());
        mAdapter.notifyDataSetChanged();
        mInfo = budgetInfo;
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
        mPresenter = budgetPresenter;
        mPresenter.attachView(this);
    }


    @Override

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.time_select_image:{
                Calendar cal = Calendar.getInstance();
                new MonPickDialog(activityContext, THEME_HOLO_LIGHT, this,cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
                break;
            }
            case R.id.graph_select_image:{
                showGraphByInfo(mInfo);
                break;
            }
            case R.id.budget_income_button:{
                budgetPresenter.getBudgetInfoByType(MainActivity.mUserInfo.getIdcard(),1);
                break;
            }
            case R.id.budget_output_button:{
                budgetPresenter.getBudgetInfoByType(MainActivity.mUserInfo.getIdcard(),2);
                break;
            }
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        try {
            String mStr = month<10?"0"+month:month+"";
            String mDay = day<10?"0"+day:day+"";
            String dateString = year+"-"+mStr+"-"+mDay;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            budgetPresenter.getBudgetInfoByTime(MainActivity.mUserInfo.getIdcard(),date);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private void showGraphByInfo(BudgetInfo info){
        LinearLayout.LayoutParams pieParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,1);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setHorizontalGravity(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(lineParams);
        PieChartView incomePie = initPieChart(info.getBudgetItemList(),0);
        PieChartView outputPie = initPieChart(info.getBudgetItemList(),1);
        linearLayout.addView(incomePie,pieParams);
        linearLayout.addView(outputPie,pieParams);

        new AlertDialog.Builder(activityContext)
                .setView(linearLayout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

    }


    public PieChartView initPieChart(List<BudgetItem> list,int type){

        PieChartView pieChartView = new PieChartView(this.getContext());

        List<BudgetItem> mList = new ArrayList<BudgetItem>();

        for(BudgetItem i:list){
            if(i.getLog()==type){
                mList.add(i);
            }
        }

        int numValues = mList.size();

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i)
        {
            SliceValue sliceValue = new SliceValue(
                    list.get(i).getSum(), ChartUtils.pickColor());
            sliceValue.setLabel(list.get(i).getType());//设置label
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
        if(type==0){
            pieChardata.setCenterText1("收入");//环形中间的文字1
        }else{
            pieChardata.setCenterText1("支出");//环形中间的文字1
        }

        pieChardata.setCenterText1Color(Color.BLACK);//文字颜色
        pieChardata.setCenterText1FontSize(14);//文字大小
        pieChartView.setPieChartData(pieChardata);
        pieChartView.setValueSelectionEnabled(true);//选择饼图某一块变大
        pieChartView.setAlpha(0.9f);//设置透明度
        pieChartView.setCircleFillRatio(1f);//设置饼图大小

        return pieChartView;
    }
}
