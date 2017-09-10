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
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liudongxun.myjianshu.R;
import com.example.liudongxun.myjianshu.database.RealmUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

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

    public Realm realm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedetail);
        ButterKnife.bind(this);
        Realm.init(this);
        intUrl();
        intButtom();
        intToolBar();
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
                finish();
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
                        setDialog();
                        break;
                    case R.id.activity_homedetail_menu2:
                        Intent it=new Intent(Intent.ACTION_SEND);
                        it.putExtra(Intent.EXTRA_TEXT,"这是我的练习app可以到我的github上去了解哟");
                        it.setType("text/plain");
                        startActivity(it);
                        break;
                    case R.id.activity_homedetail_menu3:
                          setToolbarReport();
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




   //==============================
   public  void  setShouCang(){
       img_guanzhu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                setDialog();
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
    private void setDialog()//设置dialog的点击事件
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(HomeDetailActivity.this);
        dialog.setTitle("确定是否要收藏此文章");
        dialog.setMessage("提醒:再次点击可取消收藏");
        dialog.setNegativeButton("取消收藏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RealmConfiguration config=new RealmConfiguration.Builder().name("data_test2")
                        .deleteRealmIfMigrationNeeded()
                        .build();
                realm=Realm.getInstance(config);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmUser realmUser=realm.where(RealmUser.class).equalTo("newsUrl",getIntent().getStringExtra("newsdetail")).findFirst();
                        realmUser.deleteFromRealm();
                    }
                });
                realm.close();
                Toast.makeText(HomeDetailActivity.this,"取消成功",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setPositiveButton("确定收藏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //==================添加数据
                RealmConfiguration config=new RealmConfiguration.Builder().name("data_test2")
                        .deleteRealmIfMigrationNeeded()
                                           .build();
                 realm=Realm.getInstance(config);
                RealmResults<RealmUser> result=realm.where(RealmUser.class).findAll();
                for(RealmUser user:result)//插入前先遍历一遍数据库，看看受否已经收藏
                {
                    Log.d("database",user.getNewsUrl());
                  //  Log.d("database",getIntent().getStringExtra("newsdetail"));
                    if(user.getNewsUrl().equals(getIntent().getStringExtra("newsdetail")))
                    {
                        Toast.makeText(HomeDetailActivity.this,"已经收藏过了",Toast.LENGTH_SHORT).show();
                       return;
                    }
                }
                Toast.makeText(HomeDetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmUser realmUser=realm.createObject(RealmUser.class);
                        realmUser.setNewsUrl(getIntent().getStringExtra("newsdetail"));
                    }
                });
                realm.close();
                //============添加数据
            }
        });
        dialog.show();


    }
    public void setToolbarReport()//设置toolbar的举报item的点击后触发的事件
    {


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
