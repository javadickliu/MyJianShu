package com.example.liudongxun.myjianshu.database;

import io.realm.RealmObject;

/**
 * Created by liudongxun on 2017/09/09.
 */

public class RealmUserToUser extends RealmObject {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
