package com.reality.realityapp.mock;

import com.reality.realityapp.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 铠联 on 2018/1/27.
 */

public class NewsListMock {
    public static Map<String, NewsItem> getNewItemList1(){
        NewsItem item1 = new NewsItem();
        NewsItem item2 = new NewsItem();
        NewsItem item3 = new NewsItem();
        item1.setTitle("A");
        item2.setTitle("B");
        item3.setTitle("C");
        Map<String, NewsItem> newsItems =new TreeMap<>();
        newsItems.put("0",item1);
        newsItems.put("1",item2);
        newsItems.put("2",item3);
        return newsItems;
    }

    public static Map<String, NewsItem> getNewItemList2(){
        NewsItem item1 = new NewsItem();
        NewsItem item2 = new NewsItem();
        NewsItem item3 = new NewsItem();
        item1.setTitle("B");
        item2.setTitle("B");
        item3.setTitle("C");
        Map<String, NewsItem> newsItems =new TreeMap<>();
        newsItems.put("0",item1);
        newsItems.put("1",item2);
        newsItems.put("2",item3);
        return newsItems;
    }

    public static List<NewsItem> getNewItemList3(){
        NewsItem item1 = new NewsItem();
        NewsItem item2 = new NewsItem();
        NewsItem item3 = new NewsItem();
        item1.setTitle("C");
        item2.setTitle("B");
        item3.setTitle("C");
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(item1);
        newsItems.add(item2);
        newsItems.add(item3);
        return newsItems;
    }
}
