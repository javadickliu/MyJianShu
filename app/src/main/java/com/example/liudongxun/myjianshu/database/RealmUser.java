package com.example.liudongxun.myjianshu.database;

import io.realm.RealmObject;

/**
 * Created by liudongxun on 2017/09/08.
 */

public class RealmUser extends RealmObject {
    public String newsUrl;

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
