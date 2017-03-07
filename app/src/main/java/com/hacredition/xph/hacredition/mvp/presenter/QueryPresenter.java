package com.hacredition.xph.hacredition.mvp.presenter;

import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;

/**
 * Created by xikai on 2017/2/9.
 */

public interface QueryPresenter extends BasePresenter {

    void setQueryItems(UserInfo userInfo);

}
