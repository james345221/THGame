package com.example.thgame.util;

import android.text.TextUtils;


import com.example.thgame.gson.BuyerComment;
import com.example.thgame.gson.GoodList;
import com.example.thgame.gson.GoodListFather;
import com.example.thgame.gson.Logmsg;
import com.example.thgame.weight.QBReturn;
import com.google.gson.Gson;

/**
 * Created by asus-pc on 2017/10/27.
 */

public class Utility {
    public static Logmsg handleLogResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
//                response=response.replace("\\", "");
//                response=response.substring(1, response.length()-1);
                return new Gson().fromJson(response,Logmsg.class);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    public static BuyerComment handleCommentResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                return new Gson().fromJson(response, BuyerComment.class);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    public static GoodListFather handleGoodListResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                return new Gson().fromJson(response, GoodListFather.class);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    public static QBReturn handleQBReturnResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                return new Gson().fromJson(response, QBReturn.class);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  null;
    }
}
