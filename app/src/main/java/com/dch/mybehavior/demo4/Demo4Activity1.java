package com.dch.mybehavior.demo4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;

import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Demo4Activity1 extends BaseActivity {

    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo4_layout1);
        ButterKnife.bind(this);

//        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mainToolbar.getLayoutParams();
//        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
    }
}
