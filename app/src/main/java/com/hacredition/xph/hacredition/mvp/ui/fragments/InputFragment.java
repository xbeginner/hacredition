package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.InputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.LoginActivity;
import com.hacredition.xph.hacredition.mvp.ui.activity.MainActivity;
import com.hacredition.xph.hacredition.mvp.ui.adapter.InputRecyclerAdapter;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputView;
import com.hacredition.xph.hacredition.utils.NetUtil;
import com.hacredition.xph.hacredition.utils.RecyclerItemDecoration;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class InputFragment extends BaseFragment
        implements InputView,InputRecyclerAdapter.OnInputItemClickListener{

    @BindView(R.id.input_item_recyclerview_id)
    RecyclerView recyclerView;

    @BindView(R.id.input_progress_bar_id)
    ProgressBar progressBar;

    @BindView(R.id.input_no_actions_view_id)
    TextView textView;

    @Inject
    Activity inputFragmentActivity;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    InputPresenterImpl mInputPresenterImpl;

    @Inject
    InputRecyclerAdapter inputRecyclerAdapter;

    public static boolean inputItemInited = false;



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
        return R.layout.fragment_input;
    }

    /**
     * 根据权限进行权限的查询
     */
    @Override
    public void showInputItems() {
        if(MainActivity.mUserInfo==null){
            Intent intent = new Intent(inputFragmentActivity, LoginActivity.class);
            startActivityForResult(intent,MainActivity.LOGIN_SUCCESS_CODE);
        }else{
            if(!inputItemInited) {
                mInputPresenterImpl.setInputItems(MainActivity.mUserInfo);
            }
        }
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
            Toast.makeText(inputFragmentActivity,R.string.data_error,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initPresenter() {
        mPresenter = mInputPresenterImpl;
        mPresenter.attachView(this);
    }

    @Override
    public void setInputItem(List<InputItem> inputItems) {
        inputRecyclerAdapter.setList(inputItems);
        inputRecyclerAdapter.notifyDataSetChanged();
    }


    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(inputFragmentActivity,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(inputRecyclerAdapter);
        inputRecyclerAdapter.setOnItemClickListener(this);
        //recyclerView.addItemDecoration(new RecyclerItemDecoration(2,2,getResources().getColor(R.color.dividerColor)));
    }

    @Override
    public void onItemClick(View view, int position) {
        System.out.println(position);
    }
}
