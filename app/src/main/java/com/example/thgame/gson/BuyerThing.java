package com.example.thgame.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus-pc on 2017/12/28.
 */



public class BuyerThing {


    @SerializedName("buyername")
    public String buyerName;

    @SerializedName("buyday")
    public String buyDay;

    @SerializedName("buyercomment")
    public String buyerComment;

    public String getBuyDay() {
        return buyDay;
    }

    public String getBuyerComment() {
        return buyerComment;
    }

    public String getBuyerName() {
        return buyerName;
    }
}
