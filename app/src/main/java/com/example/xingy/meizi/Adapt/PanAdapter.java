package com.example.xingy.meizi.Adapt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xingy.meizi.Activity.Video;
import com.example.xingy.meizi.FragmentPager.Pan;
import com.example.xingy.meizi.R;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by xingy on 2017/6/23.
 */

public class PanAdapter extends Adapter<PanAdapter.MyPanViewHolder>{
    private List<Pan>pen;
    private Context context;

    public PanAdapter(List<Pan>pen,Context context){
        this.context=context;
        this.pen=pen;
        this.context=context;
    }

    public class MyPanViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView name;
        public TextView status;
        public TextView score;
        public ImageView imageView;
        public MyPanViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView= (ImageView) itemView.findViewById(R.id.imageView);
            name= (TextView) itemView.findViewById(R.id.name);
            status= (TextView) itemView.findViewById(R.id.status);
            score= (TextView) itemView.findViewById(R.id.score);
        }
    }

    @Override
    public MyPanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pan,parent,false);
        final MyPanViewHolder myPanViewHolder = new MyPanViewHolder(view);
        myPanViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vPosition=myPanViewHolder.getAdapterPosition();
                Pan pan=pen.get(vPosition);
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url("http://120.55.16.187/newmovie/api/video?videoId=26260853")
                        .build();



                Intent intent=new Intent(context, Video.class);
                intent.putExtra("name",pan.getName());
                intent.putExtra("url",pan.getImg());

                intent.putExtra("movieid",pan.getMovieId());

                Toast.makeText(context,pan.getMovieId(),Toast.LENGTH_SHORT).show();
                intent.putExtra("status",pan.getStatus());
                intent.putExtra("sorce",pan.getScore().toString());
                context.startActivity(intent);

            }
        });

        return myPanViewHolder;
    }

    @Override
    public void onBindViewHolder(MyPanViewHolder holder, int position) {
       Pan pan=pen.get(position);
        Glide.with(context).load(pan.getImg()).centerCrop().into(holder.imageView);
        holder.name.setText(pan.getName());
        holder.status.setText(pan.getStatus());
        holder.score.setText(pan.getScore().toString());
    }

    @Override
    public int getItemCount() {
        return pen.size();
    }
}
