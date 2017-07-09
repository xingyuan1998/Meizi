package com.example.xingy.meizi.FragmentPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xingy.meizi.Adapt.PanAdapter;
import com.example.xingy.meizi.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import static android.content.ContentValues.TAG;

/**
 * Created by xingy on 2017/6/9.
 */

public class FragmentPager3 extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;
    //设置页数
    private int page;
    //设置对象容器
    private List<Pan>pen;
    //设置adapt
    private PanAdapter adapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout3,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
        new GetPanData().execute("http://120.55.16.187/newmovie/api/videos");
    }

    private void setListener() {

        //设置下拉刷新事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                //pen=null;
                new GetPanData().execute("http://120.55.16.187/newmovie/api/videos");
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)){
                    new GetPanData().execute("http://120.55.16.187/newmovie/api/videos?page="+(++page));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    private void initView() {
        recyclerView= (RecyclerView) getView().findViewById(R.id.recyclerView);
        swipeRefreshLayout= (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        Log.d(TAG, "initView: 567");
    }
    //目前先用AsyncTask先做，后面使用retrofit + rxjava 进行网络请求

    public class GetPanData extends AsyncTask<String,Void,String>{
        public String con;
        public OkHttpClient client = new OkHttpClient();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: 123");
            client.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);
                final Request request = new Request.Builder()
                        .url(strings[0])
                        .build();
                Log.d(TAG, "doInBackground: 111");
            try {
                con = client.newCall(request).execute().body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Toast.makeText(getContext(),"哈哈，没获取到数据",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.d(TAG, "doInBackground: 111");
//                        con=response.body().string();
//                        Log.d(TAG, "doInBackground: 111" + con);
//
//                    }
//                });
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
           Log.d(TAG, "doInBackground: "+con);
            return con;
//        } catch (IOException e) {
//                e.printStackTrace();
        }

            @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: bbb");
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: ccc");
            if (!TextUtils.isEmpty(s)){
                Log.d(TAG, "onPostExecute: ccc"+s);
                JSONObject jsonObject;
                Gson gson=new Gson();
                String jsondata=null;
                //数据转换成JavaBean
                try {
                    Log.d(TAG, "onPostExecute: ddd");
                    jsonObject=new JSONObject(s);
                    Log.d(TAG, "onPostExecute: ddd");
                    Log.d(TAG, "onPostExecute: "+jsonObject.getString("body"));
                    jsondata=jsonObject.getString("body");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (pen==null||pen.size()==0){
                    pen=gson.fromJson(jsondata, new TypeToken<List<Pan>>(){}.getType());

                }else {
                    List<Pan>more=gson.fromJson(jsondata,new TypeToken<List<Pan>>(){}.getType());
                    pen.addAll(more);
                }
                //把数据安置到Adapt中去
                if (adapter==null){
                    adapter=new PanAdapter(pen,getContext());
                    recyclerView.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }

            swipeRefreshLayout.setRefreshing(false);
            }

        }
    }
}
