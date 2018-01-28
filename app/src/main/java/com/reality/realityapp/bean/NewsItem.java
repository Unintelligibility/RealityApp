package com.reality.realityapp.bean;

/**
 * Created by 铠联 on 2018/1/27.
 */

public class NewsItem {
    private long id;
    private String title;
    private String office;
    private String time;
    private String reliability;
    private String isBadTitle;
    private String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
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

    public String isBadTitle() {
        return isBadTitle;
    }

    public void setBadTitle(String badTitle) {
        isBadTitle = badTitle;
    }
}
