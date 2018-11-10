package com.dch.mybehavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dch.mybehavior.demo1.Demo1Activity;
import com.dch.mybehavior.demo2.Demo2Activity;
import com.dch.mybehavior.demo3.Demo3Activity;
import com.dch.mybehavior.demo4.Demo4Activity;
import com.dch.mybehavior.demo5.Demo5Activity;
import com.dch.mybehavior.demo5.Demo5Activity2;
import com.dch.mybehavior.demo6.Demo6Activity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<String> list = new ArrayList<>();
        list.add("自定义behavior方式一");
        list.add("自定义behavior方式二");
        list.add("标题渐变隐藏");
        list.add("五种layout_scrollFlags");
        list.add("FloatingActionButton");
        list.add("三种layout_collapseMode");
        MyAdapter myAdapter = new MyAdapter(R.layout.main_item, list);
        recyclerview.setAdapter(myAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        toDetail(Demo1Activity.class);
                        break;
                    case 1:
                        toDetail(Demo2Activity.class);
                        break;
                    case 2:
                        toDetail(Demo3Activity.class);
                        break;
                    case 3:
                        toDetail(Demo4Activity.class);
                        break;
                    case 4:
                        toDetail(Demo5Activity.class);
                        break;
                    case 5:
                        toDetail(Demo6Activity.class);
                        break;
                }
            }
        });

    }

    private void toDetail(Class<?> cls) {
        final Intent intent = new Intent();
        intent.setClass(MainActivity.this, cls);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
