package com.example.thgame.weight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thgame.ConfigBuyActivity;
import com.example.thgame.GoodActivity;
import com.example.thgame.MainActivity;
import com.example.thgame.R;
import com.example.thgame.ZhuceActivity;
import com.example.thgame.gson.Logmsg;
import com.example.thgame.util.HttpUtil;
import com.example.thgame.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewzuce;
    private EditText editName;
    private EditText editpass;
    private Button btn;
    private ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);

        textViewzuce = (TextView) findViewById(R.id.zuce);
        editName = (EditText) findViewById(R.id.edname);
        editpass = (EditText) findViewById(R.id.edpass);
        btn = (Button) findViewById(R.id.btnSend);
        imageView = (ImageView) findViewById(R.id.loginbackground);
        Glide.with(LoginActivity.this).load(R.drawable.logback).into(imageView);

//        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear();
//        editor.commit();

        SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
        String usermsg = prefs.getString("user3",null);
        if(usermsg != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            initView();
        }


        textViewzuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ZhuceActivity.class);
                startActivityForResult(intent,1);
            }
        });


    }

//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//
//
//    }

    private void initView() {


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                        String address = "http://10.0.2.2:8089/tt/textServlet" ;
//                String address = "http://192.168.155.1:8080/qqqq/textServlet" ;
//                String address = "http://10.0.2.2:8087/logm.json" ;
//                String address = "http://10.4.88.165:8087/logm.json" ;
                        HttpUtil.sendOkHttpRequestLogin(address,editName.getText().toString(),editpass.getText().toString() ,new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this,"获取LoginMsg失败，请检查网络",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String ResponseText = response.body().string();




                        final Logmsg log = Utility.handleLogResponse(ResponseText);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(log!=null){

                                    Toast.makeText(LoginActivity.this,log.data.nickName,Toast.LENGTH_SHORT).show();

                                    if("201".equals(log.code)){
                                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                                        editor.putString("user3",ResponseText);
                                        editor.apply();

                                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);

                                        finish();

                                    }else {
                                     Toast.makeText(LoginActivity.this,"登录失败,请输入正确的账号密码",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                });



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(requestCode==RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    Log.d("LoginActivity",returnData);
                }
                break;
            default:
        }
    }
}
