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
import com.hacredition.xph.hacredition.mvp.ui.activity.LoginActivity;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputView;
import com.hacredition.xph.hacredition.utils.RecyclerItemDecoration;

import org.w3c.dom.Text;

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

    public static int REQUEST_CODE;


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

    @Override
    public void showInputItems() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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