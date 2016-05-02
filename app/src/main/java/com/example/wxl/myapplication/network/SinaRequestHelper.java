package com.example.wxl.myapplication.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wxl on 5/2/16.
 */
public class SinaRequestHelper extends RequestBase {
    private static final String REPOST_BY_ME = "https://api.weibo.com/2/statuses/repost_by_me.json";
    private static final String TOKEN = "access_token";
    public static void getData() {
        Request req = new Request.Builder().url(REPOST_BY_ME + "?" + TOKEN + "=" + "2.00lem1BG02JCGjc6b15a9e0dcOxdLC").build();
        try {
            Response res = getClient().newCall(req).execute();
            Log.d("wxl", res.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
