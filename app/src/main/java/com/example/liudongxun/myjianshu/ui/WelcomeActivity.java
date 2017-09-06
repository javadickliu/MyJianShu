package com.example.liudongxun.myjianshu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.liudongxun.myjianshu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudongxun on 2017/08/25.
 */

public class WelcomeActivity extends AppCompatActivity {

   @BindView(R.id.welcome_img_screen)
   ImageView mWelcomImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
       intAnimation();//实现欢迎界面图片的渐变效果，利用动

    }
    private void intAnimation()
    {  //c采用调用xml资源文件的形式实现Tween动画的alpha动画效果
         Animation animation= AnimationUtils.loadAnimation(this,R.anim.view_animation_alpha);
         animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                WelcomeActivity.this.finish();
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
         mWelcomImage.setAnimation(animation);
         animation.start();
       /* 采用java程序实现Tween动画
       AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {//动画结束的时候跳转
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                WelcomeActivity.this.finish();
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mWelcomImage.setAnimation(alphaAnimation);
        alphaAnimation.start();*/
    }
}
