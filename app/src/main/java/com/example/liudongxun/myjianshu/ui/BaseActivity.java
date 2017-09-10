package com.example.liudongxun.myjianshu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;

/**
 * Created by liudongxun on 2017/09/09.
 */

public abstract class BaseActivity extends AppCompatActivity {//定义成抽象类
    //从MyShouChangActivity用base其他没有用

    public abstract void intVariable();
    public abstract void intView();
    public abstract  int intLayout();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(intLayout());
        Realm.init(this);
        intVariable();
        intView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
