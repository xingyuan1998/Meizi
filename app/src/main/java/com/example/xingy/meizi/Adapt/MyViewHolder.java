package com.example.xingy.meizi.Adapt;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.xingy.meizi.R;

/**
 * Created by xingy on 2017/6/10.
 */

public class MyViewHolder extends RecyclerView.ViewHolder{
    private View view;
    public ImageView imageView;


    public MyViewHolder(View itemView) {
        super(itemView);
        view=itemView;
        imageView= (ImageView) itemView.findViewById(R.id.imageView);
    }
}
