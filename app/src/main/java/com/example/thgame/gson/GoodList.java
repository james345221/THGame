package com.example.thgame.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus-pc on 2017/12/30.
 */

public class GoodList {

    @SerializedName("buystatus")
    public String GoodIf;

    @SerializedName("buything")
    public String GoodName;

    @SerializedName("price")
    public String GoodPrice;

    @SerializedName("buyday")
    public String GoodDay;

    public String getGoodIf() {
        return GoodIf;
    }

    public String getGoodDay() {
        return GoodDay;
    }

    public String getGoodName() {
        return GoodName;
    }

    public String getGoodPrice() {
        return GoodPrice;
    }
}
