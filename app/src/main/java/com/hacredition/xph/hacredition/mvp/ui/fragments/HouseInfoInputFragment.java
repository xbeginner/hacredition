package com.hacredition.xph.hacredition.mvp.ui.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.hacredition.xph.hacredition.listener.SaveCallback;
import com.hacredition.xph.hacredition.mvp.entity.HouseInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.HouseInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.File;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class HouseInfoInputFragment extends BaseFragment
        implements InputInfoView<HouseInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener
        ,RadioGroup.OnCheckedChangeListener {




    @BindView(R.id.idcard_editview)
    EditText idcardEditText;

    @BindView(R.id.houseinfo_buildtime_edit)
    EditText buildTimeEditText;

    @BindView(R.id.houseinfo_area_edit)
    EditText areaEditText;

    @BindView(R.id.fangchanzhenghao_editview)
    EditText fangchanzhenghaoEditText;

    @BindView(R.id.houseinfo_location_edit)
    EditText locationEditText;

    @BindView(R.id.houseinfo_value_edit)
    EditText valueEditText;

    @BindView(R.id.houseinfo_radiogroup)
    RadioGroup metalogRadioGroup;

    @BindView(R.id.houseinfo_spinner_id)
    Spinner spinner;

    @BindView(R.id.houseinfo_submit_button)
    Button submitButton;

    @BindView(R.id.take_pic_button)
    Button takePicButton;

    @BindView(R.id.houseinfo_pic_img)
    ImageView houseInfoImageView;

    @Inject
    HouseInfoInputPresenterImpl houseInfoInputPresenter;

    @Inject
    @ContextLife("Activity")
    Context activityContext;



    @Inject
    Activity inputFragmentActivity;

    public final static int CONSULT_DOC_PICTURE = 1000;

    public final static int CONSULT_DOC_CAMERA = 1001;

    private Uri outputFileUri;

    private String isDiya = "否";

    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        buildTimeEditText.setInputType(InputType.TYPE_NULL);
        buildTimeEditText.setOnFocusChangeListener(this);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activityContext,R.array.houseinfo_type,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        addValidation();
        submitButton.setOnClickListener(this);
        takePicButton.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_houseinfo_input;
    }

    @Override
    public void saveInfo(HouseInfo houseInfo) {
        houseInfoInputPresenter.saveInputInfo(houseInfo);
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
        mPresenter = houseInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.houseinfo_submit_button:{
                 if(awesomeValidation.validate()){
                     HouseInfo houseInfo = initHouseInfo();
                     saveInfo(houseInfo);
                 }
                 break;
             }
             case R.id.take_pic_button:{
                 CharSequence[] items = { "从本地选择", "相机拍照" };
                 final int SELECT_FROM_PIC = 0;
                 final int SELECT_FROM_CAMERA = 1;
                 AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                 builder.setTitle("选择图片来源").setItems(items, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int which) {
                         if(which==SELECT_FROM_PIC){
                             Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                             intent.addCategory(Intent.CATEGORY_OPENABLE);
                             intent.setType("image/*");
                             startActivityForResult(Intent.createChooser(intent, "选择图片"), CONSULT_DOC_PICTURE);
                         }
                         if(which==SELECT_FROM_CAMERA){
                             File file = new File(Environment.getExternalStorageDirectory(), "textphoto.jpg");
                             outputFileUri = Uri.fromFile(file);
                             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                             intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                             startActivityForResult(intent, CONSULT_DOC_CAMERA);
                         }
                     }
                 }).create().show();
                 }
                 break;
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
        buildTimeEditText.setText(year+"-"+month+"-"+day);
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


    private HouseInfo initHouseInfo(){
        HouseInfo houseInfo = new HouseInfo();
        if(!valueEditText.getText().equals("")){
            houseInfo.setDangqianguzhi(Float.valueOf(valueEditText.getText().toString()));
        }
        houseInfo.setFangwuxingzhi((String)spinner.getSelectedItem());
        Drawable drawable = houseInfoImageView.getDrawable();
        if(drawable!=null){
            Bitmap bitmap = MyUtils.drawableToBitmap(drawable);
            byte[] housePicByte = MyUtils.Bitmap2Bytes(bitmap);
            houseInfo.setFangwutupiao(housePicByte);
        }
        houseInfo.setFangchanzhenghao(fangchanzhenghaoEditText.getText().toString());
        houseInfo.setGoujianriqi(buildTimeEditText.getText().toString());
        houseInfo.setJianzhumianji(Float.valueOf(areaEditText.getText().toString()));
        houseInfo.setNonghuIdcard(idcardEditText.getText().toString());
        houseInfo.setShifoudiya(isDiya);
        houseInfo.setSuozaidi(locationEditText.getText().toString());
        houseInfo.setInputUserId(MyUtils.getInputUserId());
        return houseInfo;
    }


    @Override
    public void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(buildTimeEditText,MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));
        awesomeValidation.addValidation(areaEditText,MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
        awesomeValidation.addValidation(locationEditText,MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(valueEditText,MyRegex.ISFLOAT,getResources().getString(R.string.validation_error_float));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONSULT_DOC_PICTURE && null != data) {
            Uri selectedImage = data.getData();
            houseInfoImageView.setVisibility(View.VISIBLE);
            Glide.with(activityContext)
                    .load(selectedImage)
                    .placeholder(R.drawable.no_pic)
                    .fitCenter()
                    .into(houseInfoImageView);
        }else{
            houseInfoImageView.setVisibility(View.VISIBLE);
            Glide.with(activityContext)
                    .load(outputFileUri)
                    .placeholder(R.drawable.no_pic)
                    .fitCenter()
                    .into(houseInfoImageView);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
         if(checkedId==R.id.houseInfo_radiobutton_yes){
             isDiya = "是";
         }else{
             isDiya = "否";
         }
    }
}
