package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reality.realityapp.R;
import com.reality.realityapp.utils.T;

public class RankInfoActivity extends AppCompatActivity {

    public static final String SOURCE= "source";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_info);

        initView();

        initEvent();

        initIntent(getIntent());
    }

    private void initView() {
    }

    private void initEvent() {
    }

    private void initIntent(Intent intent) {
        String source = intent.getStringExtra(SOURCE);


    }
}
