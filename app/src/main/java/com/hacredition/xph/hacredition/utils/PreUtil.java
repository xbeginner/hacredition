package com.hacredition.xph.hacredition.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hacredition.xph.hacredition.App;

/**
 * Created by pc on 2017/6/15.
 */

public class PreUtil {

    private static final String PRE_NAME = "day_or_night_preferences";
    private static final String PRE_NIGHT = "night";

    private static SharedPreferences getSharedPreferences() {
        return App.getAppContext()
                .getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
    }

    public static void setNight(){
        getSharedPreferences().edit().putBoolean(PRE_NIGHT, true).commit();
    }

    public static void setDay(){
        getSharedPreferences().edit().putBoolean(PRE_NIGHT, false).commit();
    }

    public static void changeDayNight(){
        boolean change = !getSharedPreferences().getBoolean(PRE_NIGHT, false);
        getSharedPreferences().edit().putBoolean(PRE_NIGHT, change).commit();
    }

    public static boolean isNight(){
        return getSharedPreferences().getBoolean(PRE_NIGHT, false);
    }

    public static int getThemeRes(){
        if (!isNight()) {
            return Constant.RESOURCES_DAYTHEME;
        } else {
            return Constant.RESOURCES_NIGHTTHEME;
        }
    }
}
