package com.example.xingy.meizi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.xingy.meizi.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static com.example.xingy.meizi.R.id.custom_videoplayer_standard;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //获取数据
        Intent intent=getIntent();
        //图片地址
        String url=intent.getStringExtra("url");
        //电影的id
        String movieid=intent.getStringExtra("movieid");
        //电影简介
        String status=intent.getStringExtra("status");
        //评分
        String sorce=intent.getStringExtra("score");
        //标题
        String name=intent.getStringExtra("name");
        //拼接播放链接http://43.241.227.35/btmovie/MoviePlay.m3u8?movieid=
        //playUrl=http://116.62.150.164/newmovie/MoviePlay.m3u8?id=282880&type=kankan
        String trueUrl="http://43.241.227.35/btmovie/MoviePlay.m3u8?movieid="+movieid+"&index=0";
        //调用节操的视频播放，用原生写一个播放器 对于目前来说难度太大，安卓自带的媒体播放太垃圾。。
        //目前还是出现了一系列的问题。
        //后面会把上面的信息放上去，目前先测试。
        Toast.makeText(Video.this,trueUrl,Toast.LENGTH_SHORT).show();
        JCVideoPlayerStandard jcVideoPlayerStandard= (JCVideoPlayerStandard) findViewById(custom_videoplayer_standard);
        //设置相关播放地址等
        jcVideoPlayerStandard.setUp(trueUrl,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,name);

    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
