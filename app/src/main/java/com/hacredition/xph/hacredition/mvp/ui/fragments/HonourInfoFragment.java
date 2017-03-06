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
import com.hacredition.xph.hacredition.mvp.entity.Entrepreneurship;
import com.hacredition.xph.hacredition.mvp.entity.HonourInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.EntrepreneurshipInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HonourInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class HonourInfoFragment extends BaseFragment
        implements InputInfoView<HonourInfo>
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener
        ,View.OnFocusChangeListener{



    @BindView(R.id.honourinfo_idcard_editview)
    EditText idcardEditText;

    @BindView(R.id.honourinfo_name_editview)
    EditText nameEditText;

    @BindView(R.id.honourinfo_shixiang_edit)
    EditText shixiangEditText;

    @BindView(R.id.honourinfo_org_editview)
    EditText orgEditText;

    @BindView(R.id.honourinfo_time_editview)
    EditText timeEditText;

    @BindView(R.id.honourinfo_number_editview)
    EditText numberEditText;

    @BindView(R.id.honourinfo_level_spinner)
    Spinner levelSpinner;

    @BindView(R.id.honourinfo_submit_button)
    Button submitButton;

    @Inject
    HonourInfoInputPresenterImpl honourInfoInputPresenter;

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
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext, R.array.honour_level, android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapter);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_honourinfo_input;
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
        mPresenter = honourInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.honourinfo_submit_button) {
            if(awesomeValidation.validate()){
                HonourInfo info = initHonourInfo();
                saveInfo(info);
            }
        }

    }





    @Override
    public void saveInfo(HonourInfo honourInfo) {
        honourInfoInputPresenter.saveInputInfo(honourInfo);
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


    private HonourInfo initHonourInfo(){
        HonourInfo info = new HonourInfo();

        info.setNonghu(idcardEditText.getText().toString());
        info.setNonghuName(nameEditText.getText().toString());
        info.setJibie((String)levelSpinner.getSelectedItem());
        info.setBiaozhangwenjian(numberEditText.getText().toString());
        info.setBiaozhangshijian(timeEditText.getText().toString());
        info.setBiaozhangbumen(orgEditText.getText().toString());
        info.setBiaozhangshixiang(shixiangEditText.getText().toString());
        info.setInputUserId(MyUtils.getInputUserId());

        return info;
    }


    private void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(numberEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(orgEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(shixiangEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
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
