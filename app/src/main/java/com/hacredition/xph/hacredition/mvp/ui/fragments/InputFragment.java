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
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.hacredition.xph.hacredition.mvp.ui.activity.InputComponentActivity;
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
        implements InputView,
        InputRecyclerAdapter.OnInputItemClickListener{

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

    private static boolean isInit = false;


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

    @Override
    protected void addValidation() {

    }

    /**
     * 根据权限进行权限的查询
     */
    @Override
    public void showInputItems() {
            if(!isInit) {
                mInputPresenterImpl.setInputItems(MainActivity.mUserInfo);
                hideProgress();
                isInit = true;
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
//        recyclerView.setLayoutManager(new LinearLayoutManager(inputFragmentActivity,
//                LinearLayoutManager.VERTICAL, false));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(inputRecyclerAdapter);
        inputRecyclerAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, String FragmentName) {
            Bundle bundle = new Bundle();
            bundle.putString("fragmentName",FragmentName);
            Intent intent = new Intent(activityContext, InputComponentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
