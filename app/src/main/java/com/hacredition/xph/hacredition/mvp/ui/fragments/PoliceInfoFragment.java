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
import com.hacredition.xph.hacredition.mvp.entity.HonourInfo;
import com.hacredition.xph.hacredition.mvp.entity.PoliceInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HonourInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.PoliceInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.activity.MainActivity;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class PoliceInfoFragment extends BaseFragment
        implements InputInfoView<PoliceInfo>
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener
        ,View.OnFocusChangeListener{

    @BindView(R.id.policeinfo_idcard_editview)
    MaterialEditText idcardEditText;

    @BindView(R.id.policeinfo_name_editview)
    MaterialEditText nameEditText;


    @BindView(R.id.policeinfo_time_editview)
    MaterialEditText timeEditText;

    @BindView(R.id.policeinfo_xianzhuang_editview)
    MaterialEditText xianzhuangEditText;

    @BindView(R.id.policeinfo_type_spinner)
    Spinner typeSpinner;

    @BindView(R.id.policeinfo_submit_button)
    Button submitButton;

    @Inject
    PoliceInfoInputPresenterImpl policeInfoInputPresenter;

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
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext, R.array.policeinfo_type, android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_policeinfo_input;
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
        mPresenter = policeInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.policeinfo_submit_button) {
            if(awesomeValidation.validate()){
                PoliceInfo info = initPoliceInfo();
                saveInfo(info);
            }
        }

    }





    @Override
    public void saveInfo(PoliceInfo policeInfo) {
        policeInfoInputPresenter.saveInputInfo(policeInfo);
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


    private PoliceInfo initPoliceInfo(){
        PoliceInfo info = new PoliceInfo();

        info.setNonghu(idcardEditText.getText().toString());
        info.setNonghuName(nameEditText.getText().toString());
        info.setType((String)typeSpinner.getSelectedItem());
        info.setTime(timeEditText.getText().toString());
        info.setXianzhuang(xianzhuangEditText.getText().toString());
        info.setInputUserId(MyUtils.getInputUserId());

        return info;
    }


    @Override
    public void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(xianzhuangEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(timeEditText,MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        timeEditText.setText(year+"-"+month+"-"+day);
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
}
