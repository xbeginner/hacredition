package com.hacredition.xph.hacredition.mvp.presenter;

import com.hacredition.xph.hacredition.mvp.presenter.base.BasePresenter;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.Collection;

/**
 * Created by xikai on 2017/2/3.
 */

public interface NewsDetailPresenter extends BasePresenter{

       void showNewsDetail(int newsId);

}
