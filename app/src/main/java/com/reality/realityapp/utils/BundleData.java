package com.reality.realityapp.utils;

import com.reality.realityapp.bean.NewsItem;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by 铠联 on 2018/3/6.
 * 因为bundle无法传递Map但可以传递单个对象，所以建立此类来包装map
 */

public class BundleData implements Serializable{
    private Map<String,NewsItem> newsItems;

    public Map<String,NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(Map<String,NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
