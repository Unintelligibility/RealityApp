package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.reality.realityapp.R;
import com.reality.realityapp.UserInfoHolder;
import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.business.NewsBusiness;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.ui.adapter.RelatedNewsAdapter;
import com.reality.realityapp.ui.view.refresh.SwipeRefresh;
import com.reality.realityapp.ui.view.refresh.SwipeRefreshLayout;
import com.reality.realityapp.utils.T;

import java.util.ArrayList;
import java.util.List;

public class NewsInfoActivity extends BaseActivity {

    public static final String NEWS_ID = "news_id";
    public static final String NEWS_TYPE = "news_type";
    public static final String NEWS_TAGS = "news_tags";
    public static final String SOURCE = "source";
    public static final String CONTENT = "content";
    public static final String TITLE = "title";

    private WebView contentWv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RelatedNewsAdapter relatedNewsAdapter;

    //查看新闻详情的起止时间（毫秒）
    private long startMillis;
    private long endMillis;
    private long reading_time;
    private String news_id;
    private String source;
    private String news_type;
    private String news_tags;
    private String title;

    NewsBusiness newsBusiness = new NewsBusiness();
    private List<NewsItem> newsItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        startMillis = System.currentTimeMillis();
//        setUpToolBar();
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        initView();

        initEvent();

        initIntent(getIntent());
    }

    private void initView() {
//        contentTv = (TextView) findViewById(R.id.id_tv_content);
        contentWv = (WebView) findViewById(R.id.id_wv_content);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.id_swiperefresh);
        recyclerView = (RecyclerView)findViewById(R.id.id_recyclerview);

        WebSettings webSettings = contentWv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//
//        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        contentWv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        contentWv.addJavascriptInterface(this, "nativeMethod");

        relatedNewsAdapter = new RelatedNewsAdapter(this, newsItems);

        //swipeRefreshLayout设置
        swipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.GREEN, Color.YELLOW);

        //recyclerview设置
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(relatedNewsAdapter);
    }

    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        relatedNewsAdapter.setOnItemClickListener(new RelatedNewsAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                NewsItem newsItem = newsItems.get(position);
                String news_id = newsItem.get_id();
                String content = newsItem.getContent();
                String source = newsItem.getSource();
                String news_type = newsItem.getNews_type();
                String news_tags = newsItem.getNews_tags();
                String title = newsItem.getTitle();
                Log.d("news_id", "onClick---news_id: " + news_id);
                Log.d("content", "onClick---content: " + content);
                Log.d("source", "onClick---source: " + source);
                Log.d("news_type", "onClick-----news_type " + news_type);
                Log.d("news_tags", "onClick-----news_tags " + news_tags);
                toNewsInfoActivity(news_id, content, title, source, news_type, news_tags);
            }
        });
    }

    private void initIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        title = intent.getStringExtra(TITLE);
        source = intent.getStringExtra(SOURCE);
        news_type = intent.getStringExtra(NEWS_TYPE);
        news_tags = intent.getStringExtra(NEWS_TAGS);

        contentWv.setWebViewClient(new MyWebViewClient() {
            boolean isLoadUrl = false;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                T.showToast("rankInfo1");
                sendReadTime();
                toRankActivity();
                T.showToast("rankInfo2");
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (!isLoadUrl) {
                    isLoadUrl = true;
                    view.loadUrl(url);

                }
                super.onPageStarted(view, url, favicon);
            }
        });

        String content = intent.getStringExtra(CONTENT);
//        Log.d("html-content", "html-content: " + content);
//        content = "<img src='http://p0.ifengimg.com/pmop/2018/0219/ED77C409CF7D10AD0F61B41D125301C247A13D5B_size38_w532_h356.jpeg'/>";
        if (TextUtils.isEmpty(content)) {
            return;
        }
//        CharSequence htmlContent;
//        //Html.FROM_HTML_MODE_COMPACT表示html块元素之间使用一个换行符分隔
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            htmlContent = Html.fromHtml(content,Html.FROM_HTML_MODE_COMPACT);
//
//        }else {
//            htmlContent = Html.fromHtml(content);
//        }
//        contentTv.setText(htmlContent);
        contentWv.loadData(content, "text/html;charset=utf-8", "UTF-8");
//        Log.d("content", "content--: " + content);

        news_id = intent.getStringExtra(NEWS_ID);
        Log.d("news_id", "news_id-------: " + news_id);
        newsBusiness.relatedNewsDisplay(news_id, new CommonCallback<List<NewsItem>>() {
            @Override
            public void onError(Exception e) {
                Log.d("error-----", "error------------ " + e.getMessage());
                T.showToast(e.getMessage());
            }

            @Override
            public void onResponse(List<NewsItem> response) {
                newsItems.clear();
                newsItems.addAll(response);
                Log.d("relatedNews", "relatedNews-list: " + newsItems.size());
                relatedNewsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        sendReadTime();
    }

    private void sendReadTime() {
        endMillis = System.currentTimeMillis();
        reading_time = endMillis - startMillis;
        Log.d("readingTime", "reading_time: " + reading_time);
        String username = UserInfoHolder.getInstance().getUser().getUsername();
        String password = UserInfoHolder.getInstance().getUser().getPassword();
        newsBusiness.sendReadInfo2(username, password, title, source, news_type, news_tags, reading_time);
    }

    //    void setChromeClient() {
//        contentWv.setWebChromeClient(new WebChromeClient() {
//
//            @Override
//            public Bitmap getDefaultVideoPoster() {
//                if (this == null) {
//                    return null;
//                }
//
//                //这个地方是加载h5的视频列表 默认图   点击前的视频图
//                return BitmapFactory.decodeResource(getApplicationContext().getResources(),
//                        R.mipmap.ic_launcher);
//            }
//
//        });
//    }

    @JavascriptInterface
    private void toRankActivity() {
        Intent intent = new Intent(this, NewsInfoActivity.class);
        intent.putExtra("source", source);
        startActivity(intent);
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

    //设置webview代理加载图片
    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();
            aReset();
        }

        private void imgReset() {
            contentWv.loadUrl("javascript:(function(){" +
                    "var objs = document.getElementsByTagName('img'); " +
                    "for(var i=0;i<objs.length;i++)  " +
                    "{"
                    + "var img = objs[i];   " +
                    " img.style.maxWidth = '100%';img.style.height='auto';" +
                    "if(img.className == 'auther-logo')" +
                    "{" +
                    "img.style.display='none';" +
                    "}" +
                    "}" +
                    "})()");
        }

        private void aReset() {
            contentWv.loadUrl("javascript:(function(){" +
                    "var as = document.getElementsByTagName('a');" +
                    "for(var j=0;j<as.length;j++)  " +
                    "{" +
                    "var a = as[j];   " +
                    "a.href='javascript:void(0);" +
                    "a.onclick=function(e){return false;};" +
                    "}" +
                    "})()");
        }

    }
}
