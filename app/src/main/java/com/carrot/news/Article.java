package com.carrot.news;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;

public class Article {

    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String aurthor;

    @SerializedName("urlToImage")
    private String urlToImage;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public String getAurthor() {
        return aurthor;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAurthor(String aurthor) {
        this.aurthor = aurthor;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
