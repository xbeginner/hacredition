package com.hacredition.xph.hacredition.listener;

import com.hacredition.xph.hacredition.mvp.entity.BaseAdapterItem;

import java.util.List;

/**
 * Created by pc on 2017/3/10.
 */

public interface QueryBaseItemResultCallback {

    void querySuccessfully(List<BaseAdapterItem> items);

    void queryFailly();

}
