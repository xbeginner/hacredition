package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.BudgetInfo;
import com.hacredition.xph.hacredition.mvp.entity.BudgetItem;
import com.hacredition.xph.hacredition.mvp.entity.ServerItem;
import com.hacredition.xph.hacredition.mvp.presenter.impl.BudgetQueryPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.ServerPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.MainActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.BudgetItemAdapter;
import com.hacredition.xph.hacredition.mvp.ui.adapter.InputRecyclerAdapter;
import com.hacredition.xph.hacredition.mvp.ui.adapter.ServerRecyclerAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.BudgetQueryView;
import com.hacredition.xph.hacredition.mvp.view.ServerView;
import com.hacredition.xph.hacredition.utils.MonPickDialog;
import com.hacredition.xph.hacredition.utils.RecyclerItemDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;


public class ServerFragment extends BaseFragment
    implements ServerView,ServerRecyclerAdapter.OnServerItemClickListener {

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @BindView(R.id.server_recycler_view)
    RecyclerView recyclerView;

    @Inject
    Activity queryFragmentActivity;

    @Inject
    ServerPresenterImpl mServerPresenter;

    @Inject
    ServerRecyclerAdapter mRecyclerAdapter;

    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        initRecyclerView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_server_layout;
    }

    @Override
    protected void addValidation() {

    }

    @Override
    public void showServers(List<ServerItem> list) {
        mRecyclerAdapter.setList(list);
        mRecyclerAdapter.notifyDataSetChanged();
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
        mPresenter = mServerPresenter;
        mPresenter.attachView(this);
    }


    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(activityContext,3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mRecyclerAdapter);
        recyclerView.addItemDecoration(new RecyclerItemDecoration(2,2,getResources().getColor(R.color.dividerColor)));
        mRecyclerAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, String serverItemName) {
        System.out.println(serverItemName);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public void showServerItems(){
        mServerPresenter.showServerItems();
    }
}
