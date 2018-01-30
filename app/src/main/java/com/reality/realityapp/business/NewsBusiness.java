package com.reality.realityapp.business;

import android.app.Activity;
import android.app.DownloadManager;
import android.util.Log;
import android.widget.TextView;

import com.reality.realityapp.constant.Url;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 新闻模块业务
 * Created by 铠联 on 2018/1/27.
 */

public class NewsBusiness {

    private static final String TAG = "NewsBusiness-request";

    private final OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * 首页新闻列表展示，即注册时选择的感兴趣的各个方向的新闻推荐
     */
    public void newsArrayDisplay(final TextView textView, final Activity activity){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Request.Builder builder = new Request.Builder();
                builder.url(Url.newsUrl);
                Request request = builder.build();
                Log.d(TAG,"run: "+request);
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        final String data = response.body().string();
                        Log.d(TAG,"responese: "+data);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(data);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
