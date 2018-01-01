package com.example.thgame.gson;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * Created by asus-pc on 2017/12/28.
 */

public class BuyerComment {

    public String status;

    public String msg;

    @SerializedName("comment")
    public List<BuyerThing> buyerThingList;
}
