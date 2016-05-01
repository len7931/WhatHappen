package com.example.wxl.myapplication.module;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxl on 4/29/16.
 */
public class NewsTopic {
    public static List<String> getTopics() {
        List<String> topics = new ArrayList<String>(5);
        topics.add("sports");
        topics.add("internation");
        topics.add("social");
        topics.add("tech");
        topics.add("apple");
        return topics;
    }
}
