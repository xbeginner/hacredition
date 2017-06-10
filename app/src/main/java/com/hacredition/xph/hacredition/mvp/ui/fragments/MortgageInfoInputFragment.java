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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.CreditInfo;
import com.hacredition.xph.hacredition.mvp.entity.MortgageInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CreditInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.MortgageInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class MortgageInfoInputFragment extends BaseFragment
        implements InputInfoView<MortgageInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener {



    @BindView(R.id.mortgage_idcard_editview)
    MaterialEditText idcardEditText;

    @BindView(R.id.mortgage_name_editview)
    MaterialEditText nameEditText;

    @BindView(R.id.mortgage_diyawu_editview)
    MaterialEditText diyawuEditText;

    @BindView(R.id.mortgage_jine_edit)
    MaterialEditText jineEditText;


    @BindView(R.id.mortgage_jiezhiriqi_edit)
    MaterialEditText jiezhiriqiEditText;

    @BindView(R.id.mortgage_submit_button)
    Button submitButton;


    @Inject
    MortgageInfoInputPresenterImpl mortgageInfoInputPresenter;

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
        jiezhiriqiEditText.setInputType(InputType.TYPE_NULL);
        jiezhiriqiEditText.setOnFocusChangeListener(this);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mortgageinfo_input;
    }

    @Override
    public void saveInfo(MortgageInfo mortgageInfo) {
        mortgageInfoInputPresenter.saveInputInfo(mortgageInfo);
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
        mPresenter = mortgageInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view){
        if(awesomeValidation.validate()){
            MortgageInfo info = initMortgageInfo();
            saveInfo(info);
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
        jiezhiriqiEditText.setText(year+"-"+month+"-"+day);
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


    private MortgageInfo initMortgageInfo(){
        MortgageInfo info = new MortgageInfo();
        info.setNonghu(idcardEditText.getText().toString());
        info.setNonghuName(nameEditText.getText().toString());
        info.setDiyawu(diyawuEditText.getText().toString());
        info.setDiyajine(Float.valueOf(jineEditText.getText().toString()));
        info.setDiyaqixian(jiezhiriqiEditText.getText().toString());
        info.setInputUserId(MyUtils.getInputUserId());
        return info;
    }


    @Override
    public void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(diyawuEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(jineEditText, MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
        awesomeValidation.addValidation(jiezhiriqiEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
    }




}
