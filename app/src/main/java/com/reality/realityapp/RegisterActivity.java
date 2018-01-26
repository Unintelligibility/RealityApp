package com.reality.realityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reality.realityapp.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    private EditText usernameEdt;
    private EditText passwordEdt;
    private EditText repasswordEdt;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUpToolBar();
        initView();
        initEvent();

        setTitle("注册");
    }

    private void initEvent() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toThemeSelectActivity();
            }
        });
    }

    private void toThemeSelectActivity() {
        Intent intent = new Intent(this,ThemeSelectActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        usernameEdt = (EditText) findViewById(R.id.id_edt_username);
        passwordEdt = (EditText) findViewById(R.id.id_edt_password);
        repasswordEdt = (EditText) findViewById(R.id.id_edt_repassword);
        registerBtn = (Button) findViewById(R.id.id_btn_register);
    }
}
