package com.example.xingy.meizi.FragmentPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.xingy.meizi.R;

/**
 * Created by xingy on 2017/6/9.
 *
 */

public class FragmentPager2 extends Fragment {
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout2,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        webView= (WebView) getView().findViewById(R.id.webView);
//
//        //设置JavaScript是否可以执行
//        webView.getSettings().setJavaScriptEnabled(true);
//        //当网页跳转另一个网页的时候目标网页依旧在其中显示。。
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("http://app161.quan.pigtk.com");

    }


}
