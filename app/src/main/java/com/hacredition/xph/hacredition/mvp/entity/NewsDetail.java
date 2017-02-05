package com.hacredition.xph.hacredition.mvp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xikai on 2017/2/3.
 */

@Entity
public class NewsDetail {

    private int newsId;

    private String  title;

    private String content;

    private String time;

    private boolean hasImg;

    private String imgSrc;

    private int readTime;

    @Generated(hash = 2146210160)
    public NewsDetail(int newsId, String title, String content, String time,
            boolean hasImg, String imgSrc, int readTime) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.time = time;
        this.hasImg = hasImg;
        this.imgSrc = imgSrc;
        this.readTime = readTime;
    }

    @Generated(hash = 1491886114)
    public NewsDetail() {
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isHasImg() {
        return hasImg;
    }

    public void setHasImg(boolean hasImg) {
        this.hasImg = hasImg;
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

    public boolean getHasImg() {
        return this.hasImg;
    }
}
