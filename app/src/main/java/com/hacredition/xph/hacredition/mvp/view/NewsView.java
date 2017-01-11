package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by pc on 2017/1/11.
 */

public interface NewsView extends BaseView{
    void initViewPager(List<String> newsTitles);
}
