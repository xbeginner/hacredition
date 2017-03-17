package com.hacredition.xph.hacredition.mvp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by pc on 2017/3/16.
 */

@Entity
public class IncomeInfo {

    Float incomeSum;

    String incomeTime;

    String incomeType;

    String incomeInfo;

    @Generated(hash = 1912592890)
    public IncomeInfo(Float incomeSum, String incomeTime, String incomeType,
            String incomeInfo) {
        this.incomeSum = incomeSum;
        this.incomeTime = incomeTime;
        this.incomeType = incomeType;
        this.incomeInfo = incomeInfo;
    }

    @Generated(hash = 2036155967)
    public IncomeInfo() {
    }

    public Float getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(Float incomeSum) {
        this.incomeSum = incomeSum;
    }

    public String getIncomeTime() {
        return incomeTime;
    }

    public void setIncomeTime(String incomeTime) {
        this.incomeTime = incomeTime;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getIncomeInfo() {
        return incomeInfo;
    }

    public void setIncomeInfo(String incomeInfo) {
        this.incomeInfo = incomeInfo;
    }
}
