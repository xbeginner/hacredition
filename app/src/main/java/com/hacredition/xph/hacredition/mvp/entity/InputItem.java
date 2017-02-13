package com.hacredition.xph.hacredition.mvp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by pc on 2017/2/10.
 */

@Entity
public class InputItem {

    int inputItemId;

    String inputItemInfo;

    String inputItemActivity;

    String inputItemSrc;

    String inputItemTitle;

    String activityName;

    @Generated(hash = 503029745)
    public InputItem(int inputItemId, String inputItemInfo,
            String inputItemActivity, String inputItemSrc, String inputItemTitle,
            String activityName) {
        this.inputItemId = inputItemId;
        this.inputItemInfo = inputItemInfo;
        this.inputItemActivity = inputItemActivity;
        this.inputItemSrc = inputItemSrc;
        this.inputItemTitle = inputItemTitle;
        this.activityName = activityName;
    }

    @Generated(hash = 195613999)
    public InputItem() {
    }

    public int getInputItemId() {
        return inputItemId;
    }

    public void setInputItemId(int inputItemId) {
        this.inputItemId = inputItemId;
    }

    public String getInputItemInfo() {
        return inputItemInfo;
    }

    public void setInputItemInfo(String inputItemInfo) {
        this.inputItemInfo = inputItemInfo;
    }

    public String getInputItemActivity() {
        return inputItemActivity;
    }

    public void setInputItemActivity(String inputItemActivity) {
        this.inputItemActivity = inputItemActivity;
    }

    public String getInputItemSrc() {
        return inputItemSrc;
    }

    public void setInputItemSrc(String inputItemSrc) {
        this.inputItemSrc = inputItemSrc;
    }

    public String getInputItemTitle() {
        return inputItemTitle;
    }

    public void setInputItemTitle(String inputItemTitle) {
        this.inputItemTitle = inputItemTitle;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
