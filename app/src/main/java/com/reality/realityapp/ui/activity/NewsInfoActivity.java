package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.utils.T;

import java.io.InputStream;

public class NewsInfoActivity extends BaseActivity {

    private WebView contentWv;

    public static final String SOURCE = "source";
    public static final String CONTENT = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

//        setUpToolBar();
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

    }

    private void initEvent() {
    }

    private void initIntent(Intent intent) {
        if (intent==null){
            return;
        }

        final String source = intent.getStringExtra(SOURCE);
        contentWv.setWebViewClient(new MyWebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                toRankActivity(source);
                return true;
            }
        });

        String content = intent.getStringExtra(CONTENT);
//        content = "<img src='http://p0.ifengimg.com/pmop/2018/0219/ED77C409CF7D10AD0F61B41D125301C247A13D5B_size38_w532_h356.jpeg'/>";
        if (TextUtils.isEmpty(content)){
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
        contentWv.loadData(content,"text/html;charset=utf-8","UTF-8");
    }

    private void toRankActivity(String source) {
        Intent intent = new Intent(this, NewsInfoActivity.class);
        intent.putExtra("source",source);
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
                    "}" +
                    "})()");
        }

        private void aReset(){
            contentWv.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('a'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var a = objs[i];   " +
                " a.style.maxWidth = '100%';img.style.height='auto';" +
                "}" +
                "})()");}

    }
}
