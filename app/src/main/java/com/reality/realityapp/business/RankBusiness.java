package com.reality.realityapp.business;

import com.reality.realityapp.bean.NewsItem;
import com.reality.realityapp.bean.RankInfo;
import com.reality.realityapp.bean.RankItem;
import com.reality.realityapp.constant.Url;
import com.reality.realityapp.net.CommonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by 铠联 on 2018/3/10.
 */

public class RankBusiness {

    /**
     * 或者排名列表
     * @param commonCallback
     */
    public void rankListDisplay(CommonCallback<List<RankItem>> commonCallback){
        OkHttpUtils
                .get()
                .url(Url.baseUrl+"source")
                .tag(this)
                .build()
                .execute(commonCallback);
    }

    /**
     * 获得具体媒体来源的排名信息，比如新华社的排名信息
     * @param commonCallback
     * @param source
     */
    public void rankInfoDisplay(CommonCallback<RankInfo> commonCallback, String source){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("source",source);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .postString()
                .mediaType(MediaType.parse("application/json"))
                .content(jsonObject.toString())
                .url(Url.baseUrl+"rankInfo")
                .tag(this)
                .build()
                .execute(commonCallback);
    }
}
