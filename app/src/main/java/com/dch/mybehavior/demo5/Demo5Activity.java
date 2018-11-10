package com.dch.mybehavior.demo5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.MyAdapter;
import com.dch.mybehavior.R;
import com.dch.mybehavior.demo4.Demo4Activity1;
import com.dch.mybehavior.demo4.Demo4Activity2;
import com.dch.mybehavior.demo4.Demo4Activity3;
import com.dch.mybehavior.demo4.Demo4Activity4;
import com.dch.mybehavior.demo4.Demo4Activity5;
import com.dch.mybehavior.demo4.Demo4Activity6;
import com.dch.mybehavior.demo4.Demo4Activity7;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Demo5Activity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo5_layout);
        ButterKnife.bind(this);

        ArrayList<String> list = new ArrayList<>();
        list.add("简单示例");
        list.add("仿知乎");

        MyAdapter myAdapter = new MyAdapter(R.layout.main_item, list);
        recyclerview.setAdapter(myAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        toDetail(Demo5Activity1.class);
                        break;

                    case 1:
                        toDetail(Demo5Activity2.class);
                        break;
                }
            }
        });
    }

    private void toDetail(Class<?> cls) {
        final Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
