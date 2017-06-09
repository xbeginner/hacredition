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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
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
import com.hacredition.xph.hacredition.mvp.entity.CreditInfo;
import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CreditInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.MainActivity;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.File;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class CreditInfoInputFragment extends BaseFragment
        implements InputInfoView<CreditInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener
        ,RadioGroup.OnCheckedChangeListener {



    @BindView(R.id.creditinfo_idcard_editview)
    MaterialEditText idcardEditText;

    @BindView(R.id.creditinfo_name_editview)
    MaterialEditText nameEditText;

    @BindView(R.id.creditinfo_yongtu_editview)
    MaterialEditText yongtuEditText;

    @BindView(R.id.creditinfo_shouxin_edit)
    MaterialEditText shouxinEditText;

    @BindView(R.id.creditinfo_fafang_edit)
    MaterialEditText fafangEditText;

    @BindView(R.id.creditinfo_fafangtime_edit)
    MaterialEditText fafangTimeEditText;

    @BindView(R.id.creditinfo_daoqitime_edit)
    MaterialEditText daoqiTimeEditText;

    @BindView(R.id.creditinfo_anqifuxi_radiogroup)
    RadioGroup anqifuxiRadioGroup;

    @BindView(R.id.creditinfo_buliang_radiogroup)
    RadioGroup buliangRadioGroup;

    @BindView(R.id.creditinfo_daichang_radiogroup)
    RadioGroup daichangRadioGroup;

    @BindView(R.id.creditinfo_yuqi_radiogroup)
    RadioGroup yuqiRadioGroup;

    @BindView(R.id.creditinfo_huanqing_radiogroup)
    RadioGroup huanqingRadioGroup;


    @BindView(R.id.creditinfo_fangshi_spinner)
    Spinner fangshiSpinner;

    @BindView(R.id.creditinfo_zhuangtai_spinner)
    Spinner zhuangtaiSpinner;

    @BindView(R.id.creditinfo_submit_button)
    Button submitButton;


    @Inject
    CreditInfoInputPresenterImpl creditInfoInputPresenter;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity inputFragmentActivity;


    private EditText editText;

    private String anqifuxi;

    private String buliang;

    private String daichang;

    private String yuqi;

    private String huanqing;

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
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(activityContext,R.array.creditinfo_fangshi,android.R.layout.simple_spinner_dropdown_item);
        fangshiSpinner.setAdapter(adapter1);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(activityContext,R.array.creditinfo_zhuangtai,android.R.layout.simple_spinner_dropdown_item);
        zhuangtaiSpinner.setAdapter(adapter2);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_creditinfo_input;
    }

    @Override
    public void saveInfo(CreditInfo creditInfo) {
        creditInfoInputPresenter.saveInputInfo(creditInfo);
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
        mPresenter = creditInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view){
        if(awesomeValidation.validate()){
            CreditInfo creditInfo = initCreditInfo();
            saveInfo(creditInfo);
        }
    }


    @Override
    public void onFocusChange(View view, boolean isFocus) {
        if(isFocus){
            if(view.getId()==R.id.creditinfo_fafangtime_edit){
                editText = fafangTimeEditText;
            }
            if(view.getId()==R.id.creditinfo_daoqitime_edit){
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


    private CreditInfo initCreditInfo(){
        CreditInfo creditInfo = new CreditInfo();
        creditInfo.setNonghu(idcardEditText.getText().toString());
        creditInfo.setNonghuName(nameEditText.getText().toString());
        creditInfo.setJiekuangyongtu(yongtuEditText.getText().toString());
        creditInfo.setFafangjine(Float.valueOf(fafangEditText.getText().toString()));
        creditInfo.setDaoqishijian(daoqiTimeEditText.getText().toString());
        creditInfo.setAnqifuxi(anqifuxi);
        creditInfo.setFenleizhuangtai((String)zhuangtaiSpinner.getSelectedItem());
        creditInfo.setXingchengbuliang(buliang);
        creditInfo.setShouxinjine(Float.valueOf(shouxinEditText.getText().toString()));
        creditInfo.setDanbaofangshi((String)fangshiSpinner.getSelectedItem());
        creditInfo.setShifoudaichang(daichang);
        creditInfo.setShifouyuqi(yuqi);
        creditInfo.setShifouhuanqing(huanqing);
        creditInfo.setInputId(MyUtils.getInputUserId());
        return creditInfo;
    }


    @Override
    public void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(yongtuEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(shouxinEditText, MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
        awesomeValidation.addValidation(fafangEditText, MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
        awesomeValidation.addValidation(fafangTimeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
        awesomeValidation.addValidation(daoqiTimeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
         switch (checkedId){
             case R.id.creditinfo_anqifuxi_yes:{
                 anqifuxi="是";
                 break;
             }
             case R.id.creditinfo_anqifuxi_no:{
                 anqifuxi="否";
                 break;
             }
             case R.id.creditinfo_buliang_yes:{
                 buliang="是";
                 break;
             }
             case R.id.creditinfo_buliang_no:{
                 buliang="否";
                 break;
             }
             case R.id.creditinfo_daichang_yes:{
                 daichang="是";
                 break;
             }
             case R.id.creditinfo_daichang_no:{
                 daichang="否";
                 break;
             }
             case R.id.creditinfo_yuqi_yes:{
                 yuqi="是";
                 break;
             }
             case R.id.creditinfo_yuqi_no:{
                 yuqi="否";
                 break;
             }
             case R.id.creditinfo_huanqing_yes:{
                 huanqing="是";
                 break;
             }
             case R.id.creditinfo_huanqing_no:{
                 huanqing="否";
                 break;
             }

         }
    }
}
