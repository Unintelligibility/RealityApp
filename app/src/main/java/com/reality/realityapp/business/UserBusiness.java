package com.reality.realityapp.business;

import com.reality.realityapp.bean.User;
import com.reality.realityapp.constant.Url;
import com.reality.realityapp.net.CommonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 铠联 on 2018/1/26.
 */

public class UserBusiness {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public void login(String username, String password, CommonCallback<User> commonCallback) {
        OkHttpUtils
                .post()
                .url(Url.baseUrl+"login")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallback);
    }

    public void register(String username, String password, CommonCallback<User> commonCallback) {
        OkHttpUtils
                .post()
                .url(Url.baseUrl+"signup")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallback);
    }

//    public void login(String username, String password, CommonCallback<User> commonCallback) {
//        FormBody formBody = new FormBody.Builder()
//                .add("username", username).add("password", password).build();
//
//        Request.Builder builder = new Request.Builder();
//        Request request = builder.post(formBody)
//                .url(Url.baseUrl+"login")
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(commonCallback);
//    }
//
//    public void register(String username, String password, CommonCallback<User> commonCallback) {
//        FormBody formBody = new FormBody.Builder()
//                .add("username", username).add("password", password).build();
//
//        Request.Builder builder = new Request.Builder();
//        Request request = builder.post(formBody)
//                .url(Url.baseUrl+"register")
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(commonCallback);
//    }

}
