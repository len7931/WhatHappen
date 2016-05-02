package com.example.wxl.myapplication.bean;

/**
 * Created by wxl on 5/2/16.
 */
public class SinaShortUrlBean {
    // 短链接
    public String url_short;
    // 原始长链接
    public String url_long;
    // 链接的类型，0：普通网页、1：视频、2：音乐、3：活动、5、投票
    public int type;
    // 短链的可用状态，true：可用、false：不可用。
    public Boolean result;
}
