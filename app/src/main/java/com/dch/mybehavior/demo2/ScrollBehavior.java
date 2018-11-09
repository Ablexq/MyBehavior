package com.dch.mybehavior.demo2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * com.dch.mybehavior.demo2.ScrollBehavior
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior {


    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //onStartNestedScroll，这里的返回值表明这次滑动我们要不要关心，我们要关心什么样的滑动？这里是y轴方向上的。
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        int leftScrolled = target.getScrollY();
        child.setScrollY(leftScrolled);//让child的scrollY的值等于目标的scrollY的值
    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        ((NestedScrollView) child).fling((int) velocityY);//直接将现在的y轴上的速度传递传递给child，让他fling起来
        return true;
    }

}
