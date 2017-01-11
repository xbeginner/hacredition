package com.hacredition.xph.hacredition.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.common.Constants;

/**
 * Created by pc on 2017/1/9.
 */

public class MyUtils {

    public static boolean isNightMode() {
        SharedPreferences preferences = App.getAppContext().getSharedPreferences(
                Constants.HA_CREDITION_SETTINGS, Activity.MODE_PRIVATE);
        return preferences.getBoolean(Constants.NIGHT_THEME_MODE, false);
    }

    public static SharedPreferences getSharedPreferences() {
        return App.getAppContext()
                .getSharedPreferences(Constants.HA_CREDITION_SETTINGS, Context.MODE_PRIVATE);
    }

}
