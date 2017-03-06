package com.hacredition.xph.hacredition.mvp.entity;

/**
 * Created by pc on 2017/3/6.
 */

public class GuaranteeInfo {

    //担保人证件号和姓名
    private String guaranteeUserIdcard;
    private String guaranteeUserName;

    //被担保人证件号和姓名
    private String guarantedUserIdcard;
    private String guarantedUserName;

    private Float danbaojine;
    private Float weijieqingjine;


    private String fafangriqi;
    private String daoqiriqi;
    private String yuqi;
    private String xingchengbuliang;
    private String daichang;

    private int inputUserId;

    public String getGuaranteeUserIdcard() {
        return guaranteeUserIdcard;
    }

    public void setGuaranteeUserIdcard(String guaranteeUserIdcard) {
        this.guaranteeUserIdcard = guaranteeUserIdcard;
    }

    public String getGuaranteeUserName() {
        return guaranteeUserName;
    }

    public void setGuaranteeUserName(String guaranteeUserName) {
        this.guaranteeUserName = guaranteeUserName;
    }

    public String getGuarantedUserIdcard() {
        return guarantedUserIdcard;
    }

    public void setGuarantedUserIdcard(String guarantedUserIdcard) {
        this.guarantedUserIdcard = guarantedUserIdcard;
    }

    public String getGuarantedUserName() {
        return guarantedUserName;
    }

    public void setGuarantedUserName(String guarantedUserName) {
        this.guarantedUserName = guarantedUserName;
    }

    public Float getDanbaojine() {
        return danbaojine;
    }

    public void setDanbaojine(Float danbaojine) {
        this.danbaojine = danbaojine;
    }

    public Float getWeijieqingjine() {
        return weijieqingjine;
    }

    public void setWeijieqingjine(Float weijieqingjine) {
        this.weijieqingjine = weijieqingjine;
    }

    public String getFafangriqi() {
        return fafangriqi;
    }

    public void setFafangriqi(String fafangriqi) {
        this.fafangriqi = fafangriqi;
    }

    public String getDaoqiriqi() {
        return daoqiriqi;
    }

    public void setDaoqiriqi(String daoqiriqi) {
        this.daoqiriqi = daoqiriqi;
    }

    public String getYuqi() {
        return yuqi;
    }

    public void setYuqi(String yuqi) {
        this.yuqi = yuqi;
    }

    public String getXingchengbuliang() {
        return xingchengbuliang;
    }

    public void setXingchengbuliang(String xingchengbuliang) {
        this.xingchengbuliang = xingchengbuliang;
    }

    public String getDaichang() {
        return daichang;
    }

    public void setDaichang(String daichang) {
        this.daichang = daichang;
    }

    public int getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(int inputUserId) {
        this.inputUserId = inputUserId;
    }
}
