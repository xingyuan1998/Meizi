package com.example.xingy.meizi.FragmentPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xingy.meizi.Adapt.MeiAdapter;
import com.example.xingy.meizi.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by xingy on 2017/6/9.
 *
 */

public class FragmentPager1 extends Fragment {
    //变量声明
    private static RecyclerView recyclerView;
    private List<Meizi>meizis;
    private GridLayoutManager gridLayoutManager;
    private int lastVisibleItem;
    private int page=1;
    private ItemTouchHelper itemTouchHelper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MeiAdapter madapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("df", "onCreateView: ");
        if (getView()==null){
            View view=inflater.inflate(R.layout.layout1,container,false);
            return view;
        }else {
            return getView();
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化布局
        Log.d("ddff", "on ");
        initView();
        //swipeRefreshLayout.setRefreshing(true);
        //设置事件
        setListener();
        //数据加载
        //
        new GetData().execute("http://gank.io/api/data/福利/10/1");
        Log.d(TAG, "000onActivityCreated: ");

    }


    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                new GetData().execute("http://gank.io/api/data/福利/10/1");

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem +2>=gridLayoutManager.getItemCount()) {
                    new GetData().execute("http://gank.io/api/data/福利/10/"+(++page));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem =gridLayoutManager.findLastVisibleItemPosition();
            }
        });

    }


    private void initView() {
        recyclerView= (RecyclerView) getView().findViewById(R.id.recyclerView);
        swipeRefreshLayout= (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout);
        //设置布局管理器
        gridLayoutManager=new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        //目前尚不清楚这是干嘛
        //swipeRefreshLayout.setProgressViewOffset(false,0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));


    }
    private class GetData extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {
            return MyOkhttp.get(params[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "123onPostExecute: ");
            if (!TextUtils.isEmpty(s)){
                JSONObject jsonObjet;
                Gson gson =new Gson();
                String jsonData=null;
                try {
                    jsonObjet = new JSONObject(s);
                    jsonData = jsonObjet.getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (meizis==null||meizis.size()==0){
                    Log.d(TAG, "aaaonPostExecute: ");
                    meizis=gson.fromJson(jsonData,new TypeToken<List<Meizi>>(){}.getType());
                }else {
                    Log.d(TAG, "bbbonPostExecute: ");
                    List<Meizi> more = gson.fromJson(jsonData,new TypeToken<List<Meizi>>(){}.getType());
                    meizis.addAll(more);
                }
                if (madapter==null){
                    recyclerView.setAdapter(madapter=new MeiAdapter(getContext(),meizis));
                }else {
                    madapter.notifyDataSetChanged();
                }
            }

            swipeRefreshLayout.setRefreshing(false);
        }
    }

}
