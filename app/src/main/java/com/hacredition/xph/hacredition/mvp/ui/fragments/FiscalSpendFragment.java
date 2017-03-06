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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.FiscalSpend;
import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.FiscalSpendInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import java.io.File;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class FiscalSpendFragment extends BaseFragment
        implements InputInfoView<FiscalSpend>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener {


    private boolean isValidate = false;

    @BindView(R.id.fiscalspend_idcard_editview)
    EditText idcardEditText;

    @BindView(R.id.fiscalspend_name_editview)
    EditText nameEditText;

    @BindView(R.id.fiscalspend_time_edit)
    EditText timeEditText;

    @BindView(R.id.fiscalspend_org_edit)
    EditText orgNameEditText;

    @BindView(R.id.fiscalspend_zhiwu_edit)
    EditText zhiwuEditText;

    @BindView(R.id.fiscalspend_location_edit)
    EditText orgLocationEditText;

    @BindView(R.id.fiscalspend_jibie_spinner)
    Spinner jibieSpinner;

    @BindView(R.id.fiscalspend_leixing_spinner)
    Spinner leixingSpinner;

    @BindView(R.id.fiscalspend_submit_button)
    Button submitButton;

    @Inject
    FiscalSpendInputPresenterImpl fiscalSpendPresenter;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity inputFragmentActivity;

    private static AwesomeValidation awesomeValidation;


    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        timeEditText.setInputType(InputType.TYPE_NULL);
        timeEditText.setOnFocusChangeListener(this);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(activityContext, R.array.fiscalspend_jibie, android.R.layout.simple_spinner_dropdown_item);
        jibieSpinner.setAdapter(adapter1);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(activityContext, R.array.fiscalspend_bianzhileixing, android.R.layout.simple_spinner_dropdown_item);
        leixingSpinner.setAdapter(adapter2);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fiscalspend_input;
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
        mPresenter = fiscalSpendPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fiscalspend_submit_button) {
            if(awesomeValidation.validate()){
                FiscalSpend fiscalSpend = initFiscalSpend();
                saveInfo(fiscalSpend);
            }
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
    public void saveInfo(FiscalSpend fiscalSpend) {
        fiscalSpendPresenter.saveInputInfo(fiscalSpend);
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


    private FiscalSpend initFiscalSpend(){
        FiscalSpend fiscalSpend = new FiscalSpend();
        fiscalSpend.setBianzhileixing((String)leixingSpinner.getSelectedItem());
        fiscalSpend.setDanweidizhi(orgLocationEditText.getText().toString());
        fiscalSpend.setDanweimingcheng(orgNameEditText.getText().toString());
        fiscalSpend.setGongzuoshijiian(timeEditText.getText().toString());
        fiscalSpend.setJibie((String)jibieSpinner.getSelectedItem());
        fiscalSpend.setNonghu(idcardEditText.getText().toString());
        fiscalSpend.setXingming(nameEditText.getText().toString());
        fiscalSpend.setZhiwu(zhiwuEditText.getText().toString());
        fiscalSpend.setInputUserId(MyUtils.getInputUserId());
        return fiscalSpend;
    }


    private void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(timeEditText,MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
        awesomeValidation.addValidation(nameEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(orgNameEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(zhiwuEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(orgLocationEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
    }

}
