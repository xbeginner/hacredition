package com.hacredition.xph.hacredition.mvp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by pc on 2017/2/10.
 */


public class QueryItem {

    int queryItemId;

    String queryItemInfo;

    String queryItemFragmentName;

    String queryItemSrc;

    String queryItemTitle;

    public int getQueryItemId() {
        return queryItemId;
    }

    public void setQueryItemId(int queryItemId) {
        this.queryItemId = queryItemId;
    }

    public String getQueryItemInfo() {
        return queryItemInfo;
    }

    public void setQueryItemInfo(String queryItemInfo) {
        this.queryItemInfo = queryItemInfo;
    }

    public String getQueryItemFragmentName() {
        return queryItemFragmentName;
    }

    public void setQueryItemFragmentName(String queryItemFragmentName) {
        this.queryItemFragmentName = queryItemFragmentName;
    }

    public String getQueryItemSrc() {
        return queryItemSrc;
    }

    public void setQueryItemSrc(String queryItemSrc) {
        this.queryItemSrc = queryItemSrc;
    }

    public String getQueryItemTitle() {
        return queryItemTitle;
    }

    public void setQueryItemTitle(String queryItemTitle) {
        this.queryItemTitle = queryItemTitle;
    }
}
