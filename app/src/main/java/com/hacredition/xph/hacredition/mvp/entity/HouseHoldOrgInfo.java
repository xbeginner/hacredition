package com.hacredition.xph.hacredition.mvp.entity;

import java.util.List;

/**
 * Created by pc on 2017/3/16.
 */

public class HouseHoldOrgInfo {

    String orgName;

    float value;

    String info;

    List<HouseHoldOrgItem> items;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<HouseHoldOrgItem> getItems() {
        return items;
    }

    public void setItems(List<HouseHoldOrgItem> items) {
        this.items = items;
    }
}
