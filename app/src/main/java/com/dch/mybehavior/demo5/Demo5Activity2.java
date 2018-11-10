package com.dch.mybehavior.demo5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.MyAdapter;
import com.dch.mybehavior.R;
import java.util.ArrayList;

public class Demo5Activity2 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo5_layout2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item" + i);
        }
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.behavior_demo_recycler);
        MyAdapter myAdapter = new MyAdapter(R.layout.main_item, list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
