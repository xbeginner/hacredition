package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.CarInfo;
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CarInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;


public class IncomeInputFragment extends BaseFragment
        implements InputInfoView<IncomeInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener {



    @BindView(R.id.income_sum_editview)
    EditText sumEditText;

    @BindView(R.id.income_time_edit)
    EditText timeEditText;


    @BindView(R.id.income_info_editview)
    EditText infoEditText;


    @BindView(R.id.car_submit_button)
    Button submitButton;

    @BindView(R.id.income_type_spinner)
    Spinner typeSpinner;



    @Inject
    IncomeInputPresenterImpl incomeInputPresenter;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity inputFragmentActivity;

    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String  time = dateFormat.format(now);
        timeEditText.setText(time);
        timeEditText.setInputType(InputType.TYPE_NULL);
        timeEditText.setOnFocusChangeListener(this);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_income_info;
    }

    @Override
    public void saveInfo(IncomeInfo incomeInfo) {
        incomeInputPresenter.saveInputInfo(incomeInfo);
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
        mPresenter = incomeInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if(awesomeValidation.validate()){
            IncomeInfo incomeInfo = initIncomeInfo();
            saveInfo(incomeInfo);
        }
    }


    @Override
    public void onFocusChange(View view, boolean isFocus) {
        if(isFocus){
            Calendar cal = Calendar.getInstance();
            new DatePickerDialog(activityContext,this,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            ).show();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        timeEditText.setText(year+"-"+month+"-"+day);
    }

    @Override
    public void saveSuccessfully() {
        Toast.makeText(activityContext,"保存成功",Toast.LENGTH_LONG).show();
        inputFragmentActivity.finish();
    }

    @Override
    public void saveFailly() {
        Toast.makeText(activityContext,"保存失败,服务器连接错误",Toast.LENGTH_LONG).show();
    }


    private IncomeInfo initIncomeInfo(){
        IncomeInfo info = new IncomeInfo();

        return info;
    }

    @Override
    public void addValidation(){
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(pinpaiEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(chexingEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(numberEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(timeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
    }



}
