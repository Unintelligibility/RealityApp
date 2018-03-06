package com.reality.realityapp.bean;

import java.io.Serializable;

/**
 * Created by 铠联 on 2018/1/30.
 */

public class NewsItem implements Serializable{
    private String _id;
    private String picture;
    private String title;
    private String content;
    private String source;
    private String time;
    private String reliability;
    private String isBadTitle;
    private String picture_info;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getPicture_info() {
        return picture_info;
    }

    public void setPicture_info(String picture_info) {
        this.picture_info = picture_info;
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
