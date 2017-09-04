package com.example.liudongxun.myjianshu.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.liudongxun.myjianshu.Adapter.RecyclerViewAdapter;
import com.example.liudongxun.myjianshu.Adapter.ViewpagerAdapter;
import com.example.liudongxun.myjianshu.BannerUrl;
import com.example.liudongxun.myjianshu.R;
import com.example.liudongxun.myjianshu.contract.MyNewsContract;
import com.example.liudongxun.myjianshu.model.HomeDatasBean;
import com.example.liudongxun.myjianshu.presenter.NewsPresenterIm;
import com.tmall.ultraviewpager.UltraViewPager;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;

/**
 * Created by liudongxun on 2017/08/26.
 */

public class HomeFragment extends BaseFragment implements MyNewsContract.MyNewsView{

 @BindView(R.id.fragment_home_recycleview)
 RecyclerView recyclerView;
@BindView(R.id.fragment_home_refreshlayout)
SwipeRefreshLayout refreshLayout;
/*   @BindView(R.id.fragment_home_header)
RecyclerViewHeader  recyclerViewHeader;*/
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private NewsPresenterIm newsPresenterIm;
    private Handler mhandler;
    private List<String> banner_img;
    //================首先需要实现四个抽象方法=============
   @Override
   public int intLayoutID() {//返回跟fragment对应的布局id
       return R.layout.fragment_home;
   }
    @Override
   public void intVareiables() {//实例化对象
        newsPresenterIm=new NewsPresenterIm(this);//實例化的同時把HomeFragment對象傳入NewsPresenterIml中
        mhandler=new Handler();
        banner_img=new ArrayList<>();
        banner_img= Arrays.asList(BannerUrl.Banner_Img);//数组转换为list

 /*    ActivityManager activityManager=  (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
     textView.setText(activityManager.getMemoryClass()+"");//这里获取到每个app最多可以分配96M内存*/
   }
    @Override
    public void intDate() {//利于presenter加载数据
        if(checkNet())
        {
            Log.d("Tag-------","333333333333333");
          newsPresenterIm.loadingDatasFromNet();
        }else
        {
            Log.d("Tag-------","4444444444444444");
        }
    }

    @Override
    public void intView() {//在这里做展示view的事情
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(checkNet()) {//如果有网络连接就刷新数据并]
                    Log.d("Tag-------","222222222222222");
                    intDate();//调用retrofit发送网络请求加载数据。
                    refreshLayout.setRefreshing(false);//关掉刷新显示
                    Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(),"网络异常哟",Toast.LENGTH_LONG).show();
                    mhandler.postDelayed(new Runnable()//利用handler延迟开启线程，开启线程结束刷新
                    {
                        @Override
                        public void run()
                        {
                            Log.d("Tag-------",Thread.currentThread().getName());
                            refreshLayout.setRefreshing(false);//关掉刷新显示
                        }
                    }, 2000);
                  }
                }
        });


//=================================================================
      /*  mQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://v.juhe.cn/toutiao/index?type=&key=d9fb5edf6bd1130fb34b2fa4e79aa412", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                 Log.d("TAG",response.toString());
               // textView.setText(response.toString());
            }
        }, new Respo
        nse.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG",error.getMessage(),error);
            }
        });
        jsonObjectRequest.setTag("my");
        mQueue.add(jsonObjectRequest);*/
    }

    //================實現view接口,作用是可以讓presenter調用，裏面的實例
    @Override
    public void showInfo(HomeDatasBean list,boolean resoponseOk) {//每次调用intDta方法之后获取网络成功的回调都在这里,有个bug
            recyclerViewAdapter=new RecyclerViewAdapter(list);//实例化的时候传入一个HomeDatasBean
            recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.MyItemOnClickListener() {
                @Override
                public void myItemClick(View v, int position) {
                    Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
                }
            });
            DividerItemDecoration dd = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
            dd.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.itemdivider1));
            recyclerView.addItemDecoration(dd);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//s
            View view=LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_header,recyclerView,false);
            setViewPager(view);
            recyclerViewAdapter.setmHeaderView(view);//recyrler添加header,?
        //recyclerview放在这里一个很大的原因，因为网络请求是耗时操作，需要开启线程去工作。如果我们把recycleview的
        //加载放在fragmetn的oncreateview的intview里面里面，主线程跑到这里的adapter是没有准备好数据的，所以是空的。
        //数据要在请求完成回调oncreate方法的时候才准备完成
    }
    public void setViewPager(View view)
    {
        UltraViewPager vp=view.findViewById(R.id.recyclerview_header_vp);
        ViewpagerAdapter vpadapter=new ViewpagerAdapter(banner_img);
        vp.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        vp.setAdapter(vpadapter);
        vp.setInfiniteLoop(true);
        vp.setAutoScroll(2000);
    }
    @Override
    public void showError(String msg) {
        Log.e("info", "--------------------->" + msg);
    }
    //=======================================================================



    @Override//重寫fargment destroy
    public void onDestroy() {
        super.onDestroy();
    //   mQueue.cancelAll("my");
        fixInputMethodManagerLeak(getContext());
    }


    //================================解决InpurMthodManager的内存泄漏==============
    //有效，先不管，有时间再细看
    public static void fixInputMethodManagerLeak(Context destContext) {

       /* if (SDK_INT < KITKAT || SDK_INT > 22) {
            return;
        }*/
        if (destContext == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f;
        Object obj_get;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f == null) {
                    return;
                }
                f.setAccessible(true);
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
