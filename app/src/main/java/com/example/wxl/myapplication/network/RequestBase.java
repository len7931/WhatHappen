package com.example.wxl.myapplication.network;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by wxl on 4/30/16.
 */
public abstract class RequestBase {
    private static final OkHttpClient CLIENT;
    private static final Gson GSON;
    private static final String APIKEY = "0c2a96b258d97b2752570e84dc1fbfd2";

    static {
        CLIENT = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .build();
        GSON = new Gson();
    }

    protected OkHttpClient getClient() {
        return CLIENT;
    }

    protected Gson getGson() {
        return GSON;
    }

    protected  String getApikey() {
        return APIKEY;
    }


}
