package com.example.liudongxun.myjianshu.networkservice;

import com.example.liudongxun.myjianshu.model.HomeDatasBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by liudongxun on 2017/08/30.
 */

public interface HomeGetNetService {
    @GET("index?type=&key=d9fb5edf6bd1130fb34b2fa4e79aa412")//聚合申請的新聞api
    Call<HomeDatasBean>  getCall();
}
