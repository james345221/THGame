package com.example.thgame.weight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thgame.R;
import com.example.thgame.gson.BuyerThing;

import java.util.List;

/**
 * Created by asus-pc on 2017/12/29.
 */

public class PeopleCommentAdapter extends RecyclerView.Adapter<PeopleCommentAdapter.ViewHolder> {



    private List<BuyerThing> mbuyerThingList;


    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView1;
        TextView textView2;
        TextView textView3;


        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.commentname);
            textView2 = (TextView) itemView.findViewById(R.id.commentday);
            textView3 = (TextView) itemView.findViewById(R.id.commentcontent);
        }
    }

    public PeopleCommentAdapter (List<BuyerThing> buyerThing){
        mbuyerThingList = buyerThing;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BuyerThing buyerThing = mbuyerThingList.get(position);
        holder.textView1.setText(buyerThing.getBuyerName());
        holder.textView2.setText(buyerThing.getBuyDay());
        holder.textView3.setText(buyerThing.getBuyerComment());
    }

    @Override
    public int getItemCount() {
        return mbuyerThingList.size();
    }
}
