package com.dch.mybehavior.demo6;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.R;

import java.util.ArrayList;
import java.util.List;

public class Demo6Activity3 extends BaseActivity {

    private ImageView imageView;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private TextView textView;

    private VpAdapter mAdapter;
    private List<String> titleList = new ArrayList<>(); //页卡标题集合
    private ArrayList<Fragment> fragmentList = new ArrayList<>(); //页卡视图集合

    private MyFragment f1, f2, f3;

    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo6_layout3);

        initView();
        setExpand();
        bindPager();
    }

    private void bindPager() {
        titleList.add("f1");
        titleList.add("f2");
        titleList.add("f3");

        f1 = new MyFragment();
        f2 = new MyFragment();
        f3 = new MyFragment();
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));
        mAdapter = new VpAdapter(getSupportFragmentManager(), fragmentList);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(mAdapter);

    }

    private void setExpand() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            /*
             * offsetChanged 里第二个参数verticalOffset含义指的是垂直滑动的距离
             * 向上滑动得到的值是负的，
             * 初始值为0 就是展开状态。
             * */
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        textView.setShadowLayer(10, 5, 5, Color.BLACK);//展开设置阴影
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        textView.setShadowLayer(0, 0, 0, Color.BLACK);//折叠取消阴影
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            textView.setShadowLayer(0, 0, 0, Color.BLACK);//中间到展开取消阴影
                        } else {
                            textView.setShadowLayer(10, 5, 5, Color.BLACK);//折叠到中间设置阴影
                        }

                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
    }

    class VpAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public VpAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        /*该方法必须写,不然tablayout不能显示标题*/
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.user_pager);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bars);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.user_tab);
        imageView = (ImageView) findViewById(R.id.user_bg);
        textView = (TextView) findViewById(R.id.user_name);

        setSupportActionBar(toolbar);
        //启用HomeAsUp按钮，也就是回退键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //处理HomeAsUp回退按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
