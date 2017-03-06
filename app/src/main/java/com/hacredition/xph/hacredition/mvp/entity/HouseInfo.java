package com.hacredition.xph.hacredition.mvp.entity;

import java.util.Arrays;

/**
 * Created by pc on 2017/2/16.
 */

public class HouseInfo {

    private String nonghuIdcard;
    private byte[] fangwutupiao;
    private String shifoudiya;
    private float dangqianguzhi;
    private String suozaidi;
    private float jianzhumianji;
    private String goujianriqi;
    private String fangwuxingzhi;
    private int inputUserId;


    public String getNonghuId() {
        return nonghuIdcard;
    }

    public void setNonghuIdcard(String nonghuIdcard) {
        this.nonghuIdcard = nonghuIdcard;
    }

    public byte[] getFangwutupiao() {
        return fangwutupiao;
    }

    public void setFangwutupiao(byte[] fangwutupiao) {
        this.fangwutupiao = fangwutupiao;
    }

    public String getShifoudiya() {
        return shifoudiya;
    }

    public void setShifoudiya(String shifoudiya) {
        this.shifoudiya = shifoudiya;
    }

    public float getDangqianguzhi() {
        return dangqianguzhi;
    }

    public void setDangqianguzhi(float dangqianguzhi) {
        this.dangqianguzhi = dangqianguzhi;
    }

    public String getSuozaidi() {
        return suozaidi;
    }

    public void setSuozaidi(String suozaidi) {
        this.suozaidi = suozaidi;
    }

    public float getJianzhumianji() {
        return jianzhumianji;
    }

    public void setJianzhumianji(float jianzhumianji) {
        this.jianzhumianji = jianzhumianji;
    }

    public String getGoujianriqi() {
        return goujianriqi;
    }

    public void setGoujianriqi(String goujianriqi) {
        this.goujianriqi = goujianriqi;
    }

    public String getFangwuxingzhi() {
        return fangwuxingzhi;
    }

    public void setFangwuxingzhi(String fangwuxingzhi) {
        this.fangwuxingzhi = fangwuxingzhi;
    }



    public int getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(int inputUserId) {
        this.inputUserId = inputUserId;
    }

    @Override
    public String toString() {
        return "HouseInfo{" +
                "nonghuIdcard='" + nonghuIdcard + '\'' +
                ", fangwutupiao=" + Arrays.toString(fangwutupiao) +
                ", shifoudiya='" + shifoudiya + '\'' +
                ", dangqianguzhi=" + dangqianguzhi +
                ", suozaidi='" + suozaidi + '\'' +
                ", jianzhumianji=" + jianzhumianji +
                ", goujianriqi='" + goujianriqi + '\'' +
                ", fangwuxingzhi='" + fangwuxingzhi + '\'' +
                '}';
    }
}
