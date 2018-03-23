package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reality.realityapp.R;
import com.reality.realityapp.UserInfoHolder;
import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.business.NewsBusiness;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.ui.adapter.NewsListAdapter;
import com.reality.realityapp.ui.view.fragment.NewsListFragment;
import com.reality.realityapp.ui.view.refresh.SwipeRefresh;
import com.reality.realityapp.ui.view.refresh.SwipeRefreshLayout;
import com.reality.realityapp.utils.T;

import java.util.HashMap;
import java.util.Map;

public class ThemeNewsListActivity extends BaseActivity {

    public static final String THEME_TITLE = "theme_title";
    private Map<String, NewsItem> newsItems = new HashMap<>();

    private NewsBusiness newsBusiness = new NewsBusiness();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private NewsListAdapter newsListAdapter;

    private String theme_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_news_list);

        setUpToolBar();

        initView();

        initEvent();

        initIntent(getIntent());

        refreshNews();
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_swiperefresh);
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

//        newsItemList = NewsListMock.getNewItemList2();
        newsListAdapter = new NewsListAdapter(this, newsItems);


        //swipeRefreshLayout设置
        swipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.GREEN, Color.YELLOW);

        //recyclerview设置
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsListAdapter);

    }

    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNews();
            }
        });

        swipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                return;
            }
        });

        newsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                NewsItem newsItem = newsItems.get(String.valueOf(position));
                String news_id = newsItem.get_id();
                String content = newsItem.getContent();
                String source = newsItem.getSource();
                String news_type = newsItem.getNews_type();
                String news_tags = newsItem.getNews_tags();
                String title = newsItem.getTitle();
//                Log.d("news_id", "onClick---news_id: " + news_id);
//                Log.d("content", "onClick---content: " + content);
//                Log.d("source", "onClick---source: " + source);
//                Log.d("news_type", "onClick-----news_type " + news_type);
//                Log.d("news_tags", "onClick-----news_tags " + news_tags);
                toNewsInfoActivity(news_id, content, title, source, news_type, news_tags);
            }
        });

    }

    private void initIntent(Intent intent) {
        theme_title = intent.getStringExtra(THEME_TITLE);
    }

    /**
     * 刷新新闻列表
     */
    private void refreshNews() {
        final String TAG = "NewsBusiness-request";
//        T.showToast("size:"+recyclerView.getLayoutManager().getItemCount());
//        Log.d(TAG, "userid1-----: " + userid);
        Log.d("theme_title", "onClick---theme_title: " + theme_title);
        newsBusiness.themeNewsListDisplay(theme_title, new CommonCallback<Map<String, NewsItem>>() {
            @Override
            public void onError(Exception e) {
                T.showToast(e.getMessage());
            }

            @Override
            public void onResponse(Map<String, NewsItem> response) {
                newsItems.clear();
                newsItems.putAll(response);
//                Log.d(TAG, "map: " + newsItems.size());
                newsListAdapter.notifyDataSetChanged();
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void toNewsInfoActivity(String news_id, String content, String title, String source, String news_type, String news_tags) {
        Intent intent = new Intent(this, NewsInfoActivity.class);
        intent.putExtra("news_id", news_id);
        intent.putExtra("content", content);
        intent.putExtra("title", title);
        intent.putExtra("source", source);
        intent.putExtra("news_type", news_type);
        intent.putExtra("news_tags", news_tags);
        startActivity(intent);
    }
}
