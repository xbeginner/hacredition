package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.Entrepreneurship;
import com.hacredition.xph.hacredition.mvp.entity.OwnerShip;
import com.hacredition.xph.hacredition.mvp.presenter.impl.EntrepreneurshipInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.OnwerShipInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;

import java.security.acl.Owner;

import javax.inject.Inject;

import butterknife.BindView;


public class OwnerShipFragment extends BaseFragment
        implements InputInfoView<OwnerShip>
        ,View.OnClickListener {


    @BindView(R.id.ownership_name_editview)
    EditText idcardEditText;

    @BindView(R.id.ownership_name_editview)
    EditText nameEditText;

    @BindView(R.id.ownership_number_edit)
    EditText numberEditText;

    @BindView(R.id.ownership_eara_edit)
    EditText earaEditText;

    @BindView(R.id.ownership_value_edit)
    EditText valueEditText;

    @BindView(R.id.ownership_qixian_edit)
    EditText qixianEditText;

    @BindView(R.id.ownership_type_spinner)
    Spinner typeSpinner;

    @BindView(R.id.ownership_submit_button)
    Button submitButton;

    @Inject
    OnwerShipInputPresenterImpl ownershipPresenter;

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
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext, R.array.ownership_type, android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ownership_input;
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
        mPresenter = ownershipPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ownership_submit_button) {
            if(awesomeValidation.validate()){
                OwnerShip ship = initOwnerShip();
                saveInfo(ship);
            }
        }

    }





    @Override
    public void saveInfo(OwnerShip ownerShip) {
        ownershipPresenter.saveInputInfo(ownerShip);
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


    private OwnerShip initOwnerShip(){
        OwnerShip ship = new OwnerShip();

        ship.setUserId(idcardEditText.getText().toString());
        ship.setUserName(nameEditText.getText().toString());
        ship.setPinggujiage(Float.valueOf(valueEditText.getText().toString()));
        ship.setQixian(Integer.valueOf(qixianEditText.getText().toString()));
        ship.setMianji(Float.valueOf(earaEditText.getText().toString()));
        ship.setQuanzhengbianhao(numberEditText.getText().toString());
        ship.setLeixing((String)typeSpinner.getSelectedItem());

        return ship;
    }


    private void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(valueEditText,MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
        awesomeValidation.addValidation(numberEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(earaEditText,MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
        awesomeValidation.addValidation(qixianEditText,MyRegex.YEAR,getResources().getString(R.string.validation_error_year));
    }

}
