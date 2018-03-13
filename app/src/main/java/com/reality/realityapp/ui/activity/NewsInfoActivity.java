package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.print.PageRange;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.bean.Token;
import com.reality.realityapp.business.NewsBusiness;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.utils.T;

import java.io.InputStream;

public class NewsInfoActivity extends BaseActivity {

    public static final String NEWS_ID = "news_id";
    private WebView contentWv;

    public static final String SOURCE = "source";
    public static final String CONTENT = "content";

    //查看新闻详情的起止时间（毫秒）
    private long startMillis;
    private long endMillis;
    private long reading_time;
    private String news_id;

    NewsBusiness newsBusiness = new NewsBusiness();

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
//        // Use WideViewport and Zoom out if there is no viewport defined
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//
//        // Enable pinch to zoom without the zoom buttons
//        webSettings.setBuiltInZoomControls(false);
//
//        // Allow use of Local Storage
//        webSettings.setDomStorageEnabled(true);
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
//            // Hide the zoom controls for HONEYCOMB+
//            webSettings.setDisplayZoomControls(false);
//        }
//
//        // Enable remote debugging via chrome://inspect
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WebView.setWebContentsDebuggingEnabled(true);
//        }

    }

    private void initEvent() {
    }

    private void initIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        news_id = intent.getStringExtra(NEWS_ID);

        final String source = intent.getStringExtra(SOURCE);
        contentWv.setWebViewClient(new MyWebViewClient() {
            boolean isLoadUrl = false;
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                T.showToast("rankInfo1");
                sendReadTime();
                toRankActivity(source);
                T.showToast("rankInfo2");
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if(!isLoadUrl){
                    isLoadUrl = true;
                    view.loadUrl(url);

                }
                super.onPageStarted(view, url, favicon);
            }
        });

        String content = intent.getStringExtra(CONTENT);
        Log.d("html-content", "html-content: " + content);
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        sendReadTime();
    }

    private void sendReadTime(){
        endMillis = System.currentTimeMillis();
        reading_time = endMillis - startMillis;
        Log.d("readingTime", "reading_time: "+reading_time);
        newsBusiness.sendReadInfo(news_id, reading_time, new CommonCallback<Token>() {
            @Override
            public void onError(Exception e) {
                Log.d("response1", "reading_error_back");
            }

            @Override
            public void onResponse(Token response) {
                Log.d("response2", "reading_success_back");
            }
        });
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

    private void toRankActivity(String source) {
        Intent intent = new Intent(this, NewsInfoActivity.class);
        intent.putExtra("source", source);
        startActivity(intent);
    }

    //设置webview代理加载图片
    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();
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

//        private void aReset(){
//            contentWv.loadUrl("javascript:(function(){" +
//                    "var img0 = getElementsByClassName('auther-logo');"+
//                    "img0.style.display='none';"+
//                "})()");
//        }

    }
}
