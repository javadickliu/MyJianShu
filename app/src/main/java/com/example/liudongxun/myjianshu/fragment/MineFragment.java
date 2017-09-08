package com.example.liudongxun.myjianshu.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liudongxun.myjianshu.R;
import com.example.liudongxun.myjianshu.database.MyDatabaseHelper;
import com.example.liudongxun.myjianshu.database.RealmUser;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by liudongxun on 2017/08/27.
 */

public class MineFragment extends BaseFragment {

  @BindView(R.id.fragment_my_test1)
    Button button1;
    @BindView(R.id.fragment_my_test2)
    Button button2;


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
        button1.setOnClickListener(new View.OnClickListener() {
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
        /*        MyDatabaseHelper myhelper=new MyDatabaseHelper(getContext(),"mydata",null,2);
                SQLiteDatabase mdp=myhelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put("url","url11111");
                mdp.insert("boo1",null,contentValues);
                myhelper.close();*/
            }
        });
      button2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              RealmResults<RealmUser> result=realm.where(RealmUser.class).findAll();

              Toast.makeText(getContext(), result.get(0).getNewsUrl(),Toast.LENGTH_SHORT).show();
             /* MyDatabaseHelper myhelper=new MyDatabaseHelper(getActivity(),"mydata",null,2);
              SQLiteDatabase mdp=myhelper.getReadableDatabase();
              Cursor cursor=mdp.query("mydata",null,null,null,null,null,null);
              cursor.moveToFirst();
              String url=cursor.getString(cursor.getColumnIndex("url"));
              Toast.makeText(getContext(),url,Toast.LENGTH_SHORT).show();*/
          }
      });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();//记得要关掉不然一直持有数据库oom
    }
}
