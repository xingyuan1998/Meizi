package com.example.xingy.meizi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.xingy.meizi.Adapt.FragAdapter;
import com.example.xingy.meizi.FragmentPager.FragmentPager1;
import com.example.xingy.meizi.FragmentPager.FragmentPager2;
import com.example.xingy.meizi.FragmentPager.FragmentPager3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MenuItem menuItem;
    BottomNavigationView navigation ;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0,true);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1,true);
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2,true);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new FragmentPager1());
        fragments.add(new FragmentPager2());
        fragments.add(new FragmentPager3());
        FragAdapter fragAdapter=new FragAdapter(getSupportFragmentManager(),fragments);

        viewPager.setAdapter(fragAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setOffscreenPageLimit(3);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
