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
import android.webkit.WebView;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.utils.T;

import java.io.InputStream;

public class NewsInfoActivity extends BaseActivity {

    private TextView contentTv;
    private WebView contentWv;

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
    }

    private void initEvent() {
    }

    private void initIntent(Intent intent) {
        if (intent==null){
            return;
        }

        String content = intent.getStringExtra(CONTENT);
        content = "<img src='http://p0.ifengimg.com/pmop/2018/0219/ED77C409CF7D10AD0F61B41D125301C247A13D5B_size38_w532_h356.jpeg'/>";
        if (TextUtils.isEmpty(content)){
            return;
        }
        CharSequence htmlContent;
        //Html.FROM_HTML_MODE_COMPACT表示html块元素之间使用一个换行符分隔
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            htmlContent = Html.fromHtml(content,Html.FROM_HTML_MODE_COMPACT);

        }else {
            htmlContent = Html.fromHtml(content);
        }
//        contentTv.setText(htmlContent);
        contentWv.loadData(content,"text/html","UTF-8");
    }
}
