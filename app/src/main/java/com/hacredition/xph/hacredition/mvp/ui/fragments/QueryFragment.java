package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.QueryItem;
import com.hacredition.xph.hacredition.mvp.presenter.impl.InputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.QueryPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.InputComponentActivity;
import com.hacredition.xph.hacredition.mvp.ui.activity.LoginActivity;
import com.hacredition.xph.hacredition.mvp.ui.activity.MainActivity;
import com.hacredition.xph.hacredition.mvp.ui.activity.QueryComponentActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.InputRecyclerAdapter;
import com.hacredition.xph.hacredition.mvp.ui.adapter.QueryRecyclerAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputView;
import com.hacredition.xph.hacredition.mvp.view.QueryView;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.hacredition.xph.hacredition.utils.NetUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class QueryFragment extends BaseFragment
        implements QueryView,QueryRecyclerAdapter.OnQueryItemClickListener{

    @BindView(R.id.query_item_recyclerview_id)
    RecyclerView recyclerView;

    @BindView(R.id.query_progress_bar_id)
    ProgressBar progressBar;

    @BindView(R.id.query_no_actions_view_id)
    TextView textView;

    @Inject
    Activity queryFragmentActivity;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    QueryPresenterImpl mQueryPresenterImpl;

    @Inject
    QueryRecyclerAdapter queryRecyclerAdapter;

    private static boolean isInit = false;

    private final static String MYQUERY = "MyHouseHoldQuery";

    private final static String MYGRADEQUERY = "MyHouseHoldGradeQuery";

    private final static String MYBUDGETQUERY = "MyBudgetQuery";

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
        return R.layout.fragment_query;
    }

    @Override
    protected void addValidation() {

    }

    /**
     * 根据权限进行权限的查询
     */
    @Override
    public void showQueryItems() {
//        if(MainActivity.mUserInfo==null){
//            Intent intent = new Intent(queryFragmentActivity, LoginActivity.class);
//            startActivityForResult(intent,MainActivity.LOGIN_SUCCESS_CODE);
//        }else{
            if(!isInit) {
                mQueryPresenterImpl.setQueryItems(MyUtils.getCurrentUserInfo());
                isInit = true;
                hideProgress();
            }
//        }
    }

    @Override
    public void showProgress() {
        if(progressBar!=null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if(progressBar!=null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMsg(){
        if(NetUtil.isNetworkAvailable()){
            Toast.makeText(queryFragmentActivity,R.string.data_error,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initPresenter() {
        mPresenter = mQueryPresenterImpl;
        mPresenter.attachView(this);
    }

    @Override
    public void setQueryItem(List<QueryItem> queryItems) {
        queryRecyclerAdapter.setList(queryItems);
        queryRecyclerAdapter.notifyDataSetChanged();
    }


    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(queryFragmentActivity,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(queryRecyclerAdapter);
        queryRecyclerAdapter.setOnItemClickListener(this);
        //recyclerView.addItemDecoration(new RecyclerItemDecoration(2,2,getResources().getColor(R.color.dividerColor)));
    }

    @Override
    public void onItemClick(View view, String FragmentName) {
        final String fragmentName = FragmentName;
        if(fragmentName.equals(MYQUERY)||fragmentName.equals(MYGRADEQUERY)||fragmentName.equals(MYBUDGETQUERY)){
            Bundle bundle = new Bundle();
            bundle.putString("queryfragmentName",fragmentName);
            bundle.putString("idcard",MainActivity.mUserInfo.getIdcard());
            Intent intent = new Intent(activityContext, QueryComponentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{

            final EditText et = new EditText(activityContext);
            new AlertDialog.Builder(activityContext)
                    .setTitle("请输入证件号")
                    .setView(et)
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("queryfragmentName",fragmentName);
                            bundle.putString("idcard",et.getText().toString());
                            Intent intent = new Intent(activityContext, QueryComponentActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }).show();
        }

    }

    @Override
    public void onItemClick(View view, int position) {

    }


}
