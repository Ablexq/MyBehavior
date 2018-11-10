package com.dch.mybehavior.demo6;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.MyAdapter;
import com.dch.mybehavior.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Demo6Activity1 extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo6_layout1);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //当CollapsingToolbarLayout完全折叠后的背景颜色
        collapsingToolbarLayout.setContentScrimColor(Color.RED);
        //当CollapsingToolbarLayout完全折叠后状态栏颜色
        collapsingToolbarLayout.setStatusBarScrimColor(Color.RED);
        //文字
        collapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        //标题颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLUE);//扩张颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.YELLOW);//折叠颜色

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item" + i);
        }
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.my_list);
        MyAdapter myAdapter = new MyAdapter(R.layout.main_item, list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
