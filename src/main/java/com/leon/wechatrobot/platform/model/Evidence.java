package com.leon.wechatrobot.platform.model;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class Evidence {
    private String title;
    private String snippet;
    private String url;
    private int sort;

    public Evidence(String title, String snippet, String url, int sort) {
        this.title = title;
        this.snippet = snippet;
        this.url = url;
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
