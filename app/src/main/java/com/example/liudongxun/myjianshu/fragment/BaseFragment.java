package com.example.liudongxun.myjianshu.fragment;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liudongxun.myjianshu.MyApplication;
import com.example.liudongxun.myjianshu.presenter.NewsPresenterIm;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by liudongxun on 2017/08/27.
 */
//定义为抽象类，让继承这个抽象类的fragment都实现一样事件逻辑
public abstract class BaseFragment extends Fragment{
    private boolean connect;
    //onCreateView是fragment生命周期之一，在这里面做一些初始化动作

    public abstract int intLayoutID();
    public abstract void intVareiables();
    public abstract  void intDate();
    public abstract void intView();
    public static int intViewFlag=0;//注意静态方法的调用，在这里定义了，在其他地方可以通过类名.变量名访问

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(intLayoutID(),container,false);
        ButterKnife.bind(this,view);
        Realm.init(getActivity());//realm数据库
        intVareiables();
        intDate();
        intView();
     //，順序有問題是不對的，這裡需要有先後順序的,先加載對象的實例intVareiables，往佈局加載view，最後加載數據
        return view;
    }
    //写一个简单的判断网络连接的方法，没有区分是wifi还是流量哟
   public boolean checkNet()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
     /*  connect=((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE))
                                           .getActiveNetworkInfo()
                                           .isConnected();
        if(connect)
        {
           return true;
        }else
        {
          return false;
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher= MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
