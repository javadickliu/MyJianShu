package com.example.liudongxun.myjianshu.contract;

import com.example.liudongxun.myjianshu.model.HomeDatasBean;

import java.util.List;

/**
 * Created by liudongxun on 2017/08/30.
 */

public interface MyNewsContract {
    public  interface MyNewsView{
          void showInfo(HomeDatasBean list,boolean ok);
          void showError(String msg);

    }
    public interface  MyNewsPresenter //接口定義方法是要在presenter實現網絡加載的邏輯
    {
        void loadingDatasFromNet();//网络获取数据
        void loadingDatasFromCache();//缓存获取数据
    }
}
