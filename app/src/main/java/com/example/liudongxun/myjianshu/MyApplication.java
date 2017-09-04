package com.example.liudongxun.myjianshu;

import android.app.Application;
import android.content.Context;
import android.os.Looper;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by liudongxun on 2017/08/26.
 */

public class MyApplication extends Application {
      RefWatcher refWatcher;

   public static RefWatcher getRefWatcher(Context context)//这个fragment检测的时候用到
   {
       MyApplication application=(MyApplication)context.getApplicationContext();
       return  application.refWatcher;
   }
   @Override
     public void onCreate()
   {
     super.onCreate();
       refWatcher= LeakCanary.install(this);
       Fresco.initialize(this);//初始化fresco图片加载库
   }
}
