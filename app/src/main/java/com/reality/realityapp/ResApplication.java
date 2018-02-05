package com.reality.realityapp;

import android.app.Application;

import com.reality.realityapp.utils.SPUtils;
import com.reality.realityapp.utils.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 铠联 on 2018/2/5.
 */

public class ResApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        T.init(this);
        SPUtils.init(this,"sp_user.pref");

        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
