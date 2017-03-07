package com.hacredition.xph.hacredition.mvp.entity;

/**
 * Created by pc on 2017/3/7.
 */

public class CourtInfo {

    private String nonghu;
    private String nonghuName;
    private String type;
    private String shiyou;
    private String time;
    private int inputUserId;


    public String getNonghu() {
        return nonghu;
    }

    public void setNonghu(String nonghu) {
        this.nonghu = nonghu;
    }

    public String getNonghuName() {
        return nonghuName;
    }

    public void setNonghuName(String nonghuName) {
        this.nonghuName = nonghuName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShiyou() {
        return shiyou;
    }

    public void setShiyou(String shiyou) {
        this.shiyou = shiyou;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(int inputUserId) {
        this.inputUserId = inputUserId;
    }
}
