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

    String inputItemFragmentName;

    String inputItemSrc;

    String inputItemTitle;







    @Generated(hash = 31962579)
    public InputItem(int inputItemId, String inputItemInfo,
            String inputItemFragmentName, String inputItemSrc,
            String inputItemTitle) {
        this.inputItemId = inputItemId;
        this.inputItemInfo = inputItemInfo;
        this.inputItemFragmentName = inputItemFragmentName;
        this.inputItemSrc = inputItemSrc;
        this.inputItemTitle = inputItemTitle;
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

    public String getInputItemFragmentName() {
        return inputItemFragmentName;
    }

    public void setInputItemFragmentName(String inputItemActivity) {
        this.inputItemFragmentName = inputItemActivity;
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


}
