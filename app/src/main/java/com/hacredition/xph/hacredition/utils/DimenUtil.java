package com.hacredition.xph.hacredition.utils;

import com.hacredition.xph.hacredition.App;

/**
 * Created by pc on 2017/1/18.
 */

public class DimenUtil {
    public static int dp2px(float dp) {
        final float scale = App.getAppContext().getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }

    public static float sp2px(float sp) {
        final float scale = App.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static int getScreenSize() {
        return App.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }
}
