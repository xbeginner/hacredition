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
import com.hacredition.xph.hacredition.mvp.entity.CourtInfo;
import com.hacredition.xph.hacredition.mvp.entity.CreditInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CourtInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CreditInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class CourtInfoInputFragment extends BaseFragment
        implements InputInfoView<CourtInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener{



    @BindView(R.id.courtinfo_idcard_editview)
    MaterialEditText idcardEditText;

    @BindView(R.id.courtinfo_name_editview)
    MaterialEditText nameEditText;

    @BindView(R.id.courtinfo_time_editview)
    MaterialEditText timeEditText;

    @BindView(R.id.courtinfo_shiyou_editview)
    MaterialEditText shiyouEditText;


    @BindView(R.id.courtinfo_type_spinner)
    Spinner typeSpinner;


    @BindView(R.id.courtinfo_submit_button)
    Button submitButton;


    @Inject
    CourtInfoInputPresenterImpl courtInfoInputPresenter;

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
        timeEditText.setInputType(InputType.TYPE_NULL);
        timeEditText.setOnFocusChangeListener(this);
        typeSpinner.setPrompt("请选择");
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext,R.array.courtinfo_type,android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_courtinfo_input;
    }

    @Override
    public void saveInfo(CourtInfo courtInfo) {
        courtInfoInputPresenter.saveInputInfo(courtInfo);
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
        mPresenter = courtInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view){
        if(awesomeValidation.validate()){
            CourtInfo courtInfo = initCourtInfo();
            saveInfo(courtInfo);
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


    private CourtInfo initCourtInfo(){
        CourtInfo info = new CourtInfo();
        info.setInputUserId(MyUtils.getInputUserId());
        info.setNonghu(idcardEditText.getText().toString());
        info.setNonghuName(nameEditText.getText().toString());
        info.setShiyou(shiyouEditText.getText().toString());
        info.setTime(timeEditText.getText().toString());
        info.setType((String)typeSpinner.getSelectedItem());
        return info;
    }

    @Override
    public void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(shiyouEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(timeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
    }

}
