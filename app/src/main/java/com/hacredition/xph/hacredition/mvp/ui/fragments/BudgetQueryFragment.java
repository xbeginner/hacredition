package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.MainThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
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
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

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


    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        budgetPresenter.getBudgetInfo(MainActivity.mUserInfo.getIdcard());
        recyclerView.setLayoutManager(new LinearLayoutManager(queryFragmentActivity,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
    public void showBudgetInfos(final BudgetInfo budgetInfo) {
        budgetIncomeText.setText(String.valueOf(budgetInfo.getIncomeSum()));
        budgetOutputText.setText(String.valueOf(budgetInfo.getOutputSum()));
        budgetSumText.setText(String.valueOf(budgetInfo.getSum()));
        mAdapter.setList(budgetInfo.getBudgetItemList());
        mAdapter.notifyDataSetChanged();
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
        budgetPresenter.getBudgetInfoByTime(MainActivity.mUserInfo.getIdcard(),year,month);
    }
}
