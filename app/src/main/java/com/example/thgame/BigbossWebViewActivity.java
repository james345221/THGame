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
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BigbossWebViewActivity extends AppCompatActivity {

    public static final String MSG_NAME = "msg_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_bigboss_web_view);

        Intent intent1 = getIntent();
        String title = intent1.getStringExtra(MSG_NAME);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


        String address ;

        final String blackaddress = "http://www.gamersky.com/news/201711/976994.shtml";
        final String kunteaddress = "http://www.ali213.net/news/html/2017-12/338397.html";
        final String mangrenaddress = "http://www.gamersky.com/news/201712/996742.shtml";
        final String longzhuaddress = "http://www.gamersky.com/news/201712/995638.shtml";
        final String jianianhuaaddress = "http://www.gamersky.com/review/201712/995010.shtml";
        final String zhanjinaddress = "http://www.gamersky.com/zl/column/201712/993219.shtml";
        final String qujiangaddress = "http://www.gamersky.com/zhuanti/wqj2017/";
        final String gumuaddress = "http://www.gamersky.com/news/201712/996859.shtml";


        if(title.equals("昆特牌")){
            address = kunteaddress;
            webView.loadUrl(address);
        }else if(title.equals("黑色洛城")){
            address = blackaddress;
            webView.loadUrl(address);
        }else if(title.equals("盲人吃鸡")){
            address=mangrenaddress;
            webView.loadUrl(address);
        }else if(title.equals("龙珠斗士超强集结")){
            address=longzhuaddress;
            webView.loadUrl(address);
        }else if(title.equals("嘉年华VR")){
            address=jianianhuaaddress;
            webView.loadUrl(address);
        }else if(title.equals("x战警回归漫威")){
            address=zhanjinaddress;
            webView.loadUrl(address);
        }else if(title.equals("趣奖游戏")){
            address=qujiangaddress;
            webView.loadUrl(address);
        }else if(title.equals("古墓丽影")){
            address=gumuaddress;
            webView.loadUrl(address);
        }



    }
}
