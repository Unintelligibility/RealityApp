package com.reality.realityapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 初始进入界面，会自动跳转到登录界面
 */
public class SplashActivity extends AppCompatActivity {

    private Button skipButton;
    private Handler countHandler = new Handler();

    private Runnable runnableToLogin = new Runnable() {
        @Override
        public void run() {
            toLoginActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        skipButton = (Button) findViewById(R.id.id_btn_skip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginActivity();
                countHandler.removeCallbacks(runnableToLogin);
            }
        });

        countHandler.postDelayed(runnableToLogin,3000);
    }

    /**
     * 跳转到登录界面
     */
    public void toLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countHandler.removeCallbacks(runnableToLogin);
    }
}
