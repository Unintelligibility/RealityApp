package com.reality.realityapp.net;

import com.google.gson.Gson;
import com.reality.realityapp.utils.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 铠联 on 2018/1/27.
 */

public abstract class CommonCallback<T> extends StringCallback {

    private Type type;

    public CommonCallback() {
        Class<? extends CommonCallback> clazz = getClass();
        Type genericSuperclass = clazz.getGenericSuperclass();

        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("没有写泛型");
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        type = parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onError(e);
    }

    @Override
    public void onResponse(String response, int id) {
        try {
            JSONObject resp = new JSONObject(response);
            int resultCode = resp.getInt("resultCode");

            if (resultCode == 1) {
                String data = resp.getString("data");
                onResponse((T) GsonUtil.getGson().fromJson(data, type));
            } else {
                onError(new Exception(resp.getString("resultMessage")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 返回true使得所有请求，无论response code为多少都当做响应成功，交给onResponse方法处理，
     * 便于不同错误响应的返回
     * @param response
     * @param id
     * @return
     */
    @Override
    public boolean validateReponse(Response response, int id) {
        return true;
    }
    //    @Override
//    public void onResponse(Call call, Response response) throws IOException {
//        Gson gson = new Gson();
//        onResponse((T) GsonUtil.getGson().fromJson(response.body().string(),type));
//    }

    public abstract void onError(Exception e);

    public abstract void onResponse(T response);
}
