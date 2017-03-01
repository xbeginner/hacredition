package com.hacredition.xph.hacredition.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.provider.SyncStateContract;

import com.hacredition.xph.hacredition.App;
import com.hacredition.xph.hacredition.common.Constants;

import org.reactivestreams.Subscription;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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


    public static void cancelSubscription(rx.Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


    /**
     * MD5摘要获取
     * @param val
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5(String val)  {
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            byte[] m = md5.digest();//加密
            return getString(m);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    private static String getString(byte[] b){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < b.length; i ++){
            sb.append(b[i]);
        }
        return sb.toString();
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);


        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }



    public static byte[] Bitmap2Bytes(Bitmap bm) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                return baos.toByteArray();
    }

}
