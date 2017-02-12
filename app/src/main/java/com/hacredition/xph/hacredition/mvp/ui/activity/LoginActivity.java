package com.hacredition.xph.hacredition.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.LoginPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.base.BaseActivity;
import com.hacredition.xph.hacredition.mvp.view.LoginView;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;
import com.hacredition.xph.hacredition.utils.MyUtils;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;

public class LoginActivity extends BaseActivity
        implements LoginView,View.OnClickListener
        ,TextWatcher{

    @BindView(R.id.username_editview)
    EditText usernameEidt;


    @BindView(R.id.password_editview)
    EditText passwordEidt;

    @BindView(R.id.error_info)
    TextView errorText;

    @BindView(R.id.register_button)
    Button registerButton;

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.forget_password_text)
    TextView forgetPasswordText;

    @BindView(R.id.back_image_view_id)
    ImageView backButton;

    @Inject
    LoginPresenterImpl loginPresenter;

    @Inject
    Activity mActivity;

    @Inject
    @ContextLife("Activity")
    Context activityContext;



    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        usernameEidt.addTextChangedListener(this);
        passwordEidt.addTextChangedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backButton.setVisibility(View.VISIBLE);
        initPresenter();
    }

    @Override
    public void login() {
        loginPresenter.login(usernameEidt.getText().toString(), MyUtils.getMD5(passwordEidt.getText().toString()));
    }




    @Override
    public void showLoginErrorInfo(String msg) {
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(msg);
    }

    @Override
    public void loginSuccessfully(UserInfo userInfo) {
        loginPresenter.onDestory();
        Intent intent = new Intent(this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo", userInfo);
        intent.putExtras(bundle);
        setResult(MainActivity.LOGIN_SUCCESS_CODE,intent);
        this.finish();
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
        loginPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:{
                login();
                break;
            }
            case R.id.back_image_view_id:{
                loginPresenter.onDestory();
                setResult(MainActivity.LOGIN_FAIL_CODE);
                this.finish();
            }
        }
    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        errorText.setVisibility(View.GONE);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }



}
