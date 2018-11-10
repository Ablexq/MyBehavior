package com.dch.mybehavior.demo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.MainActivity;
import com.dch.mybehavior.MyAdapter;
import com.dch.mybehavior.R;
import com.dch.mybehavior.demo1.Demo1Activity;
import com.dch.mybehavior.demo2.Demo2Activity;
import com.dch.mybehavior.demo3.Demo3Activity;
import com.dch.mybehavior.demo5.Demo5Activity;
import com.dch.mybehavior.demo6.Demo6Activity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Demo4Activity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo4_layout);
        ButterKnife.bind(this);

        ArrayList<String> list = new ArrayList<>();
        list.add("不设置：固定，不会变化");
        list.add("scroll|enterAlways：即时上, 即时下（下滑时，先toolbar再RecyclerView）");
        list.add("scroll|snap：即时上，下来时需要滚动见顶才可以，如果视图只有底部25%显示，它将折叠。相反，如果它的底部75%可见，那么它将完全展开。");
        list.add("scroll|enterAlwaysCollapsed：即时上，下来时需要滚动见顶才可以");
        list.add("scroll|exitUntilCollapsed：即时上，但会保持最小高度不变，下来时需要滚动见顶才可以");
        list.add("scroll：即时上, 即时下(类似拼接的头部)");
        list.add("scroll|enterAlways|enterAlwaysCollapsed：即时上, 向下滑动时，先滑动到最小值，然后RecyclerView 开始滑动，到达边界时，ToolBar在向下滑动。");
        MyAdapter myAdapter = new MyAdapter(R.layout.main_item, list);
        recyclerview.setAdapter(myAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        toDetail(Demo4Activity1.class);
                        break;
                    case 1:
                        toDetail(Demo4Activity2.class);
                        break;
                    case 2:
                        toDetail(Demo4Activity3.class);
                        break;
                    case 3:
                        toDetail(Demo4Activity4.class);
                        break;
                    case 4:
                        toDetail(Demo4Activity5.class);
                        break;
                    case 5:
                        toDetail(Demo4Activity6.class);
                        break;
                    case 6:
                        toDetail(Demo4Activity7.class);
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
