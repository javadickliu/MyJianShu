package com.example.liudongxun.myjianshu.presenter;

import android.util.Log;

import com.example.liudongxun.myjianshu.contract.MyNewsContract;
import com.example.liudongxun.myjianshu.fragment.BaseFragment;
import com.example.liudongxun.myjianshu.fragment.HomeFragment;
import com.example.liudongxun.myjianshu.model.HomeDatasBean;
import com.example.liudongxun.myjianshu.networkservice.HomeGetNetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liudongxun on 2017/08/30.
 */
//presenter層
public class NewsPresenterIm  implements MyNewsContract.MyNewsPresenter{

//    private  Retrofit retrofit;這裡儘量用局部變量吧
   private MyNewsContract.MyNewsView myNewsView1;//存儲實現MyNewsContract.MyNewsView接口的類的引用

    public NewsPresenterIm(MyNewsContract.MyNewsView myNewsView)
    {
      this.myNewsView1=myNewsView;//傳入HomeFragment的實例
    }

    @Override
    public void loadingDatasFromNet() {
        Retrofit  retrofit=new Retrofit.Builder().baseUrl("http://v.juhe.cn/toutiao/").addConverterFactory(GsonConverterFactory.create())
                                    .build();
        HomeGetNetService homeGetNetService=retrofit.create(HomeGetNetService.class);
        Call<HomeDatasBean> call=homeGetNetService.getCall();//返回的是一個list集合對象別搞錯了
         call.enqueue(new Callback<HomeDatasBean>() {
             @Override
             public void onResponse(Call<HomeDatasBean> call, Response<HomeDatasBean> response) {
                 Log.d("Tag-------",Thread.currentThread().getName());
                 myNewsView1.showInfo(response.body(),true);//这里这样写是不行的。

             }

             @Override
             public void onFailure(Call<HomeDatasBean> call, Throwable t) {
                 t.printStackTrace();

             }
         });


    }

    @Override
    public void loadingDatasFromCache() {

    }
}
