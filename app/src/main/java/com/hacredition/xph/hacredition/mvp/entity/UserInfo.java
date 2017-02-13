package com.hacredition.xph.hacredition.mvp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2017/2/7.
 */

@Entity
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;

    private String userName;

    private int orgId;

    private String orgName;

    private String tel;

    private String imgSrc;


    private Date lastLoginTime;

    @Generated(hash = 2034020735)
    public UserInfo(int userId, String userName, int orgId, String orgName,
            String tel, String imgSrc, Date lastLoginTime) {
        this.userId = userId;
        this.userName = userName;
        this.orgId = orgId;
        this.orgName = orgName;
        this.tel = tel;
        this.imgSrc = imgSrc;
        this.lastLoginTime = lastLoginTime;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

}
