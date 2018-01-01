package com.example.thgame.weight;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thgame.BigbossWebViewActivity;
import com.example.thgame.R;
import com.example.thgame.db.Bigboss;

import java.util.List;

/**
 * Created by asus-pc on 2017/12/24.
 */

public class GamemsgAdapter extends RecyclerView.Adapter<GamemsgAdapter.ViewHolder> {

    private Context mContext;

    private List<Bigboss> mGameMsgList;



    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView gameMsgImage;
        TextView gameMsgName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            gameMsgImage = (ImageView) itemView.findViewById(R.id.gamemsg_image);
            gameMsgName = (TextView) itemView.findViewById(R.id.gamemsg_name);

        }
    }

    public  GamemsgAdapter(List<Bigboss> GamemsgList){
        this.mGameMsgList = GamemsgList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.gamemsg_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Bigboss bigboss = mGameMsgList.get(position);

                Intent intent = new Intent(mContext, BigbossWebViewActivity.class);
                intent.putExtra(BigbossWebViewActivity.MSG_NAME,bigboss.getName());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bigboss bigboss = mGameMsgList.get(position);
        holder.gameMsgName.setText(bigboss.getName());
        Glide.with(mContext).load(bigboss.getImageId()).into(holder.gameMsgImage);
    }

    @Override
    public int getItemCount() {
        return mGameMsgList.size();
    }


}
