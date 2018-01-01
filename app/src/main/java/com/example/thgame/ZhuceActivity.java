package com.example.thgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thgame.util.HttpUtil;
import com.example.thgame.util.Utility;
import com.example.thgame.weight.LoginActivity;
import com.example.thgame.weight.QBReturn;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZhuceActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_zhuce);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText1 = (EditText) findViewById(R.id.zhuce_id);
        editText2 = (EditText) findViewById(R.id.zhuce_name);
        editText3 = (EditText) findViewById(R.id.zhuce_password);
        button = (Button) findViewById(R.id.zhuce_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText1.getText().toString())){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ZhuceActivity.this,"帐号不可为空",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if(TextUtils.isEmpty(editText2.getText().toString())){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ZhuceActivity.this,"昵称不可为空",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if(TextUtils.isEmpty(editText3.getText().toString())){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ZhuceActivity.this,"密码不可为空",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    requestFromService();
                }


            }
        });

    }



    public void requestFromService(){
        String address = "http://10.0.2.2:8089/tt/ZhuceServlet";

        HttpUtil.sendOkHttpRequestZhuce(address, editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ZhuceActivity.this,"请求失败，请检查网络",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String requestText1 = response.body().string();


                final QBReturn zhuceReturn = Utility.handleQBReturnResponse(requestText1);

                if(zhuceReturn.status.equals("0")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ZhuceActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent();
                    intent.putExtra("data_return","注册成功啦");
                    setResult(RESULT_OK, intent);
                    finish();
                }
                if(zhuceReturn.status.equals("1")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ZhuceActivity.this, "注册失败，账号已存在", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
