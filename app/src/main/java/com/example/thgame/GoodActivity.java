package com.example.thgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thgame.gson.BuyerComment;
import com.example.thgame.gson.BuyerThing;
import com.example.thgame.util.HttpUtil;
import com.example.thgame.util.Utility;
import com.example.thgame.weight.PeopleCommentAdapter;
import com.example.thgame.weight.commentAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GoodActivity extends AppCompatActivity {

    private ImageView buyImage;

//    private PeopleCommentAdapter cAdapter;
//
//    private RecyclerView re;

    private List<BuyerThing> buyerList = new ArrayList<>();


    private ViewPager mGoodViewPaper;
    private ImageView imageView;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.timg,
            R.drawable.timg,
            R.drawable.timg,
            R.drawable.timg,
            R.drawable.timg
    };

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_good);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.buyButton);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodActivity.this,ConfigBuyActivity.class);
                startActivityForResult(intent,1);
            }
        });

        initView();

        requestComment();


    }

    private void requestComment() {
        String address = "http://10.0.2.2:8089/tt/CommentServlet";
//        String address = "http://192.168.155.2:8089/tt/CommentServlet";
//        String address = "http://192.168.155.3:8089/tt/CommentServlet";
        HttpUtil.sendOkHttpRequestComment(address, "QB", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GoodActivity.this,"获取失败，请检查网络",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                        final String requestText = response.body().string();


                        final BuyerComment buyerComment = Utility.handleCommentResponse(requestText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(buyerComment!=null){
                            if("202".equals(buyerComment.status)){
                              querycomment(buyerComment);


                                initRView();
                            }else {
                                Toast.makeText(GoodActivity.this,"获取mentco失败，请检查网络",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        });
    }

    private void initView() {

        buyImage = (ImageView) findViewById(R.id.buyButton);

        Glide.with(this).load(R.drawable.buy_now).into(buyImage);

        mGoodViewPaper = (ViewPager) findViewById(R.id.vp);

        //显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));

        adapter = new ViewPagerAdapter(this, images);
        mGoodViewPaper.setAdapter(adapter);

        mGoodViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });




    }

    private void initRView() {

        PeopleCommentAdapter  cAdapter = new PeopleCommentAdapter(buyerList);
        RecyclerView re = (RecyclerView) findViewById(R.id.goodRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        re.setLayoutManager(layoutManager);
        re.setAdapter(cAdapter);


//        cAdapter.setMbuyerThingList(buyerList);

    }

    public void querycomment(BuyerComment b){
        buyerList.addAll(b.buyerThingList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(requestCode==RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    Log.d("GoodActivity",returnData);
                }
                break;
            default:
        }
    }
}
