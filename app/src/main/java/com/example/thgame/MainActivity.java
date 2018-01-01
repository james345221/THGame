package com.example.thgame;


import android.app.Fragment;
import android.content.Intent;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thgame.gson.BuyerComment;
import com.example.thgame.gson.GoodList;
import com.example.thgame.gson.GoodListFather;
import com.example.thgame.gson.Logmsg;
import com.example.thgame.util.HttpUtil;
import com.example.thgame.util.Utility;
import com.example.thgame.weight.FragmentTabHost;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater mInflater;
    public FragmentTabHost mTabHost;
    private ArrayList<TabDataBean> tabDataList = new ArrayList<>(4);
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);

        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        initTabHost();


        requestUserGood();
    }


    private void initTabHost() {
        //初始化fTabHost, 第三个参数为内容容器
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        /*初始化数据源*/
        TabDataBean tabHome = new TabDataBean(R.string.tabHome, R.drawable.selector_tab_home, HomeFragment.class);
        TabDataBean tabSearch = new TabDataBean(R.string.tabSearch, R.drawable.selector_tab_search, SearchFragment.class);
        TabDataBean tabShop = new TabDataBean(R.string.tabShop, R.drawable.selector_tab_shop, ShopFragment.class);
        TabDataBean tabMine = new TabDataBean(R.string.tabMyself, R.drawable.selector_tab_mine, MineFragment.class);
        tabDataList.add(tabHome);
        tabDataList.add(tabSearch);
        tabDataList.add(tabShop);
        tabDataList.add(tabMine);
        //添加底部菜单项-tabSpec
        for (TabDataBean bean : tabDataList) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(bean.getTabName()));
            //给菜单项添加内容，indicator,其中indicator需要的参数View即为菜单项的布局
            tabSpec.setIndicator(getIndicatorView(bean));
            //第二参数就是该菜单项对应的页面内容
            mTabHost.addTab(tabSpec, bean.getContent(), null);
            mTabHost.getTabWidget().setDividerDrawable(null);
        }
    }
    private View getIndicatorView(TabDataBean bean){
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView iconTab = (ImageView) view.findViewById(R.id.iv_tab_icon);
        TextView tvTab = (TextView) view.findViewById(R.id.tv_tab_text);
        iconTab.setImageResource(bean.getTabIcon());
        tvTab.setText(bean.getTabName());
        return view;
    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
////
////        Fragment f = fragmentManager.findFragmentByTag("");
////        f.onActivityResult(requestCode, resultCode, data);
//    }



    private void requestUserGood() {
        String address = "http://10.0.2.2:8089/tt/UserGoodListServlet";
//        String address = "http://192.168.155.2:8089/tt/UserGoodListServlet";
//        String address = "http://192.168.155.3:8089/tt/CommentServlet";
        SharedPreferences prefs = getDefaultSharedPreferences(this);
        String usermsg = prefs.getString("user3",null);
        String s = null;
        if(usermsg != null){
            Logmsg logmsg = Utility.handleLogResponse(usermsg);
            s=logmsg.data.nickName;
        }else {
            Log.d("MainActivity","登录出错");
        }


        HttpUtil.sendOkHttpRequestUserGoodList(address, s, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"获取失败，请检查网络",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String requestText1 = response.body().string();


                final GoodListFather goodListfather = Utility.handleGoodListResponse(requestText1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(goodListfather!=null){
                            if("203".equals(goodListfather.status)){
                                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                                editor.putString("usergoodlist",requestText1);
                                editor.apply();

                            }else {
                                Toast.makeText(MainActivity.this,"获取失败，请检查网络",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        });
    }

}
