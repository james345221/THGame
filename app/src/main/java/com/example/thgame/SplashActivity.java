package com.example.thgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thgame.weight.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        setContentView(R.layout.activity_splash);

        ImageView splashimage = (ImageView) findViewById(R.id.splashimage);
//        Glide.with(this).load(R.drawable.start).into(splashimage);
        //透明度渐变动画
        AlphaAnimation animation = new AlphaAnimation(1.0f, 1.0f);
        //设置动画时长
        animation.setDuration(2000);
        //将组件和动画进行关联
        splashimage.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            //动画启动执行
            @Override
            public void onAnimationStart(Animation animation) {
//                Toast.makeText(SplashActivity.this, "欢迎使用DeMon制作微博", Toast.LENGTH_LONG).show();
                //检查并网络的方法
//                Tools.checkNetWork(SplashActivity.this);
            }

            //动画结束执行
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            //动画重复执行
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}