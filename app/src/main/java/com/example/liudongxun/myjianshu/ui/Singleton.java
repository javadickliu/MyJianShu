package com.example.liudongxun.myjianshu.ui;

/**
 * Created by liudongxun on 2017/08/26.
 */

public class Singleton {//饿汉式单例模式
    private Singleton()
    {

    }
    private static final Singleton singleton=new Singleton();
    public Singleton getSingleton()
    {
        return  Singleton.singleton;
    }
}
