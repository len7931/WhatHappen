package com.example.wxl.myapplication.network;

import android.util.Log;

import com.example.wxl.myapplication.bean.ChannelItem;
import com.example.wxl.myapplication.bean.ChannelJson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wxl on 4/30/16.
 */
public class ChannelNewsRequest extends RequestBase {
    private String baseUrl;
    private static HashMap<String, ChannelNewsRequest> POOL;
    private static HashMap<String, String> TYPE;

    private ChannelNewsRequest(String base) {
        baseUrl = base;
    }

    public static ChannelNewsRequest newInstance(String type) {
        if (TYPE == null)
            initType();
        if (POOL == null)
            initPool();
        return POOL.get(type);
    }

    private static void initPool() {
        POOL = new HashMap<>(TYPE.size());
        Iterator iter = TYPE.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry enter = (Map.Entry) iter.next();
            POOL.put((String)enter.getKey(), new ChannelNewsRequest((String)enter.getValue()));
        }
    }
   private static void initType() {
        TYPE = new HashMap<>(5);
        TYPE.put("sports", "http://apis.baidu.com/txapi/tiyu/tiyu");
        TYPE.put("social", "http://apis.baidu.com/txapi/social/social");
        TYPE.put("tech", "http://apis.baidu.com/txapi/keji/keji");
        TYPE.put("internation", "http://apis.baidu.com/txapi/world/world");
        TYPE.put("apple", "http://apis.baidu.com/txapi/apple/apple");
    }


    public List<ChannelItem> getData(int num, int page) {
        String url = baseUrl+ "?" + "num=" + num + "&page=" + page;
        Request req = new Request.Builder().url(url).addHeader("apikey", getApikey()).build();
        try {
            Response response = getClient().newCall(req).execute();
            String json = response.body().string();
            ChannelJson data = getGson().fromJson(json, new TypeToken<ChannelJson>(){}.getType());
            return data.newslist;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
