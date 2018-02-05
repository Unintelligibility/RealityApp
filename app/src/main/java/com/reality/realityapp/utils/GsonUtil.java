package com.reality.realityapp.utils;

import com.google.gson.Gson;

/**
 * Created by xkl on 17/10/23.
 */
public class GsonUtil {

    public static Gson mGson = new Gson();

    public static Gson getGson() {
        return mGson;
    }

}
