package com.hacredition.xph.hacredition.mvp.entity;

/**
 * Created by pc on 2017/1/16.
 */

public class NewsSummary {

    //对应id
    private int newsId;

    //是否有图片
    private boolean hasImg;

    //是否有副标题
    private boolean hasSubTitle;

    //题目
    private String title;

    //副标题
    private String subTitle;

    //发布时间
    private String time;

    //图片链接
    private String imgSrc;

    //阅读量
    private int readTime;

    //内容简述
    private String summaryContent;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public boolean isHasImg() {
        return hasImg;
    }

    public void setHasImg(boolean hasImg) {
        this.hasImg = hasImg;
    }

    public boolean isHasSubTitle() {
        return hasSubTitle;
    }

    public void setHasSubTitle(boolean hasSubTitle) {
        this.hasSubTitle = hasSubTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public String getSummaryContent() {
        return summaryContent;
    }

    public void setSummaryContent(String summaryContent) {
        this.summaryContent = summaryContent;
    }
}
