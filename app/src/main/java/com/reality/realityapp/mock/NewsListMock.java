package com.reality.realityapp.mock;

import com.reality.realityapp.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 铠联 on 2018/1/27.
 */

public class NewsListMock {
    public static List<NewsItem> getNewItemList1(){
        NewsItem item1 = new NewsItem();
        NewsItem item2 = new NewsItem();
        NewsItem item3 = new NewsItem();
        item1.setTitle("A");
        item2.setTitle("B");
        item3.setTitle("C");
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(item1);
        newsItems.add(item2);
        newsItems.add(item3);
        return newsItems;
    }

    public static List<NewsItem> getNewItemList2(){
        NewsItem item1 = new NewsItem();
        NewsItem item2 = new NewsItem();
        NewsItem item3 = new NewsItem();
        item1.setTitle("B");
        item2.setTitle("B");
        item3.setTitle("C");
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(item1);
        newsItems.add(item2);
        newsItems.add(item3);
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
