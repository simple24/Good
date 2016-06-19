package com.simplexu.good.model.bean;

/**
 * Created by Simple
 */
public class CollectBean {

    private String Url;
    private String Desc;
    private String Who;
    private String PublishedAt;
    private String Type;

    public CollectBean() {
    }

    public CollectBean(String url, String desc, String who, String publishedAt, String type) {
        Url = url;
        Desc = desc;
        Who = who;
        PublishedAt = publishedAt;
        Type = type;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getWho() {
        return Who;
    }

    public void setWho(String who) {
        Who = who;
    }

    public String getPublishedAt() {
        return PublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        PublishedAt = publishedAt;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
