package com.example.xingy.meizi.Adapt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xingy.meizi.Activity.Image;
import com.example.xingy.meizi.FragmentPager.Meizi;
import com.example.xingy.meizi.R;

import java.util.List;




/**
 * Created by xingy on 2017/6/10.
 */

public class MeiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements View.OnClickListener {
    private List<Meizi>meizis;
    private Context context;


    public MeiAdapter(Context context,List<Meizi>meizis){
        this.context=context;
        this.meizis=meizis;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meizi,parent,false);
        final MyViewHolder holder =new MyViewHolder(view);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Meizi meizi =meizis.get(position);
                Toast.makeText(view.getContext(),"you click image "+meizi.getUrl(),Toast.LENGTH_SHORT).show();
                String url = meizi.getUrl();
                Intent intent=new Intent(view.getContext(), Image.class);
                intent.putExtra("url",url);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //picasso实现
       // Picasso.with(context).load(meizis.get(position).getUrl()).into(((MyViewHolder)holder).imageView);
        Glide.with(context).load(meizis.get(position).getUrl()).centerCrop().into(((MyViewHolder)holder).imageView);
    }

    @Override
    public int getItemCount() {
        return meizis.size();
    }

    @Override
    public void onClick(View view) {

    }
    public void addItem(Meizi meizi,int postion){
        meizis.add(postion,meizi);
        notifyItemInserted(postion);
    }
    public  void delItem(Meizi meizi,int postion){
        meizis.remove(postion);
        notifyItemRemoved(postion);
    }


}
