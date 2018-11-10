package com.dch.mybehavior.demo5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Demo5Activity1 extends BaseActivity {


    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo5_layout1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Snackbar.make(fab, "FAB", Snackbar.LENGTH_LONG)
                .setAction("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //这里的单击事件代表点击消除Action后的响应事件
                    }
                }).show();
    }
}
