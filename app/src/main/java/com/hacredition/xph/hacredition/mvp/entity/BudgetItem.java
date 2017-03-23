package com.hacredition.xph.hacredition.mvp.entity;

import java.util.Date;

/**
 * Created by pc on 2017/3/21.
 */

public class BudgetItem implements Comparable<BudgetItem>{

    float sum;

    String type;

    int log;

    Date time;

    String info;

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public int compareTo(BudgetItem budgetItem) {
        return this.getTime().compareTo(budgetItem.getTime());
    }
}
