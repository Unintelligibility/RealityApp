package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.ui.activity.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private EditText usernameEdt;
    private EditText passwordEdt;
    private Button loginBtn;
    private TextView registerTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();

    }

    private void initView() {
        usernameEdt = (EditText) findViewById(R.id.id_edt_username);
        passwordEdt = (EditText) findViewById(R.id.id_edt_password);
        loginBtn = (Button) findViewById(R.id.id_btn_login);
        registerTv = (TextView) findViewById(R.id.id_tv_register);
    }

    private void initEvent() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 检测登录是否成功
                toFirstPageActivity();
            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterActivity();
            }
        });
    }

    private void toFirstPageActivity() {
        Intent intent = new Intent(this,FirstPageActivity.class);
        startActivity(intent);
        finish();
    }

    private void toRegisterActivity() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
