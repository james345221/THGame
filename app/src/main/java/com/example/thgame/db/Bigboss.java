package com.example.thgame.db;

/**
 * Created by asus-pc on 2017/12/24.
 */

public class Bigboss {
    private String name ;
    private  int imageId;

    public Bigboss(String name , int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
