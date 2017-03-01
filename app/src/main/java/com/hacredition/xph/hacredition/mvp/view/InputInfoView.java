package com.hacredition.xph.hacredition.mvp.view;

import android.widget.Button;

import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

/**
 * Created by pc on 2017/2/15.
 */

public interface InputInfoView<T> extends BaseView {

    void saveInfo(T t);

    void saveSuccessfully();

    void saveFailly();


}
