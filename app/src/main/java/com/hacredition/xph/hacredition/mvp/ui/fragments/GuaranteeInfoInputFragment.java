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
import com.hacredition.xph.hacredition.mvp.entity.GuaranteeInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CreditInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.GuaranteeInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class GuaranteeInfoInputFragment extends BaseFragment
        implements InputInfoView<GuaranteeInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener
        ,RadioGroup.OnCheckedChangeListener {



    @BindView(R.id.guarantee_idcard_editview)
    EditText idcardEditText;

    @BindView(R.id.guarantee_name_editview)
    EditText nameEditText;

    @BindView(R.id.guaranted_idcard_editview)
    EditText guarantedIdcardEditText;

    @BindView(R.id.guaranted_name_editview)
    EditText guarantedNameEditText;


    @BindView(R.id.guarantee_jine_edit)
    EditText jineEditText;

    @BindView(R.id.guarantee_weijieqing_edit)
    EditText weijieqingEditText;

    @BindView(R.id.guarantee_fafangtime_edit)
    EditText fafangTimeEditText;

    @BindView(R.id.guarantee_daoqitime_edit)
    EditText daoqiTimeEditText;



    @BindView(R.id.guarantee_buliang_radiogroup)
    RadioGroup buliangRadioGroup;

    @BindView(R.id.guarantee_daichang_radiogroup)
    RadioGroup daichangRadioGroup;

    @BindView(R.id.guarantee_yuqi_radiogroup)
    RadioGroup yuqiRadioGroup;


    @BindView(R.id.guarantee_submit_button)
    Button submitButton;


    @Inject
    GuaranteeInfoInputPresenterImpl guaranteeInfoInputPresenter;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity inputFragmentActivity;



    private EditText editText;

    private String buliang;

    private String daichang;

    private String yuqi;


    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        fafangTimeEditText.setInputType(InputType.TYPE_NULL);
        fafangTimeEditText.setOnFocusChangeListener(this);
        daoqiTimeEditText.setInputType(InputType.TYPE_NULL);
        daoqiTimeEditText.setOnFocusChangeListener(this);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_guaranteeinfo_input;
    }

    @Override
    public void saveInfo(GuaranteeInfo guaranteeInfo) {
        guaranteeInfoInputPresenter.saveInputInfo(guaranteeInfo);
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
        mPresenter = guaranteeInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view){
        if(awesomeValidation.validate()){
            GuaranteeInfo guaranteeInfo = initGuaranteeInfo();
            saveInfo(guaranteeInfo);
        }
    }


    @Override
    public void onFocusChange(View view, boolean isFocus) {
        if(isFocus){
            if(view.getId()==R.id.guarantee_fafangtime_edit){
                editText = fafangTimeEditText;
            }
            if(view.getId()==R.id.guarantee_daoqitime_edit){
                editText = daoqiTimeEditText;
            }
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
        editText.setText(year+"-"+month+"-"+day);
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


    private GuaranteeInfo initGuaranteeInfo(){
        GuaranteeInfo info = new GuaranteeInfo();
        info.setGuaranteeUserIdcard(idcardEditText.getText().toString());
        info.setGuaranteeUserName(nameEditText.getText().toString());
        info.setGuarantedUserIdcard(guarantedIdcardEditText.getText().toString());
        info.setGuarantedUserName(guarantedNameEditText.getText().toString());
        info.setDanbaojine(Float.valueOf(jineEditText.getText().toString()));
        info.setWeijieqingjine(Float.valueOf(weijieqingEditText.getText().toString()));
        info.setFafangriqi(fafangTimeEditText.getText().toString());
        info.setDaoqiriqi(daoqiTimeEditText.getText().toString());
        info.setYuqi(yuqi);
        info.setXingchengbuliang(buliang);
        info.setDaichang(daichang);
        info.setInputUserId(MyUtils.getInputUserId());
        return info;
    }


    @Override
    public void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(guarantedIdcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(guarantedNameEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(fafangTimeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
        awesomeValidation.addValidation(daoqiTimeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
        awesomeValidation.addValidation(jineEditText, MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
        awesomeValidation.addValidation(weijieqingEditText, MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
         switch (checkedId){
             case R.id.guarantee_buliang_yes:{
                 buliang="是";
                 break;
             }
             case R.id.guarantee_buliang_no:{
                 buliang="否";
                 break;
             }
             case R.id.guarantee_daichang_yes:{
                 daichang="是";
                 break;
             }
             case R.id.guarantee_daichang_no:{
                 daichang="否";
                 break;
             }
             case R.id.guarantee_yuqi_yes:{
                 yuqi="是";
                 break;
             }
             case R.id.guarantee_yuqi_no:{
                 yuqi="否";
                 break;
             }
         }
    }
}
