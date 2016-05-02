package com.example.wxl.myapplication.bean;

import java.util.List;

/**
 * Created by wxl on 5/2/16.
 */
public class SinaStatusBean {
    // 微博创建时间
    public String created_at;
    // 微博ID
    public long id;
    // 微博MID
    public long mid;
    // 字符串型的微博ID
    public String idstr;
    // 微博信息内容
    public String text;
    // 微博来源
    public String source;
    // 是否已收藏，true：是，false：否
    public Boolean favorited;
    // 是否被截断，true：是，false：否
    public Boolean truncated;
    // （暂未支持）回复ID
    public String in_reply_to_status_id;
    // （暂未支持）回复人UID
    public String in_reply_to_user_id;
    // （暂未支持）回复人昵称
    public String in_reply_to_screen_name;
    // 缩略图片地址，没有时不返回此字段
    public String thumbnail_pic;
    // 中等尺寸图片地址，没有时不返回此字段
    public String bmiddle_pic;
    // 原始图片地址，没有时不返回此字段
    public String original_pic;
    // 地理信息字段 详细
    public SinaGeoBean geo;
    // 微博作者的用户信息字段 详细
    public SinaUserBean user;
    // 被转发的原微博信息字段，当该微博为转发微博时返回 详细
    public SinaStatusBean retweeted_status;
    // 转发数
    public int reposts_count;
    // 评论数
    public int comments_count;
    // 表态数
    public int attitudes_count;
    // 暂未支持
    public int mlevel;
    // 微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
    // TODO confirm class
    public Object visible;
    // 微博配图ID。多图时返回多图ID，用来拼接图片url。用返回字段thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。
    // TODO confirm class
    public Object pic_ids;
    // 微博流内的推广微博ID
    public List<Integer> ad;
}
