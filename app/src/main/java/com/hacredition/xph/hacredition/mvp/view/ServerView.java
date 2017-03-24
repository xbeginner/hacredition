package com.hacredition.xph.hacredition.mvp.view;

import com.hacredition.xph.hacredition.mvp.entity.ServerItem;
import com.hacredition.xph.hacredition.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by pc on 2017/3/24.
 */

public interface ServerView extends BaseView{

    void showServers(List<ServerItem> list);

}
