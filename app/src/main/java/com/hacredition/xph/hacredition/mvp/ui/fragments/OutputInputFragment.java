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
import com.hacredition.xph.hacredition.mvp.entity.IncomeInfo;
import com.hacredition.xph.hacredition.mvp.entity.OutputInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.IncomeInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.OutputInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;


public class OutputInputFragment extends BaseFragment
        implements InputInfoView<OutputInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener {



    @BindView(R.id.output_sum_editview)
    EditText sumEditText;

    @BindView(R.id.output_time_edit)
    EditText timeEditText;


    @BindView(R.id.output_info_editview)
    EditText infoEditText;


    @BindView(R.id.output_submit_button)
    Button submitButton;

    @BindView(R.id.output_type_spinner)
    Spinner typeSpinner;


    @BindView(R.id.spend_type_spinner)
    Spinner spendSpinner;



    @Inject
    OutputInfoInputPresenterImpl outputInputPresenter;

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
        String time = dateFormat.format(now);
        timeEditText.setText(time);
        timeEditText.setInputType(InputType.TYPE_NULL);
        timeEditText.setOnFocusChangeListener(this);
        //addValidation();
        submitButton.setOnClickListener(this);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext,R.array.outputType,android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(activityContext,R.array.spendType,android.R.layout.simple_spinner_dropdown_item);
        spendSpinner.setAdapter(adapter1);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_output_info;
    }

    @Override
    public void saveInfo(OutputInfo outputInfo) {
        outputInputPresenter.saveInputInfo(outputInfo);
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
        mPresenter = outputInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        //if(awesomeValidation.validate()){
            OutputInfo outputInfo = initOutputInfo();
            saveInfo(outputInfo);
       // }
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
        String mStr = month<10?"0"+month:month+"";
        String mDay = day<10?"0"+day:day+"";
        timeEditText.setText(year+"-"+mStr+"-"+mDay);
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


    private OutputInfo initOutputInfo(){
        OutputInfo info = new OutputInfo();
        info.setOutputInfo(infoEditText.getText().toString());
        info.setOutputSum(Float.valueOf(sumEditText.getText().toString()));
        info.setOutputTime(timeEditText.getText().toString());
        info.setOutputType((String)typeSpinner.getSelectedItem());
        info.setSpendType((String)spendSpinner.getSelectedItem());
        return info;
    }

    @Override
    public void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(sumEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(timeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
    }



}
