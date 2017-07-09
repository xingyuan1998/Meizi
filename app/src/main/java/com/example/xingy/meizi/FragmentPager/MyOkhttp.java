package com.example.xingy.meizi.FragmentPager;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by xingy on 2017/6/10.
 */

public class MyOkhttp{

    public static OkHttpClient client = new OkHttpClient();

    public static String jsondata;

    public static String get(String url){

        client.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
       @Override
       public void onFailure(Call call, IOException e) {

       }

       @Override
       public void onResponse(Call call, Response response) throws IOException {
           jsondata=response.body().string();
           Log.d(TAG, "onResponse: "+jsondata);
       }
       });

        return jsondata;
    }


}