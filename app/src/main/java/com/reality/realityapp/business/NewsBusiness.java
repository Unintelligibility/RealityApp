package com.reality.realityapp.business;

import android.app.Activity;
import android.app.DownloadManager;
import android.util.Log;
import android.widget.TextView;

import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.bean.ThemeItem;
import com.reality.realityapp.bean.Token;
import com.reality.realityapp.constant.Url;
import com.reality.realityapp.net.CommonCallback;
import com.reality.realityapp.utils.BasicAuthInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 新闻模块业务
 * Created by 铠联 on 2018/1/27.
 */

public class NewsBusiness {

    private static final String TAG = "NewsBusiness-request";

//    private final OkHttpClient okHttpClient = new OkHttpClient();

    public void newsListFirstDisplay(CommonCallback<Map<String, NewsItem>> commonCallback) {
        OkHttpUtils
                .get()
                .url(Url.baseUrl + "read")
                .tag(this)
                .build()
                .execute(commonCallback);
    }

    public void newsListDisplay(String userid, CommonCallback<Map<String, NewsItem>> commonCallback) {
        OkHttpUtils
                .get()
                .url(Url.baseUrl + userid + "/read")
                .tag(this)
//                .addHeader("Connection","close")
                .build()
                .execute(commonCallback);
    }

    public void newsSearch(String keyword, int start, int size, CommonCallback<Map<String, NewsItem>> commonCallback) {
        OkHttpUtils
                .get()
                .url(Url.baseUrl + keyword + "/" + String.valueOf(start) + "/" + String.valueOf(size) + "/search")
                .tag(this)
//                .addHeader("Connection","close")
                .build()
                .execute(commonCallback);
    }

    public void sendReadInfo(String news_id, long reading_time, CommonCallback<Token> commonCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("news_id", news_id);
            jsonObject.put("reading_time", reading_time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .postString()
                .mediaType(MediaType.parse("application/json"))
                .content(jsonObject.toString())
                .url(Url.baseUrl + "read_info")
                .tag(this)
                .build()
                .execute(commonCallback);
    }

    /**
     * read_info需要附带token的解决办法
     *
     * @param username
     * @param password
     * @param news_type
     * @param reading_time
     */
    public void sendReadInfo2(String username, String password, String title, String source, String news_type, String news_tags, long reading_time) {
        //创建带有basic auth的OkHttpClient
        //Stack OverFlow地址：https://stackoverflow.com/questions/22490057/android-okhttp-with-basic-authentication
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("source", source);
            jsonObject.put("news_type", news_type);
            jsonObject.put("news_tags", news_tags);
            jsonObject.put("reading_time", reading_time);
            Log.d(TAG, "title: " + title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("jsonObject: " + jsonObject);

        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(Url.baseUrl + "read_info")//请求的url
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
                System.out.println("推送失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "sendReadInfo: " + response.body().string());
                System.out.println("推送成功");
            }
        });
    }

    public void relatedNewsDisplay(String newsid, CommonCallback<List<NewsItem>> commonCallback) {
        OkHttpUtils
                .get()
                .url(Url.baseUrl + newsid + "/relate")
                .tag(this)
//                .addHeader("Connection","close")
                .build()
                .execute(commonCallback);
    }

    public void themeListDisplay(CommonCallback<Map<String, ThemeItem>> commonCallback) {
        OkHttpUtils
                .get()
                .url(Url.baseUrl + "theme_list")
                .tag(this)
//                .addHeader("Connection","close")
                .build()
                .execute(commonCallback);
    }

    public void themeNewsListDisplay(String theme_title, CommonCallback<Map<String, NewsItem>> commonCallback) {
        OkHttpUtils
                .get()
                .url(Url.baseUrl + theme_title + "/theme_news")
                .tag(this)
//                .addHeader("Connection","close")
                .build()
                .execute(commonCallback);
    }
//    public void newsListDisplay(Callback callback){
//        Request.Builder builder = new Request.Builder();
//        builder.url(Url.baseUrl+"read");
//        final Request request = builder.build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(callback);
//    }
//    /**
//     * 首页新闻列表展示，即注册时选择的感兴趣的各个方向的新闻推荐
//     */
//    public void newsListDisplay2(final TextView textView, final Activity activity){
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                Request.Builder builder = new Request.Builder();
//                builder.url(Url.baseUrl+"read");
//                Request request = builder.build();
//                Log.d(TAG,"run: "+request);
//                Call call = okHttpClient.newCall(request);
//                try {
//                    Response response = call.execute();
//                    if (response.isSuccessful()) {
//                        final String data = response.body().string();
//                        Log.d(TAG,"responese: "+data);
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                textView.setText(data);
//                            }
//                        });
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
}
