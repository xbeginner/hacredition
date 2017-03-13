package com.hacredition.xph.hacredition.mvp.entity;

import java.util.List;

/**
 * 农户得分
 * Created by pc on 2017/3/13.
 */

public class HouseHoldGradeInfo {

    private String name;

    private String idcard;

    private String grade;

    private String lastTime;

    private String sumGrade;

    private String delGrade;

    List<SumGradeDetailInfo> sumGradeDetailInfoList;

    List<DelGradeDetailInfo> delGradeDetailInfoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getSumGrade() {
        return sumGrade;
    }

    public void setSumGrade(String sumGrade) {
        this.sumGrade = sumGrade;
    }

    public String getDelGrade() {
        return delGrade;
    }

    public void setDelGrade(String delGrade) {
        this.delGrade = delGrade;
    }

    public List<SumGradeDetailInfo> getSumGradeDetailInfoList() {
        return sumGradeDetailInfoList;
    }

    public void setSumGradeDetailInfoList(List<SumGradeDetailInfo> sumGradeDetailInfoList) {
        this.sumGradeDetailInfoList = sumGradeDetailInfoList;
    }

    public List<DelGradeDetailInfo> getDelGradeDetailInfoList() {
        return delGradeDetailInfoList;
    }

    public void setDelGradeDetailInfoList(List<DelGradeDetailInfo> delGradeDetailInfoList) {
        this.delGradeDetailInfoList = delGradeDetailInfoList;
    }
}
