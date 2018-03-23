package com.reality.realityapp.business;

import android.util.Log;

import com.reality.realityapp.bean.Token;
import com.reality.realityapp.bean.User;
import com.reality.realityapp.constant.Url;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.utils.BasicAuthInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 铠联 on 2018/1/26.
 */

public class UserBusiness {

//    private final OkHttpClient okHttpClient = new OkHttpClient();
    private static final String TAG = "UserBusiness";
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    public void login(String username, String password, CommonCallback<Token> commonCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",username);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .postString()
                .mediaType(MediaType.parse("application/json"))
                .content(jsonObject.toString())
                .url(Url.baseUrl+"signin")
                .tag(this)
//                .addHeader("Connection","close")
                .build()
                .execute(commonCallback);
    }

    public void register(String username, String password, CommonCallback<User> commonCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",username);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .postString()
                .mediaType(MediaType.parse("application/json"))
                .content(jsonObject.toString())
                .url(Url.baseUrl+"signup")
                .tag(this)
                .build()
                .execute(commonCallback);
    }

    public void themeSelect(String username, String password, List<String> selectedThemes, CommonCallback<Token> commonCallback) {
        JSONObject jsonObject = new JSONObject();
        //创建带有basic auth的OkHttpClient
        //Stack OverFlow地址：https://stackoverflow.com/questions/22490057/android-okhttp-with-basic-authentication
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        //将List转化为JSONArray
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<selectedThemes.size();i++) {
            System.out.println(selectedThemes.get(i));
            jsonArray.put(selectedThemes.get(i));
        }
        try {
            jsonObject.put("likes",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("jsonObject: " +jsonObject);

        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(Url.baseUrl+"interest")//请求的url
                .post(requestBody)
                .build();

        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "失败惹");
                System.out.println("连接失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                System.out.println("连接成功");
//                Log.v(TAG, response.body().string());
            }
        });
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
