package com.hacredition.xph.hacredition.mvp.presenter;

import com.hacredition.xph.hacredition.mvp.entity.UserInfo;
import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;

import java.util.List;

/**
 * Created by xikai on 2017/2/9.
 */

public interface InputPresenter extends BasePresenter {



    void setInputItems(UserInfo userInfo);

}
