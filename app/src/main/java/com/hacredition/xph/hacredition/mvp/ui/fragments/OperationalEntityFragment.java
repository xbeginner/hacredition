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
import com.hacredition.xph.hacredition.mvp.entity.FiscalSpend;
import com.hacredition.xph.hacredition.mvp.entity.OperationalEntity;
import com.hacredition.xph.hacredition.mvp.presenter.impl.FiscalSpendInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.OperationalEntityInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class OperationalEntityFragment extends BaseFragment
        implements InputInfoView<OperationalEntity>
        ,View.OnClickListener {


    private boolean isValidate = false;

    @BindView(R.id.operationalentity_idcard_editview)
    EditText idcardEditText;

    @BindView(R.id.operationalentity_name_editview)
    EditText nameEditText;

    @BindView(R.id.operationalentity_xiangmu_edit)
    EditText xiangmuEditText;

    @BindView(R.id.operationalentity_guimo_edit)
    EditText guimoEditText;

    @BindView(R.id.operationalentity_type_spinner)
    Spinner typeSpinner;

    @BindView(R.id.operationalentity_submit_button)
    Button submitButton;

    @Inject
    OperationalEntityInputPresenterImpl operationalEntityInputPresenter;

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
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext, R.array.operationalentity_type, android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_operationalentity_input;
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
        mPresenter = operationalEntityInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.operationalentity_submit_button) {
            if(awesomeValidation.validate()){
                OperationalEntity operationalEntity = initOperationalEntity();
                saveInfo(operationalEntity);
            }
        }

    }





    @Override
    public void saveInfo(OperationalEntity operationalEntity) {
        operationalEntityInputPresenter.saveInputInfo(operationalEntity);
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


    private OperationalEntity initOperationalEntity(){
        OperationalEntity operationalEntity = new OperationalEntity();
        operationalEntity.setNonghu(idcardEditText.getText().toString());
        operationalEntity.setGuimo(guimoEditText.getText().toString());
        operationalEntity.setLeixing((String)typeSpinner.getSelectedItem());
        operationalEntity.setXiangmu(xiangmuEditText.getText().toString());
        return operationalEntity;
    }


    private void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(xiangmuEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(guimoEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
    }

}
