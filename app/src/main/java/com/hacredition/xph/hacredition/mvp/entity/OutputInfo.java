package com.hacredition.xph.hacredition.mvp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by pc on 2017/3/17.
 */

@Entity
public class OutputInfo {

    Float outputSum;

    String outputTime;

    String outputType;

    String outputInfo;

    String spendType;


    @Generated(hash = 2021927583)
    public OutputInfo(Float outputSum, String outputTime, String outputType,
            String outputInfo, String spendType) {
        this.outputSum = outputSum;
        this.outputTime = outputTime;
        this.outputType = outputType;
        this.outputInfo = outputInfo;
        this.spendType = spendType;
    }

    @Generated(hash = 2028281914)
    public OutputInfo() {
    }


    public Float getOutputSum() {
        return outputSum;
    }

    public void setOutputSum(Float outputSum) {
        this.outputSum = outputSum;
    }

    public String getOutputTime() {
        return outputTime;
    }

    public void setOutputTime(String outputTime) {
        this.outputTime = outputTime;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getOutputInfo() {
        return outputInfo;
    }

    public void setOutputInfo(String outputInfo) {
        this.outputInfo = outputInfo;
    }

    public String getSpendType() {
        return spendType;
    }

    public void setSpendType(String spendType) {
        this.spendType = spendType;
    }
}
