package com.hacredition.xph.hacredition.mvp.entity;

import org.greenrobot.greendao.annotation.Entity;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by pc on 2017/3/8.
 */


@Entity
public class HouseHoldBasicQueryHistory {

    private String sfzno;

    private String xingming;

    private Date queryTime;

    private int queryUserId;

    @Generated(hash = 1818303454)
    public HouseHoldBasicQueryHistory(String sfzno, String xingming, Date queryTime,
            int queryUserId) {
        this.sfzno = sfzno;
        this.xingming = xingming;
        this.queryTime = queryTime;
        this.queryUserId = queryUserId;
    }

    @Generated(hash = 243773407)
    public HouseHoldBasicQueryHistory() {
    }

    public String getSfzno() {
        return sfzno;
    }

    public void setSfzno(String sfzno) {
        this.sfzno = sfzno;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    public int getQueryUserId() {
        return queryUserId;
    }

    public void setQueryUserId(int queryUserId) {
        this.queryUserId = queryUserId;
    }
}
