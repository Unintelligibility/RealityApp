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
    private int fake;
    private int clickbait;
    private String picture_info;
    private String news_type;
    private String news_tags;

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

    public int getFake() {
        return fake;
    }

    public void setFake(int fake) {
        this.fake = fake;
    }

    public int getClickbait() {
        return clickbait;
    }

    public void setClickbait(int clickbait) {
        this.clickbait = clickbait;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public String getNews_tags() {
        return news_tags;
    }

    public void setNews_tags(String news_tags) {
        this.news_tags = news_tags;
    }
}
