package com.reality.realityapp.business;

import android.app.Activity;
import android.app.DownloadManager;
import android.util.Log;
import android.widget.TextView;

import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.bean.Token;
import com.reality.realityapp.constant.Url;
import com.reality.realityapp.net.CommonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 新闻模块业务
 * Created by 铠联 on 2018/1/27.
 */

public class NewsBusiness {

    private static final String TAG = "NewsBusiness-request";

//    private final OkHttpClient okHttpClient = new OkHttpClient();

    public void newsListFirstDisplay(CommonCallback<Map<String,NewsItem>> commonCallback){
        OkHttpUtils
                .get()
                .url(Url.baseUrl+"read")
                .tag(this)
                .build()
                .execute(commonCallback);
    }

    public void newsListDisplay(String userid,CommonCallback<Map<String,NewsItem>> commonCallback){
        OkHttpUtils
                .get()
                .url(Url.baseUrl+userid+"/read")
                .tag(this)
                .build()
                .execute(commonCallback);
    }

    public void sendReadInfo(String news_id,long reading_time, CommonCallback<Token> commonCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("news_id",news_id);
            jsonObject.put("reading_time",reading_time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .postString()
                .mediaType(MediaType.parse("application/json"))
                .content(jsonObject.toString())
                .url(Url.baseUrl+"read_info")
                .tag(this)
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
