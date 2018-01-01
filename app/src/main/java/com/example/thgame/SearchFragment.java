package com.example.thgame;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thgame.db.Bigboss;
import com.example.thgame.weight.GamemsgAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.security.AccessController.getContext;

/**
 * Created by asus-pc on 2017/10/12.
 */

class SearchFragment extends Fragment {

    private Bigboss[] msgs = {
            new Bigboss("昆特牌", R.drawable.fenlei1),
            new Bigboss("黑色洛城", R.drawable.fenlei2),
            new Bigboss("盲人吃鸡", R.drawable.fenlei3),
            new Bigboss("龙珠斗士超强集结", R.drawable.fenlei4),
            new Bigboss("嘉年华VR", R.drawable.fenlei5),
            new Bigboss("x战警回归漫威", R.drawable.fenlei6),
            new Bigboss("趣奖游戏", R.drawable.fenlei7),
            new Bigboss("古墓丽影", R.drawable.fenlei8)};

    private List<Bigboss> msgList = new ArrayList<>();

    GamemsgAdapter adapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search, container,false);


        Toolbar toolbar1 = (Toolbar) view1.findViewById(R.id.findtoolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar1);
        initMsg();
        initfindview(view1);

        return view1;
    }

    private void initMsg() {
        msgList.clear();
        for(Bigboss bigboss : msgs){

            msgList.add(bigboss);
        }
    }

    private void initfindview(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GamemsgAdapter(msgList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
    }
}
