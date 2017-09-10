package com.example.liudongxun.myjianshu.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liudongxun.myjianshu.R;
import com.example.liudongxun.myjianshu.database.MyDatabaseHelper;
import com.example.liudongxun.myjianshu.database.RealmUser;
import com.example.liudongxun.myjianshu.database.RealmUserToUser;
import com.example.liudongxun.myjianshu.ui.MyShouChangActivity;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by liudongxun on 2017/08/27.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.fragment_my_user)
   Button btn_username;
    @BindView(R.id.fragment_my_shouchang)
    Button btn_shouchang;
    @BindView(R.id.fragment_my_like)
    Button btn_like;
    @BindView(R.id.fragment_my_share)
    Button btn_share;
    @BindView(R.id.fragment_my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_my_refreshlayout)
    SwipeRefreshLayout refreshLayout;
//    @BindView(R.id.fragment_my_refreshlayout)
//    SwipeRefreshLayout refreshlayout;
    EditText editText;
    private Realm realm;
    private  Handler mhandler;
    public int intLayoutID() {
        return R.layout.fragment_my;

    }

    @Override
    public void intVareiables() {
          mhandler=new Handler();
    }

    @Override
    public void intDate() {
     //   getUserName();

    }

    @Override
    public void intView() {

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("我的");//想navagationbar的位置是文字，那就不设置navigationbar就好了。title会自己移动到这里的
        btn_username.setOnClickListener(this);
        btn_shouchang.setOnClickListener(this);
        btn_like.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        setRefreshLayout();//设置下拉刷新功能
        //==============下拉刷新数据

 /*       button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm=Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmUser realmUser=realm.createObject(RealmUser.class);
                        realmUser.setNewsUrl("baidu.com");
                        Toast.makeText(getContext(),"插入数据成功",Toast.LENGTH_SHORT).show();
                    }
                });
      button2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              RealmConfiguration config=new RealmConfiguration.Builder().name("data_test2")
                      .build();
              realm=Realm.getInstance(config);
              RealmResults<RealmUser> result=realm.where(RealmUser.class).findAll();
              for(RealmUser user:result)
              {
                  Log.d("database",user.getNewsUrl());
              }
              Toast.makeText(getContext(), result.get(0).getNewsUrl(),Toast.LENGTH_SHORT).show();
             /* MyDatabaseHelper myhelper=new MyDatabaseHelper(getActivity(),"mydata",null,2);
              SQLiteDatabase mdp=myhelper.getReadableDatabase();
              Cursor cursor=mdp.query("mydata",null,null,null,null,null,null);
              cursor.moveToFirst();
              String url=cursor.getString(cursor.getColumnIndex("url"));
              Toast.makeText(getContext(),url,Toast.LENGTH_SHORT).show();*/
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fragment_my_user :
              setEditUserNameDialog();
                break;

            case R.id.fragment_my_like :

                break;
            case R.id.fragment_my_share:
                  setShareDialog();
                break;
            case R.id.fragment_my_help:

                break;
            default:
                break;
        }
    }
    public  void setRefreshLayout()
    {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(checkNet()) {//如果有网络连接就刷新数据并]
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("MyUserData",Context.MODE_PRIVATE);
                    btn_username.setText(sharedPreferences.getString("UserName","请输入用户名"));
                    refreshLayout.setRefreshing(false);//关掉刷新显示
                    Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_LONG).show();
                }
                else {
                    mhandler.postDelayed(new Runnable()//利用handler延迟开启线程，开启线程结束刷新
                    {
                        @Override
                        public void run()
                        {
                            Log.d("Tag-------",Thread.currentThread().getName());
                            refreshLayout.setRefreshing(false);//关掉刷新显示
                        }
                    }, 2000);
                    Toast.makeText(getContext(),"网络异常哟",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
  public void  setEditUserNameDialog()
  {
     editText=new EditText(getActivity());
      AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
      editText.setMaxLines(1);//这里输入的时候U如果输入回车换行，存储数据的时候会把这个换行也存进去导致显示有问题
      editText.setMaxEms(8);//这样的edittext控件会设置一下，
      builder.setTitle("输入你的用户名").setView(editText);
      builder.setNegativeButton("取消",null);
      builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
              SharedPreferences sp=getActivity().getSharedPreferences("MyUserData", Context.MODE_PRIVATE);
              SharedPreferences.Editor spe=sp.edit();
              spe.putString("UserName",editText.getText().toString());
              spe.commit();
            /*  RealmConfiguration config=new RealmConfiguration.Builder().name("database_user")
                      .build();
              realm=Realm.getInstance(config);
              realm.executeTransaction(new Realm.Transaction() {
                  @Override
                  public void execute(Realm realm) {
                      RealmUserToUser user=realm.createObject(RealmUserToUser.class);
                      user.setUsername(editText.getText().toString());
                  }
              });
           realm.close();*/
          }
      }).show();
  }
/*   public void getUserName()//创建这个fragment的时候
   {

   }*/
  public void setShareDialog(){
      Intent it=new Intent(Intent.ACTION_SEND);
      it.putExtra(Intent.EXTRA_TEXT,"这是我的练习app可以到我的github上去了解哟");
      it.setType("text/plain");
      startActivity(it);
  }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
