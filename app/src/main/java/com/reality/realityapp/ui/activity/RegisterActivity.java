package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reality.realityapp.R;
import com.reality.realityapp.UserInfoHolder;
import com.reality.realityapp.bean.User;
import com.reality.realityapp.business.UserBusiness;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.utils.T;

public class RegisterActivity extends BaseActivity {

    private EditText usernameEdt;
    private EditText passwordEdt;
    private EditText repasswordEdt;
    private Button registerBtn;

    private UserBusiness userBusiness = new UserBusiness();

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

                String username = usernameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                final String repassword = repasswordEdt.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    T.showToast("账号或者密码不能为空");
                    return;
                }

                if (!password.equals(repassword)){
                    T.showToast("两次输入的密码不一致");
                    return;
                }

                startLoadingProgress();

                userBusiness.register(username, password, new CommonCallback<User>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        T.showToast(e.getMessage());
                    }

                    @Override
                    public void onResponse(User response) {
                        stopLoadingProgress();
                        T.showToast("注册成功,用户名为："+response.getUsername());

                        //携带着username和password
                        LoginActivity.lauch(RegisterActivity.this,response.getUsername(),response.getPassword());
                        finish();
                    }
                });
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
