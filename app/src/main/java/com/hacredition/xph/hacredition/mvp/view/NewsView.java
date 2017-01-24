package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.common.LoadNewsType;
import com.hacredition.xph.hacredition.mvp.entity.NewsSummary;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by pc on 2017/1/11.
 */

public interface NewsView extends BaseView{
    /**
     * 设置List
     * @param newsSummary
     * @param loadType  对结果进行设置
     */
    void setNewsList(List<NewsSummary> newsSummary, @LoadNewsType.checker int loadType);
}
