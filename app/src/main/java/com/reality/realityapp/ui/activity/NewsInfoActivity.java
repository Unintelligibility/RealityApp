package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.utils.T;

public class NewsInfoActivity extends BaseActivity {

    private TextView contentTv;

    public static final String CONTENT = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        initView();

        initEvent();

        initIntent(getIntent());
    }

    private void initView() {
        contentTv = (TextView) findViewById(R.id.id_tv_content);
    }

    private void initEvent() {
    }

    private void initIntent(Intent intent) {
        if (intent==null){
            return;
        }

        String content = intent.getStringExtra(CONTENT);
        T.showToast("content2"+content);
        if (TextUtils.isEmpty(content)){
            return;
        }
        contentTv.setText(content);

    }
}
