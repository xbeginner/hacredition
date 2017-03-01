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
import com.hacredition.xph.hacredition.mvp.entity.OperationalEntity;
import com.hacredition.xph.hacredition.mvp.presenter.impl.EntrepreneurshipInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.OperationalEntityInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;

import javax.inject.Inject;

import butterknife.BindView;


public class EntrepreneurshipFragment extends BaseFragment
        implements InputInfoView<Entrepreneurship>
        ,View.OnClickListener {


    private boolean isValidate = false;

    @BindView(R.id.entrepreneurship_idcard_editview)
    EditText idcardEditText;

    @BindView(R.id.entrepreneurship_name_editview)
    EditText nameEditText;

    @BindView(R.id.entrepreneurship_xiangmu_edit)
    EditText xiangmuEditText;

    @BindView(R.id.entrepreneurship_guimo_edit)
    EditText guimoEditText;

    @BindView(R.id.entrepreneurship_jindu_edit)
    EditText jinduEditText;

    @BindView(R.id.entrepreneurship_type_spinner)
    Spinner typeSpinner;

    @BindView(R.id.entrepreneurship_submit_button)
    Button submitButton;

    @Inject
    EntrepreneurshipInputPresenterImpl entrepreneurshipInputPresenter;

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
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext, R.array.entrepreneurship_type, android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        addValidation();
        submitButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_entrepreneurship_input;
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
        mPresenter = entrepreneurshipInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.entrepreneurship_submit_button) {
            if(awesomeValidation.validate()){
                Entrepreneurship ship = initEntrepreneurship();
                saveInfo(ship);
            }
        }

    }





    @Override
    public void saveInfo(Entrepreneurship entrepreneurship) {
        entrepreneurshipInputPresenter.saveInputInfo(entrepreneurship);
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


    private Entrepreneurship initEntrepreneurship(){
        Entrepreneurship ship = new Entrepreneurship();

        ship.setNonghu(idcardEditText.getText().toString());
        ship.setGuimo(guimoEditText.getText().toString());
        ship.setLeixing((String)typeSpinner.getSelectedItem());
        ship.setXiangmu(xiangmuEditText.getText().toString());
        ship.setDangqianjindu(jinduEditText.getText().toString());

        return ship;
    }


    private void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(xiangmuEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(guimoEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(jinduEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
    }

}
