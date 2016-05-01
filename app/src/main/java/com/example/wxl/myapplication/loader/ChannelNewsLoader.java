package com.example.wxl.myapplication.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.wxl.myapplication.bean.ChannelItem;
import com.example.wxl.myapplication.network.ChannelNewsRequest;

import java.util.List;

/**
 * Created by wxl on 4/30/16.
 */
public class ChannelNewsLoader extends AsyncTaskLoader<List<ChannelItem>> {
    private final String mChannelType;
    private final int ONCE_LOAD_NUM = 20;
    private int mPage;
    public ChannelNewsLoader(Context context, String type) {
        super(context);
        mChannelType = type;
        mPage = 1;
    }

    public void getPage(int page) {
        Log.d("wxl", "getPage");
        mPage = page;
        onContentChanged();
    }

    @Override
    public List<ChannelItem> loadInBackground() {
        Log.d("wxl", "loadInBackground");
        return ChannelNewsRequest.newInstance(mChannelType).getData(ONCE_LOAD_NUM, mPage);
    }
}
