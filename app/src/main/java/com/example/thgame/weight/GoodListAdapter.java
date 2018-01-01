package com.example.thgame.weight;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thgame.R;
import com.example.thgame.db.Bigboss;
import com.example.thgame.gson.GoodList;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by asus-pc on 2017/12/30.
 */

public class GoodListAdapter extends RecyclerView.Adapter<GoodListAdapter.ViewHolder>{



    private List<GoodList> mGoodList;

    private Context mContext;




    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        TextView GoodName;
        TextView GoodPrice;
        TextView GoodDay;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            GoodName = (TextView) itemView.findViewById(R.id.list_item_goodname);
            GoodPrice = (TextView) itemView.findViewById(R.id.list_item_price);
            GoodDay = (TextView) itemView.findViewById(R.id.list_item_goodday);

        }
    }

    public  GoodListAdapter(List<GoodList> GoodList){
        this.mGoodList = GoodList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(mContext == null){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.usergoodlist_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodList goodList = mGoodList.get(position);
        holder.GoodName.setText(goodList.getGoodName());
        holder.GoodPrice.setText("￥"+goodList.getGoodPrice());
        holder.GoodDay.setText(goodList.getGoodDay());
    }

    @Override
    public int getItemCount() {
        return mGoodList.size();
    }
}
