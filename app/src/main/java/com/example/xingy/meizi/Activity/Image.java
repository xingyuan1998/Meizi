package com.example.xingy.meizi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.xingy.meizi.R;
import com.squareup.picasso.Picasso;

public class Image extends AppCompatActivity {
    ImageView imageView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView= (ImageView) findViewById(R.id.image);
        Intent intent=getIntent();
        String data=intent.getStringExtra("url");
        Picasso.with(Image.this).load(data).into(imageView);
    }
}
