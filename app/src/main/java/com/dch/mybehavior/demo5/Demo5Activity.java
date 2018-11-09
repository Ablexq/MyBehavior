package com.dch.mybehavior.demo5;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.MyAdapter;
import com.dch.mybehavior.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Demo5Activity extends BaseActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private CoordinatorLayout mCoor;
    private TextView hide;
    //判断是否打开
    private boolean isOpen = false;
    private ArrayList<String> lists = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo5_layout);

        recyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mCoor = (CoordinatorLayout) findViewById(R.id.coor);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        hide = (TextView) findViewById(R.id.hide);

        // 线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] mTitles = getResources().getStringArray(R.array.titles);
        for (String mTitle : mTitles) {
            lists.add(mTitle);
            recyclerView.setAdapter(new MyAdapter(R.layout.main_item, lists));
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * SnackBar结合RotateBehavior使用
                 */
//                Snackbar.make(mCoor,"FAB", Snackbar.LENGTH_SHORT)
//                        .setAction("UNDO", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        })
//                        .show();
                if (!isOpen) {
                    turnLeft(v);
                } else {
                    turnRight(v);
                }
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnRight(fab);
            }
        });

    }

    //开始旋转
    public void turnLeft(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v, "rotation", 0, -155, -135);
        objectAnimator.setDuration(300);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
        hide.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.75f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        hide.startAnimation(alphaAnimation);
        hide.setClickable(true);
        isOpen = true;
    }

    //回到起始位置
    public void turnRight(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v, "rotation", -135, 20, 0);
        objectAnimator.setDuration(300);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
        hide.setVisibility(View.GONE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.75f, 0);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        hide.startAnimation(alphaAnimation);
        hide.setClickable(false);
        isOpen = false;
    }

}
