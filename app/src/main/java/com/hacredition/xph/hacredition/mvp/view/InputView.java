package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.mvp.entity.InputItem;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by pc on 2017/2/7.
 */

public interface InputView extends BaseView {

    void showInputItems();

    void setInputItem(List<InputItem> inputItems);

}
