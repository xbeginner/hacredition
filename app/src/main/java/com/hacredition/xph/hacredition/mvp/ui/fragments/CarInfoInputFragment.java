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
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.bumptech.glide.Glide;
import com.hacredition.xph.hacredition.R;
import com.hacredition.xph.hacredition.di.scope.ContextLife;
import com.hacredition.xph.hacredition.mvp.entity.CarInfo;
import com.hacredition.xph.hacredition.mvp.entity.MachineInfo;
import com.hacredition.xph.hacredition.mvp.presenter.impl.CarInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.presenter.impl.MachineInfoInputPresenterImpl;
import com.hacredition.xph.hacredition.mvp.ui.fragments.base.BaseFragment;
import com.hacredition.xph.hacredition.mvp.view.InputInfoView;
import com.hacredition.xph.hacredition.utils.MyRegex;
import com.hacredition.xph.hacredition.utils.MyUtils;

import java.io.File;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;


public class CarInfoInputFragment extends BaseFragment
        implements InputInfoView<CarInfo>
        ,View.OnFocusChangeListener
        ,View.OnClickListener
        ,DatePickerDialog.OnDateSetListener {



    @BindView(R.id.car_name_editview)
    EditText nameEditText;

    @BindView(R.id.car_idcard_editview)
    EditText idcardEditText;


    @BindView(R.id.car_pinpai_editview)
    EditText pinpaiEditText;

    @BindView(R.id.car_chexing_editview)
    EditText chexingEditText;

    @BindView(R.id.car_time_edit)
    EditText timeEditText;

    @BindView(R.id.car_number_editview)
    EditText numberEditText;


    @BindView(R.id.car_submit_button)
    Button submitButton;

    @BindView(R.id.car_pic_button)
    Button takePicButton;

    @BindView(R.id.car_pic_img)
    ImageView carInfoImageView;

    @Inject
    CarInfoInputPresenterImpl carInfoInputPresenter;

    @Inject
    @ContextLife("Activity")
    Context activityContext;

    @Inject
    Activity inputFragmentActivity;

    public final static int CONSULT_DOC_PICTURE = 3000;

    public final static int CONSULT_DOC_CAMERA = 3001;


    private static AwesomeValidation awesomeValidation;

    private Uri outputFileUri;


    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initPresenter();
        timeEditText.setInputType(InputType.TYPE_NULL);
        timeEditText.setOnFocusChangeListener(this);
        addValidation();
        submitButton.setOnClickListener(this);
        takePicButton.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_car_input;
    }

    @Override
    public void saveInfo(CarInfo carInfo) {
        carInfoInputPresenter.saveInputInfo(carInfo);
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
        mPresenter = carInfoInputPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.car_submit_button:{
                 if(awesomeValidation.validate()){
                     CarInfo carInfo = initCarInfo();
                     saveInfo(carInfo);
                 }
                 break;
             }
             case R.id.car_pic_button:{
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


    private CarInfo initCarInfo(){
        CarInfo info = new CarInfo();
        info.setNonghu(idcardEditText.getText().toString());
        info.setNonghuName(nameEditText.getText().toString());
        info.setPinpai(pinpaiEditText.getText().toString());
        info.setChexing(chexingEditText.getText().toString());
        info.setGoumairiqi(timeEditText.getText().toString());
        info.setXingchezhenghao(numberEditText.getText().toString());
        Drawable drawable = carInfoImageView.getDrawable();
        if(drawable!=null){
            Bitmap bitmap = MyUtils.drawableToBitmap(drawable);
            byte[] carPicByte = MyUtils.Bitmap2Bytes(bitmap);
            info.setCheliangzhaopian(carPicByte);
        }
        info.setInputUserId(MyUtils.getInputUserId());
        return info;
    }


    private void addValidation(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(idcardEditText, MyRegex.IDCARD,getResources().getString(R.string.validation_error_idcard));
        awesomeValidation.addValidation(nameEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(pinpaiEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(chexingEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(numberEditText, MyRegex.NOTNULL,getResources().getString(R.string.validation_error_null));
        awesomeValidation.addValidation(timeEditText, MyRegex.DATE,getResources().getString(R.string.validation_error_pattern));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONSULT_DOC_PICTURE && null != data) {
            Uri selectedImage = data.getData();
            carInfoImageView.setVisibility(View.VISIBLE);
            Glide.with(activityContext)
                    .load(selectedImage)
                    .placeholder(R.drawable.no_pic)
                    .fitCenter()
                    .into(carInfoImageView);
        }else{
            carInfoImageView.setVisibility(View.VISIBLE);
            Glide.with(activityContext)
                    .load(outputFileUri)
                    .placeholder(R.drawable.no_pic)
                    .fitCenter()
                    .into(carInfoImageView);
        }
    }

}
