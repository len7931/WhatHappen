package com.example.wxl.myapplication.module;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxl on 4/29/16.
 */
public class NewsTopic {
    public static List<String> getTopics() {
        List<String> topics = new ArrayList<String>(8);
        topics.add("android");
        topics.add("炉石");
        topics.add("魔兽世界");
        topics.add("房价");
        topics.add("股市");
        topics.add("苍井空");
        topics.add("阿里");
        topics.add("腾讯");
        return topics;
    }
}
