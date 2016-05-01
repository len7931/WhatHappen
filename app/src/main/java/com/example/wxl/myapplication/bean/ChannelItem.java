package com.example.wxl.myapplication.bean;

/**
 * Created by wxl on 4/30/16.
 */
public class ChannelItem {
    public String ctime;
    public String title;
    public String description;
    public String picUrl;
    public String url;


    @Override
    public boolean equals(Object o) {
        ChannelItem tmp = (ChannelItem)o;
        return (tmp.ctime.equals(this.ctime) && tmp.title.equals(this.title)
            && tmp.description.equals(this.description) && tmp.picUrl.equals(this.picUrl)
            && tmp.url.equals(this.url));
    }
}
