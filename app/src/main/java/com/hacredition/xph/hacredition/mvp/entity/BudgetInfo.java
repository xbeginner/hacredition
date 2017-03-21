package com.hacredition.xph.hacredition.mvp.entity;

import java.util.List;

/**
 * Created by pc on 2017/3/21.
 */

public class BudgetInfo {

    float sum;

    float incomeSum;

    float outputSum;

    List<BudgetItem> budgetItemList;

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public float getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(float incomeSum) {
        this.incomeSum = incomeSum;
    }

    public float getOutputSum() {
        return outputSum;
    }

    public void setOutputSum(float outputSum) {
        this.outputSum = outputSum;
    }

    public List<BudgetItem> getBudgetItemList() {
        return budgetItemList;
    }

    public void setBudgetItemList(List<BudgetItem> budgetItemList) {
        this.budgetItemList = budgetItemList;
    }
}
