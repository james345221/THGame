package com.example.thgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thgame.gson.GoodList;
import com.example.thgame.gson.GoodListFather;
import com.example.thgame.gson.Logmsg;
import com.example.thgame.util.HttpUtil;
import com.example.thgame.util.Utility;
import com.example.thgame.weight.GamemsgAdapter;
import com.example.thgame.weight.GoodListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class AllGoodsFragment extends Fragment {


    GoodListAdapter adapter;

    public SwipeRefreshLayout swipeRefreshLayout;

    private List<GoodList> usergoodlist = new ArrayList<>();

    private RecyclerView recyclerView;

    String s ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_goods, container, false);

        SharedPreferences prefs = getDefaultSharedPreferences(getContext());
        String usergoodmsg = prefs.getString("usergoodlist",null);
        if(usergoodmsg != null){

            GoodListFather usergoodmsg1 = Utility.handleGoodListResponse(usergoodmsg);
            showInfo(usergoodmsg1);
            initfindview(view);
        }else {
            Log.d("MainActivity","登录出错");
        }



        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.all_good_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestUserGood();
            }
        });

        return view;

    }

    private void initfindview(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_allgoods);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GoodListAdapter(usergoodlist);
        recyclerView.setAdapter(adapter);


    }

    private void reflash() {
        adapter = new GoodListAdapter(usergoodlist);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    private void showInfo(GoodListFather usergoodmsg1) {
        usergoodlist = usergoodmsg1.regoodList;

    }

    private void requestUserGood() {
        String address = "http://10.0.2.2:8089/tt/UserGoodListServlet";
//        String address = "http://192.168.155.2:8089/tt/UserGoodListServlet";
//        String address = "http://192.168.155.3:8089/tt/CommentServlet";
        SharedPreferences prefs = getDefaultSharedPreferences(getContext());
        String usermsg = prefs.getString("user3",null);
        s = new String();
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"获取失败，请检查网络",Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String requestText1 = response.body().string();


                final GoodListFather goodListfather = Utility.handleGoodListResponse(requestText1);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(goodListfather!=null){
                            if("203".equals(goodListfather.status)){
                                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                                editor.putString("usergoodlist",requestText1);
                                editor.apply();

                                showInfo(goodListfather);
                                reflash();

                                swipeRefreshLayout.setRefreshing(false);


                            }else {
                                Toast.makeText(getActivity(),"获取失败，请检查网络",Toast.LENGTH_SHORT).show();
                                swipeRefreshLayout.setRefreshing(false);
                            }

                        }
                    }
                });
            }
        });
    }


}
