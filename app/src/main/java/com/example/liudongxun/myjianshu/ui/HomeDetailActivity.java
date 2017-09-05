package com.example.liudongxun.myjianshu.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedetail);
        ButterKnife.bind(this);
        intToolBar();

    }

    public void intToolBar()//初始化toolbar
    {
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               switch (item.getItemId())
               {
                   case R.id.activity_homedetail_menu1:
                       Toast.makeText(HomeDetailActivity.this,"1",Toast.LENGTH_LONG).show();
                   break;
                   case R.id.activity_homedetail_menu2:
                       Toast.makeText(HomeDetailActivity.this,"2",Toast.LENGTH_LONG).show();
                       break;
                   case R.id.activity_homedetail_menu3:
                       Toast.makeText(HomeDetailActivity.this,"3",Toast.LENGTH_LONG).show();
                       break;
               }
                return true;
            }
        });
    }
}
