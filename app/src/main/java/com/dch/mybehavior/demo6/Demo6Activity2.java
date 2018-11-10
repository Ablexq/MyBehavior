package com.dch.mybehavior.demo6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.MyAdapter;
import com.dch.mybehavior.R;

import java.util.ArrayList;

public class Demo6Activity2 extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater mInflater;

    private ArrayList<String> mTitles = new ArrayList<>();//页卡标题集合
    private View view1, view2;
    private ArrayList<View> mViewList = new ArrayList<>();//页卡视图集合

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo6_layout2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //去掉标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mInflater = LayoutInflater.from(this);
        view1 = mInflater.inflate(R.layout.demo6_item, null);
        view2 = mInflater.inflate(R.layout.demo6_item, null);
        //添加到View集合
        mViewList.add(view1);
        mViewList.add(view2);

        //添加标题集合
        mTitles.add("安卓开发");
        mTitles.add("混合开发");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(1)));

        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        //mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }

    class MyPagerAdapter extends PagerAdapter {
        private ArrayList<View> mViewList;

        public MyPagerAdapter(ArrayList<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        /*该方法必须写,不然tablayout不能显示标题*/
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}