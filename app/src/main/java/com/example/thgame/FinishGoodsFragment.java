package com.example.thgame;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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
import com.example.thgame.weight.GoodListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinishGoodsFragment extends Fragment {

    GoodListAdapter adapter1;

    private List<GoodList> usergoodlist = new ArrayList<>();

    private List<GoodList> finishusergoodlist = new ArrayList<>();

    public SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish_goods,container,false);

        SharedPreferences prefs = getDefaultSharedPreferences(getContext());
        String usergoodmsg = prefs.getString("usergoodlist",null);
        if(usergoodmsg != null){

            GoodListFather usergoodmsg1 = Utility.handleGoodListResponse(usergoodmsg);
            showInfo(usergoodmsg1);
            initfindview(view);
        }else {
            Log.d("MainActivity","出错");
        }

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.finish_swipe);
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_finishgoods);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter1 = new GoodListAdapter(finishusergoodlist);
        recyclerView.setAdapter(adapter1);


    }

    private void showInfo(GoodListFather usergoodmsg1) {
        finishusergoodlist.clear();
        usergoodlist = usergoodmsg1.regoodList;

        for (GoodList goodlist : usergoodlist){
            if(goodlist.getGoodIf().equals("1")){
                finishusergoodlist.add(goodlist);
            }

        }
    }

    private void reflash() {
        adapter1 = new GoodListAdapter(finishusergoodlist);
        adapter1.notifyDataSetChanged();
        recyclerView.setAdapter(adapter1);

    }



    private void requestUserGood() {
        String address = "http://10.0.2.2:8089/tt/UserGoodListServlet";
//        String address = "http://192.168.155.2:8089/tt/UserGoodListServlet";
//        String address = "http://192.168.155.3:8089/tt/CommentServlet";
        SharedPreferences prefs = getDefaultSharedPreferences(getContext());
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
