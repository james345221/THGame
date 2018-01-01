package com.example.thgame.weight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.thgame.R;

/**
 * Created by asus-pc on 2017/10/16.
 */

public class GridViewAdapter extends BaseAdapter{

    private Context context;
    private int[] data;

    public GridViewAdapter(Context context,int[] data){

        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HolderView viewHolder = null;
        if(view == null){
            viewHolder = new HolderView();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_grid_home, null);
            viewHolder.iv_pic = (ImageView) view.findViewById(R.id.iv_adapter_grid_pic);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (HolderView) view.getTag();
        }


        viewHolder.iv_pic.setImageResource(data[i]);
        return view;
    }

    public class HolderView {

        private ImageView iv_pic;

    }
}
