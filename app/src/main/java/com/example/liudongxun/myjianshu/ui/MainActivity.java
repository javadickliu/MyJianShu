package com.example.liudongxun.myjianshu.ui;

import android.content.Context;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.liudongxun.myjianshu.MyApplication;
import com.example.liudongxun.myjianshu.R;
import com.example.liudongxun.myjianshu.fragment.FaxianFragment;
import com.example.liudongxun.myjianshu.fragment.GuanzhuFragment;
import com.example.liudongxun.myjianshu.fragment.HomeFragment;
import com.example.liudongxun.myjianshu.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.main_view_navigationbar)
    BottomNavigationBar mbottomNavigationBar;//注解只能用在成员变量
 @BindView(R.id.main_layout_fragment)
    FrameLayout frameLayout;

    private FragmentManager fm;

    private HomeFragment homeFragment;
    private FaxianFragment faxianFragment;
    private GuanzhuFragment guanzhuFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setBottom();//不加这个27m，加了28M应该没有泄漏
      /*  LeakClass leakClass=new LeakClass();
        leakClass.start();*/

    }
    //====================用来测试内存泄漏=========
 /* class LeakClass extends Thread{
         @Override
         public void run()
         {
             while(true)
             {
                try{
                 Thread.sleep(3600000);
                }catch (InterruptedException e)
                {
                 e.printStackTrace();
                }

             }
         }
    }*/
    //=========================================
    //=======设置bottomNavigationvar样式=====//
    private void setBottom()
    {
        mbottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_bottom_home_active,"首页")
              .setActiveColor(R.color.colorBottom).setInactiveIconResource(R.drawable.icon_bottom_home_normal));
        mbottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_bottom_found_active,"发现")
               .setActiveColor(R.color.colorBottom).setInactiveIconResource(R.drawable.icon_bottom_found_normal));
        mbottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_bottom_notice_active,"消息")
                .setActiveColor(R.color.colorBottom).setInactiveIconResource(R.drawable.icon_bottom_notice_normal));
        mbottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_bottom_me_active,"我的")
                .setActiveColor(R.color.colorBottom).setInactiveIconResource(R.drawable.icon_bottom_me_normal));
        mbottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setFirstSelectedPosition(0) //BottomNavigationItem是对应到导航每个item的类，可以通过这个类设置一些相关属性
                .initialise();//setFirstSelectedPosition设置刚开始的时候是打开哪一个item;//不同模式是导航切换的效果不一样而已
    mbottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {//表示按下哪一个item
            switch (position)
            {
                case 0:
                    showFragment(0);
                    break;
                case 1:
                    showFragment(1);
                    break;
                case 2:
                    showFragment(2);
                    break;
                case 3:
                    showFragment(3);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    });
     showFragment(0);//记得要先把第一个fragment显示出来
    }
    //================动态的加载fragment利用FragmentManager FragmentTransaction ;
    //===思路：按下某个item加载对应的fragment。在切换其他item的时候需要保存数据所以不从移FragmentTransaction
    //移除而是隐藏fragment，用到的时候show出来即可，
    private void showFragment(int position)
    {
       // FragmentTransaction fragmentTransaction=fm.beginTransaction();
      FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
      hideAllFragments(fragmentTransaction);
       switch (position)
       {
           case 0:
               if(homeFragment!=null)
               {
                fragmentTransaction.show(homeFragment);
               }else
               {
                homeFragment=new HomeFragment();
                 fragmentTransaction.add(R.id.main_layout_fragment,homeFragment);
               }
               break;
           case 1:
               if(faxianFragment!=null)
               {
                   fragmentTransaction.show(faxianFragment);
               }else
               {
                   faxianFragment=new FaxianFragment();
                   fragmentTransaction.add(R.id.main_layout_fragment,faxianFragment);
               }
               break;
           case 2:
               if(guanzhuFragment!=null)
               {
                   fragmentTransaction.show(guanzhuFragment);
               }else
               {
                   guanzhuFragment=new GuanzhuFragment();
                   fragmentTransaction.add(R.id.main_layout_fragment,guanzhuFragment);
               }
               break;
           case 3:
               if(mineFragment!=null)
               {
                   fragmentTransaction.show(mineFragment);
               }else
               {
                   mineFragment=new MineFragment();
                   fragmentTransaction.add(R.id.main_layout_fragment,mineFragment);
               }
               break;
              default:
                  break;
       }
       fragmentTransaction.commit();
    }
    private void hideAllFragments(FragmentTransaction ft) {

        if (homeFragment != null)
        {
            ft.hide(homeFragment);
        }
        if (guanzhuFragment != null)
        {
            ft.hide(guanzhuFragment);
        }
        if (mineFragment != null)
        {
            ft.hide(mineFragment);
        }
        if (faxianFragment != null)
        {
            ft.hide(faxianFragment);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
