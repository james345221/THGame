package com.example.thgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thgame.gson.GoodListFather;
import com.example.thgame.gson.Logmsg;
import com.example.thgame.util.HttpUtil;
import com.example.thgame.util.Utility;
import com.example.thgame.weight.QBReturn;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class ConfigBuyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private Button button;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;
    private EditText editText;
    private String p = "5QB0";
    private String s = null;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_config_buy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView1 = (TextView) findViewById(R.id.config_choice_5ge);
        textView2 = (TextView) findViewById(R.id.config_choice_100ge);
        textView3 = (TextView) findViewById(R.id.config_choice_250ge);
        textView4 = (TextView) findViewById(R.id.config_choice_500ge);
        textView5 = (TextView) findViewById(R.id.config_choice_1000ge);
        textView6 = (TextView) findViewById(R.id.config_choice_15000ge);
        textView7 = (TextView) findViewById(R.id.config_choice_20000ge);
        textView8 = (TextView) findViewById(R.id.config_choice_50000ge);
        textView9 = (TextView) findViewById(R.id.config_choice_100000ge);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);
        textView9.setOnClickListener(this);

        textView1.setBackgroundResource(R.drawable.yuanjiao);
        textView2.setBackgroundResource(R.drawable.yuanjiao);
        textView3.setBackgroundResource(R.drawable.yuanjiao);
        textView4.setBackgroundResource(R.drawable.yuanjiao);
        textView5.setBackgroundResource(R.drawable.yuanjiao);
        textView6.setBackgroundResource(R.drawable.yuanjiao);
        textView7.setBackgroundResource(R.drawable.yuanjiao);
        textView8.setBackgroundResource(R.drawable.yuanjiao);
        textView9.setBackgroundResource(R.drawable.yuanjiao);




        textView10 = (TextView) findViewById(R.id.config_textview1);
        textView10 = (TextView) findViewById(R.id.config_textview2);
        textView10 = (TextView) findViewById(R.id.config_textview3);
        textView10 = (TextView) findViewById(R.id.config_textview4);

        editText = (EditText) findViewById(R.id.config_edittext1);

        button = (Button) findViewById(R.id.config_btn);

        SharedPreferences prefs = getDefaultSharedPreferences(this);
        String usermsg = prefs.getString("user3",null);

        if(usermsg != null){
            Logmsg logmsg = Utility.handleLogResponse(usermsg);
            s=logmsg.data.nickName;
        }else {
            Log.d("MainActivity","登录出错");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String address = "http://10.0.2.2:8089/tt/QBServlet";
                HttpUtil.sendOkHttpRequestQB(address, p, s, price, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String requestText1 = response.body().string();


                        final QBReturn qbReturn = Utility.handleQBReturnResponse(requestText1);
                        if(qbReturn.status.equals("0")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ConfigBuyActivity.this,"购买成功",Toast.LENGTH_SHORT).show();
                                }
                            });

                            Intent intent = new Intent();
                            intent.putExtra("data_return","购买成功啦");
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                        if(qbReturn.status.equals("1")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ConfigBuyActivity.this, "购买失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.config_choice_5ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao_choice);
                textView2.setBackgroundResource(R.drawable.yuanjiao);
                textView3.setBackgroundResource(R.drawable.yuanjiao);
                textView4.setBackgroundResource(R.drawable.yuanjiao);
                textView5.setBackgroundResource(R.drawable.yuanjiao);
                textView6.setBackgroundResource(R.drawable.yuanjiao);
                textView7.setBackgroundResource(R.drawable.yuanjiao);
                textView8.setBackgroundResource(R.drawable.yuanjiao);
                textView9.setBackgroundResource(R.drawable.yuanjiao);
                p = textView1.getText().toString();
                price = "5";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
            case R.id.config_choice_100ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao);
                textView2.setBackgroundResource(R.drawable.yuanjiao_choice);
                textView3.setBackgroundResource(R.drawable.yuanjiao);
                textView4.setBackgroundResource(R.drawable.yuanjiao);
                textView5.setBackgroundResource(R.drawable.yuanjiao);
                textView6.setBackgroundResource(R.drawable.yuanjiao);
                textView7.setBackgroundResource(R.drawable.yuanjiao);
                textView8.setBackgroundResource(R.drawable.yuanjiao);
                textView9.setBackgroundResource(R.drawable.yuanjiao);
                p = textView2.getText().toString();
                price = "100";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
            case R.id.config_choice_250ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao);
                textView2.setBackgroundResource(R.drawable.yuanjiao);
                textView3.setBackgroundResource(R.drawable.yuanjiao_choice);
                textView4.setBackgroundResource(R.drawable.yuanjiao);
                textView5.setBackgroundResource(R.drawable.yuanjiao);
                textView6.setBackgroundResource(R.drawable.yuanjiao);
                textView7.setBackgroundResource(R.drawable.yuanjiao);
                textView8.setBackgroundResource(R.drawable.yuanjiao);
                textView9.setBackgroundResource(R.drawable.yuanjiao);
                p = textView3.getText().toString();
                price = "250";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
            case R.id.config_choice_500ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao);
                textView2.setBackgroundResource(R.drawable.yuanjiao);
                textView3.setBackgroundResource(R.drawable.yuanjiao);
                textView4.setBackgroundResource(R.drawable.yuanjiao_choice);
                textView5.setBackgroundResource(R.drawable.yuanjiao);
                textView6.setBackgroundResource(R.drawable.yuanjiao);
                textView7.setBackgroundResource(R.drawable.yuanjiao);
                textView8.setBackgroundResource(R.drawable.yuanjiao);
                textView9.setBackgroundResource(R.drawable.yuanjiao);
                p = textView4.getText().toString();
                price = "500";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
            case R.id.config_choice_1000ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao);
                textView2.setBackgroundResource(R.drawable.yuanjiao);
                textView3.setBackgroundResource(R.drawable.yuanjiao);
                textView4.setBackgroundResource(R.drawable.yuanjiao);
                textView5.setBackgroundResource(R.drawable.yuanjiao_choice);
                textView6.setBackgroundResource(R.drawable.yuanjiao);
                textView7.setBackgroundResource(R.drawable.yuanjiao);
                textView8.setBackgroundResource(R.drawable.yuanjiao);
                textView9.setBackgroundResource(R.drawable.yuanjiao);
                p = textView5.getText().toString();
                price = "1000";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
            case R.id.config_choice_15000ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao);
                textView2.setBackgroundResource(R.drawable.yuanjiao);
                textView3.setBackgroundResource(R.drawable.yuanjiao);
                textView4.setBackgroundResource(R.drawable.yuanjiao);
                textView5.setBackgroundResource(R.drawable.yuanjiao);
                textView6.setBackgroundResource(R.drawable.yuanjiao_choice);
                textView7.setBackgroundResource(R.drawable.yuanjiao);
                textView8.setBackgroundResource(R.drawable.yuanjiao);
                textView9.setBackgroundResource(R.drawable.yuanjiao);
                p = textView6.getText().toString();
                price = "15000";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
            case R.id.config_choice_20000ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao);
                textView2.setBackgroundResource(R.drawable.yuanjiao);
                textView3.setBackgroundResource(R.drawable.yuanjiao);
                textView4.setBackgroundResource(R.drawable.yuanjiao);
                textView5.setBackgroundResource(R.drawable.yuanjiao);
                textView6.setBackgroundResource(R.drawable.yuanjiao);
                textView7.setBackgroundResource(R.drawable.yuanjiao_choice);
                textView8.setBackgroundResource(R.drawable.yuanjiao);
                textView9.setBackgroundResource(R.drawable.yuanjiao);
                p = textView7.getText().toString();
                price = "20000";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
            case R.id.config_choice_50000ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao);
                textView2.setBackgroundResource(R.drawable.yuanjiao);
                textView3.setBackgroundResource(R.drawable.yuanjiao);
                textView4.setBackgroundResource(R.drawable.yuanjiao);
                textView5.setBackgroundResource(R.drawable.yuanjiao);
                textView6.setBackgroundResource(R.drawable.yuanjiao);
                textView7.setBackgroundResource(R.drawable.yuanjiao);
                textView8.setBackgroundResource(R.drawable.yuanjiao_choice);
                textView9.setBackgroundResource(R.drawable.yuanjiao);
                p = textView8.getText().toString();
                price = "50000";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
            case R.id.config_choice_100000ge:
                textView1.setBackgroundResource(R.drawable.yuanjiao);
                textView2.setBackgroundResource(R.drawable.yuanjiao);
                textView3.setBackgroundResource(R.drawable.yuanjiao);
                textView4.setBackgroundResource(R.drawable.yuanjiao);
                textView5.setBackgroundResource(R.drawable.yuanjiao);
                textView6.setBackgroundResource(R.drawable.yuanjiao);
                textView7.setBackgroundResource(R.drawable.yuanjiao);
                textView8.setBackgroundResource(R.drawable.yuanjiao);
                textView9.setBackgroundResource(R.drawable.yuanjiao_choice);
                p = textView9.getText().toString();;
                price = "100000";
                Toast.makeText(ConfigBuyActivity.this,p+price,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
