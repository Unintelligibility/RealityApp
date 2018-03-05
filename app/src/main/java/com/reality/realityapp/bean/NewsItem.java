package com.reality.realityapp.bean;

import java.io.Serializable;

/**
 * Created by 铠联 on 2018/1/30.
 */

public class NewsItem implements Serializable{
    private long id;
    private String picUrl;
    private String picInfo;
    private String title;
    private String content;
    private String source;
    private String time;
    private String reliability;
    private String isBadTitle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(String picInfo) {
        this.picInfo = picInfo;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getIsBadTitle() {
        return isBadTitle;
    }

    public void setIsBadTitle(String isBadTitle) {
        this.isBadTitle = isBadTitle;
    }
}
