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

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.InputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.LoginActivity;
import com.hacredition.xph.hacredition.mvp.ui.activity.MainActivity;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputView;
import com.hacredition.xph.hacredition.utils.RecyclerItemDecoration;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class InputFragment extends BaseFragment
        implements InputView{

    @BindView(R.id.input_item_recyclerview_id)
    RecyclerView recyclerView;

    @BindView(R.id.input_progress_bar_id)
    ProgressBar progressBar;

    @BindView(R.id.input_no_actions_view_id)
    TextView textView;

    @Inject
    Activity newsFragmentActivity;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    InputPresenterImpl mInputPresenterImpl;





    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initRecyclerView();
        initPresenter();
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
            Intent intent = new Intent(newsFragmentActivity, LoginActivity.class);
            startActivityForResult(intent,MainActivity.LOGIN_SUCCESS_CODE);
        }else{
            mInputPresenterImpl.setInputItems(MainActivity.mUserInfo);
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

    }

    @Override
    public void initPresenter() {

    }

    private void initRecyclerView() {

    }


}
