package com.example.liudongxun.myjianshu.fragment;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liudongxun.myjianshu.R;
import com.example.liudongxun.myjianshu.database.MyDatabaseHelper;
import com.example.liudongxun.myjianshu.database.RealmUser;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by liudongxun on 2017/08/27.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

  @BindView(R.id.fragment_my_shouchang)
    Button btn_shouchang;
    @BindView(R.id.fragment_my_like)
    Button btn_like;
    @BindView(R.id.fragment_my_share)
    Button btn_share;
    @BindView(R.id.fragment_my_toolbar)
    Toolbar toolbar;
    private Realm realm;
    public int intLayoutID() {
        return R.layout.fragment_my;

    }

    @Override
    public void intVareiables() {

    }

    @Override
    public void intDate() {

    }

    @Override
    public void intView() {

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
  //      toolbar.setNavigationIcon(R.drawable.icon_activity_like);
        toolbar.setTitle("我的");

        btn_shouchang.setOnClickListener(this);
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
            case R.id.fragment_my_shouchang :

                break;
            case R.id.fragment_my_like :

                break;
            case R.id.fragment_my_share:

                break;
            default:
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();//记得要关掉不然一直持有数据库oom
    }
}
