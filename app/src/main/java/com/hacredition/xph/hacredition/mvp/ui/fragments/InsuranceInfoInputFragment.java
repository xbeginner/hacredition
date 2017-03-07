package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.CourtInfo;
import com.hacredition.xph.hacredition.mvp.entity.InsuranceInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CourtInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.InsuranceInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class InsuranceInfoInputFragment extends BaseFragment
        implements InputInfoView<InsuranceInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener{



    @BindView(R.id.insurance_idcard_editview)
    EditText idcardEditText;

    @BindView(R.id.insurance_name_editview)
    EditText nameEditText;

    @BindView(R.id.insurance_toubaojine_edit)
    EditText toubaojineEditText;

    @BindView(R.id.insurance_baofeijine_edit)
    EditText baofeijineEditText;

    @BindView(R.id.insurance_canbaotime_edit)
    EditText canbaotimeEditText;

    @BindView(R.id.insurance_daoqitime_edit)
    EditText daoqitimeEditText;


    @BindView(R.id.insurance_leixing_spinner)
    Spinner leixingSpinner;

    @BindView(R.id.insurance_jiaofei_spinner)
    Spinner jiaofeiSpinner;


    @BindView(R.id.insurance_submit_button)
    Button submitButton;


    @Inject
    InsuranceInfoInputPresenterImpl insuranceInfoInputPresenter;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity inputFragmentActivity;

    private static AwesomeValidation awesomeValidation;

    private EditText timeEditText;


    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        canbaotimeEditText.setInputType(InputType.TYPE_NULL);
        canbaotimeEditText.setOnFocusChangeListener(this);
        daoqitimeEditText.setInputType(InputType.TYPE_NULL);
        daoqitimeEditText.setOnFocusChangeListener(this);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(activityContext,R.array.insurance_baoxian,android.R.layout.simple_spinner_dropdown_item);
        leixingSpinner.setAdapter(adapter1);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(activityContext,R.array.insurance_jiaofei,android.R.layout.simple_spinner_dropdown_item);
        jiaofeiSpinner.setAdapter(adapter2);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_insuranceinfo_input;
    }

    @Override
    public void saveInfo(InsuranceInfo insuranceInfo) {
        insuranceInfoInputPresenter.saveInputInfo(insuranceInfo);
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
        mPresenter = insuranceInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view){
        if(awesomeValidation.validate()){
            InsuranceInfo insuranceInfo = initInsuranceInfo();
            saveInfo(insuranceInfo);
        }
    }


    @Override
    public void onFocusChange(View view, boolean isFocus) {
        if(view.getId()==R.id.insurance_canbaotime_edit){
            timeEditText = canbaotimeEditText;
        }
        if(view.getId()==R.id.insurance_daoqitime_edit){
            timeEditText = daoqitimeEditText;
        }
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


    private InsuranceInfo initInsuranceInfo(){
        InsuranceInfo info = new InsuranceInfo();
        info.setInputUserId(MyUtils.getInputUserId());
        info.setNonghu(idcardEditText.getText().toString());
        info.setNonghuName(nameEditText.getText().toString());
        info.setDaoqishijian(daoqitimeEditText.getText().toString());
        info.setJiaofeileixing((String)jiaofeiSpinner.getSelectedItem());
        info.setBaofeijine(Float.valueOf(baofeijineEditText.getText().toString()));
        info.setToubaojine(Float.valueOf(toubaojineEditText.getText().toString()));
        info.setCanbaoshijian(canbaotimeEditText.getText().toString());
        info.setBaoxianleixing((String)leixingSpinner.getSelectedItem());
        return info;
    }


    private void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(canbaotimeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
        awesomeValidation.addValidation(daoqitimeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
        awesomeValidation.addValidation(baofeijineEditText, MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
        awesomeValidation.addValidation(toubaojineEditText, MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
    }

}
