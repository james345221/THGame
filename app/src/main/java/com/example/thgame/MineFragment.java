package com.example.thgame;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.thgame.gson.Logmsg;
import com.example.thgame.util.HttpUtil;
import com.example.thgame.util.Utility;
import com.example.thgame.weight.FragmentTabHost;
import com.example.thgame.weight.LoginActivity;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static java.security.AccessController.getContext;

/**
 * Created by asus-pc on 2017/10/12.
 */

class MineFragment extends Fragment {

    private TextView textView;
    private CircleImageView imageView1;
    private ImageView dislog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mine, container,false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.minetoolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        //沉浸顶部
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        initView(view);

        textView = (TextView) view.findViewById(R.id.textView1);
        imageView1 = (CircleImageView) view.findViewById(R.id.imageView1);
        dislog = (ImageView) view.findViewById(R.id.dislogbtn);



//        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit().clear();
//        editor.commit();
//


        SharedPreferences prefs = getDefaultSharedPreferences(getContext());
        String usermsg = prefs.getString("user3",null);
        if(usermsg != null){
           Logmsg logmsg = Utility.handleLogResponse(usermsg);
            showInfo(logmsg);
        }else {
            Log.d("MainActivity","登录出错");
        }


        dislog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit().clear();
                editor.commit();

                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        return view;
    }


    private void showInfo(Logmsg logmsg){

        textView.setText(logmsg.data.nickName);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

//    @Override
//    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//                switch (requestCode){
//            case 1:
//                if(resultCode==RESULT_OK){
//                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//
//                    String usermsg1 = prefs.getString("user1",null);
//                    final Logmsg logmsg1 = Utility.handleLogResponse(usermsg1);
//                    Toast.makeText(getActivity(),logmsg1.data.nickName,Toast.LENGTH_SHORT).show();
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            showInfo(logmsg1);
//                        }
//                    });
//                }
//        }
//    }
}