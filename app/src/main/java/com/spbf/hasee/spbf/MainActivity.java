package com.spbf.hasee.spbf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import base.SpbfFragmentPagerAdapter;
import fragment.MianFragment;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    private DrawerLayout mDrawerLayout;
    private TextView btn_me;
    private TextView txv_tvlist;
    private  TextView tv_title;
    private ViewPager pager;
    private List<Fragment>fragList;
    private SpbfFragmentPagerAdapter vpAdapter;
    //底部按钮22
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initView2();
        initDatas();
    }


    private void initView() {
        setContentView(R.layout.activity_main);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.id_drawerLayout);
        btn_me= (TextView) findViewById(R.id.btn_me);
        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        txv_tvlist= (TextView) findViewById(R.id.tv_videolist);
        txv_tvlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,VideoList.class);
                startActivity(intent);
            }
        });

    }
    private void initView2() {
        tv_title= (TextView) findViewById(R.id.tev_title);
        pager= (ViewPager) findViewById(R.id.main_viewpager);
    }
    private void initDatas() {
        fragList=new ArrayList<>();
        MianFragment mainFragment=new MianFragment();
        fragList.add(mainFragment);
        //fragList.add(new VideoListFragment());
        vpAdapter=new SpbfFragmentPagerAdapter(getSupportFragmentManager(),fragList);
        pager.setAdapter(vpAdapter);
       // pager.setOffscreenPageLimit(1);
        pager.setOnPageChangeListener(this);
        setTitle(0);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTitle(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        switch (state) {
//            case 1: isScrolling = true;
//                isBackScrolling = false;
//                break;
//            case 2: isScrolling = false;
//                isBackScrolling = true;
//                break;
//            default:
//                isScrolling = false;
//                isBackScrolling = false;
//                break;
//        }
    }
    public void setTitle(int position){
        switch (position){
            case 1:
                tv_title.setText("视频列表");
                break;
           default:tv_title.setText("首页");
               break;
        }
    }
}
