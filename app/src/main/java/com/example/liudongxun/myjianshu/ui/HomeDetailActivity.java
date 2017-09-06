package com.example.liudongxun.myjianshu.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liudongxun.myjianshu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudongxun on 2017/09/05.
 */

public class HomeDetailActivity extends AppCompatActivity {
@BindView(R.id.activity_homedetail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_homedetail_webview)
    WebView webView;
    @BindView(R.id.activity_homedetail_guanzhu)
    ImageView img_guanzhu;
    @BindView(R.id.activity_homedetail_share)
    ImageView img_share;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedetail);
        ButterKnife.bind(this);
        intToolBar();
        intUrl();
        intButtom();
    }

    public void intToolBar()//初始化toolbar
    {
        setSupportActionBar(toolbar);//加这个menu才生效。。。但是不加这个navigation也能生效
        ActionBar ab=getSupportActionBar();
     //   ab.setDisplayHomeAsUpEnabled(true);//这里用的返回图标是默认的而且已经注册了点击事件
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent it=new Intent(HomeDetailActivity.this,MainActivity.class);
               startActivity(it);
             //   overridePendingTransition(0,R.anim.activity_out);
            }
        });
        ab.setDisplayShowTitleEnabled(false);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.activity_homedetail_menu1:
                        setShouCang();
                        break;
                    case R.id.activity_homedetail_menu2:
                        setShare();
                        break;
                    case R.id.activity_homedetail_menu3:

                        break;
                }
                return true;
            }
        });
    }
   public  void intUrl()
   {
        Intent i=getIntent();
        webView.loadUrl(i.getStringExtra("newsdetail"));
   }
   public void intButtom()
   {
     setShouCang();
     setShare();
   }
   public  void  setShouCang(){
       img_guanzhu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder dialog=new AlertDialog.Builder(HomeDetailActivity.this);
               dialog.setTitle("确定是否要收藏此文章");
               dialog.setMessage("提醒:再次点击可取消收藏");
               dialog.setNegativeButton("取消收藏", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Toast.makeText(HomeDetailActivity.this,"取消成功",Toast.LENGTH_SHORT).show();
                   }
               });
               dialog.setPositiveButton("确定收藏", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Toast.makeText(HomeDetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                   }
               });
               dialog.show();
           }
       });
   }
    public  void  setShare(){
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_TEXT,"这是我的练习app可以到我的github上去了解哟");
                it.setType("text/plain");
                startActivity(it);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }


}
