package com.reality.realityapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reality.realityapp.R;
import com.reality.realityapp.UserInfoHolder;
import com.reality.realityapp.bean.Token;
import com.reality.realityapp.bean.User;
import com.reality.realityapp.business.UserBusiness;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.ui.activity.base.BaseActivity;
import com.reality.realityapp.utils.BasicAuthInterceptor;
import com.reality.realityapp.utils.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import java.io.IOException;

public class LoginActivity extends BaseActivity {

    private EditText usernameEdt;
    private EditText passwordEdt;
    private Button loginBtn;
    private TextView registerTv;

    private UserBusiness userBusiness = new UserBusiness();

    public static final String KEY_USERNAME = "key_username";
    public static final String KEY_PASSWORD = "key_password";

    @Override
    protected void onResume() {
        super.onResume();
        //移除cookie中的记录，使得每次登陆都要输入账号和密码
        CookieJarImpl cookieJar = (CookieJarImpl) OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        cookieJar.getCookieStore().removeAll();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();

        initIntent(getIntent());

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

                final String username = usernameEdt.getText().toString();
                final String password = passwordEdt.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    T.showToast("账号或者密码不能为空");
                    return;
                }

                startLoadingProgress();

                userBusiness.login(username, password, new CommonCallback<Token>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        T.showToast(e.getMessage());
                    }

                    @Override
                    public void onResponse(Token response) {
                        stopLoadingProgress();
                        T.showToast("登陆成功:" + response.getToken());
                        OkHttpUtils.getInstance().getOkHttpClient().newBuilder().addInterceptor(new BasicAuthInterceptor(response.getToken(), ""));

                        //保存用户的信息
                        User user = new User(response.get_id(), username, password, response.getToken());
                        UserInfoHolder.getInstance().setUser(user);


                        //传递token给兴趣领域选择界面，传输json的authentication要用
                        Log.d("selected", "selected: " + response.getSelected());
                        if (response.getSelected()) {
                            toFirstPageActivity();
                        } else {
                            ThemeSelectActivity.launch(LoginActivity.this, username, password, response.getToken());
                        }
                        finish();
//                        toThemeSelectActivity();
                    }
                });
            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterActivity();
            }
        });
    }

    /**
     * 跳转到首页
     */
    private void toFirstPageActivity() {
        Intent intent = new Intent(this, FirstPageActivity.class);
        startActivity(intent);
        finish();
    }

    private void toRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 选择感兴趣的领域
     */
    private void toThemeSelectActivity() {
        Intent intent = new Intent(this, ThemeSelectActivity.class);
        startActivity(intent);
//        ThemeSelectActivity.launch(LoginActivity.this,response.getUsername(),response.getPassword());
        finish();
    }

    public static void lauch(Context context, String username, String password) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KEY_USERNAME, username);
        intent.putExtra(KEY_PASSWORD, password);
//        Log.d("login activity", "string1: "+username);
        context.startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntent(intent);
    }

    private void initIntent(Intent intent) {
//        Log.d("login activity", "initIntent: "+intent);
        if (intent == null) {
            return;
        }


        String username = intent.getStringExtra(KEY_USERNAME);
        String password = intent.getStringExtra(KEY_PASSWORD);
        Log.d("login activity", "string: " + username);
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return;
        }

        usernameEdt.setText(username);
        passwordEdt.setText(password);
    }
}
