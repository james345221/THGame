package com.example.thgame.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus-pc on 2017/10/27.
 */

public class Logmsg {

    @SerializedName("errcode")
    public String code;

    @SerializedName("msg")
    public String reMessage;

    public Data data;

    public class Data{
        @SerializedName("userid")
        public String userId;

        @SerializedName("nickname")
        public String nickName;
    }
}
