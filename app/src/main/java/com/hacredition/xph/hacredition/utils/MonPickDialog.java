package com.hacredition.xph.hacredition.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

/**
 * Created by pc on 2017/3/23.
 */

public class MonPickDialog extends DatePickerDialog {
    public MonPickDialog(Context context,int themeRes,OnDateSetListener listener,int year,int month,int day) {
        super(context,themeRes,listener,year,month,day);
        this.setTitle(year + "年" + (month + 1) + "月");
        ((ViewGroup) ((ViewGroup) this.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(1).setVisibility(View.GONE);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
        this.setTitle(year + "年" + (month + 1) + "月");
    }

}