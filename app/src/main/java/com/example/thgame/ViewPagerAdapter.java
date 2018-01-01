package com.example.thgame;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by asus-pc on 2017/10/14.
 */

class ViewPagerAdapter extends PagerAdapter{

    private List<ImageView> images;

    public ViewPagerAdapter(Context context , List<ImageView> images){
        this.images = images;
    };

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {

        view.removeView(images.get(position));
    }
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        // TODO Auto-generated method stub
        view.addView(images.get(position));
        return images.get(position);
    }
}
