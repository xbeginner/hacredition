package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;
import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseHoldBasicInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.BudgetQueryPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseHoldBasicQueryPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.QueryComponentActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.BasicQueryItemAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.BudgetQueryView;
import com.hacredition.xph.hacredition.mvp.view.HouseHoldBasicQueryView;
import com.hacredition.xph.hacredition.utils.LineGridView;
import com.hacredition.xph.hacredition.utils.MyHashMaps;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class BudgetQueryFragment extends BaseFragment
    implements BudgetQueryView{

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


    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        budgetPresenter.getBudgetInfo();
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
}
