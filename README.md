
参考：

[CoordinatorLayout高级用法-自定义Behavior](https://blog.csdn.net/qibin0506/article/details/50290421)


# 一、CoordinatorLayout【直接子控件】的自定义behavior

### 方式一：某个view监听另一个view的状态变化，例如大小、位置、显示状态等

关注：layoutDependsOn 和 onDependentViewChanged 方法，请见[demo1](https://github.com/Ablexq/MyBehavior/blob/master/app/src/main/java/com/dch/mybehavior/demo1/DependentBehavior.java)
```
/*
* 确定使用Behavior的View要依赖的View的类型
* parant     代表CoordinatorLayout，
* child      代表使用该Behavior的View，观察者
* dependency 代表要监听的View，被观察者
* */
@Override
public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
    return super.layoutDependsOn(parent, child, dependency);
}

//当被依赖的View状态改变时回调
@Override
public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
    return super.onDependentViewChanged(parent, child, dependency);
}
```

### 方式二：某个view监听CoordinatorLayout里的滑动状态

关注：onStartNestedScroll 和 onNestedPreScroll 方法。请见[demo2](https://github.com/Ablexq/MyBehavior/blob/master/app/src/main/java/com/dch/mybehavior/demo2/ScrollBehavior.java)
```
//嵌套滑动开始（ACTION_DOWN），确定Behavior是否要监听此次事件
@Override
public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
    return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
}

//嵌套滑动进行中，要监听的子 View将要滑动，滑动事件即将被消费（但最终被谁消费，可以通过代码控制）
@Override
public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
}

//要监听的子 View在快速滑动中
@Override
public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
    return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
}
```



### CoordinatorLayout

CoordinatorLayout 继承自viewgroup,但是使用类似于framLayout,有层次结构,后面的布局会覆盖在前面的布局之上,

但跟behavior属性也有很大关系的，

注意：app:layout_behavior属性,只有CoordinatorLayout的直接子布局才能响应

参见：[demo3](https://github.com/Ablexq/MyBehavior/blob/master/app/src/main/java/com/dch/mybehavior/demo3/TitleBehavior.java)


# 二、AppBarLayout【直接子控件】的五种layout_scrollFlags

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/app_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:elevation="0dp">

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/toolbar_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:contentScrim="#00ffffff"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:background="@mipmap/ic_launcher"
                android:fitsSystemWindows="true"
                android:scaleType="fitXY"
                app:layout_collapseMode="parallax"
                app:layout_collapseParallaxMultiplier="0.7" />

        </android.support.design.widget.CollapsingToolbarLayout>

    </android.support.design.widget.AppBarLayout>

    <android.support.v7.widget.RecyclerView
        android:id="@+id/my_list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />

    <TextView
        android:id="@+id/title"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="#ff0000"
        android:gravity="center"
        android:text="Hello World"
        android:textColor="#ffffff"
        android:textSize="18sp"
        app:layout_behavior="@string/demo3_behavior" />

</android.support.design.widget.CoordinatorLayout>

```
AppBarLayout继承自LinearLayout，布局方向为垂直方向。

AppBarLayout 就是实现了MD风格的滚动手势的LinearLayout。

AppBarLayout响应了CoordinatorLayout的layout_behavior属性

AppBarLayout的【直接子控件】可以设置的属性:layout_scrollFlags （是否可响应滑动）

> 不设置：固定，不会变化

> scroll|enterAlways：即时上, 即时下（下滑时，先toolbar再RecyclerView）

> scroll|snap：即时上，下来时需要滚动见顶才可以，如果视图只有底部25%显示，它将折叠。相反，如果它的底部75%可见，那么它将完全展开。

> scroll|enterAlwaysCollapsed：即时上，下来时需要滚动见顶才可以

> scroll|exitUntilCollapsed：即时上，但会保持最小高度不变，下来时需要滚动见顶才可以

> scroll：即时上, 即时下(类似拼接的头部, 下滑时，RecyclerView与Toolbar同时进行)

> scroll|enterAlways|enterAlwaysCollapsed：即时上, 向下滑动时，先滑动到最小值，然后RecyclerView 开始滑动，到达边界时，ToolBar在向下滑动。

参考：

[android Material Design 学习之六：AppBarLayout](https://blog.csdn.net/dhl_1986/article/details/80269007)


### CollapsingToolbarLayout

```
app:contentScrim="?attr/colorPrimary"
//设置当完全CollapsingToolbarLayout折叠(收缩)后的背景颜色。默认contentScrim是colorPrimary的色值
app:statusBarScrim="@android:color/transparent"
//设置当完全CollapsingToolbarLayout折叠(收缩)后的状态栏颜色。默认contentScrim是colorPrimaryDark的色值

app:expandedTitleMarginStart="48dp"
//设置扩张时候(还没有收缩时)title向左填充的距离。
```

### 代码中设置layout_scrollFlags：

```
AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | 
                      AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
```

注意：layout_scrollFlags可以多个结合使用：
```
app:layout_scrollFlags="scroll|exitUntilCollapsed|snap">

```


# 三、CollapsingToolbarLayout【直接子布局】的2种折叠模式

```
public class CollapsingToolbarLayout extends FrameLayout {...}
```

layout_collapseMode (折叠模式) - 有两个值:

```
app:layout_collapseMode="parallax"
//设置为这个模式时，在内容滚动时，CollapsingToolbarLayout中的View（比如ImageView)也可以同时滚动，实现视差滚动效果，
//（常用于图片/轮播图）
//通常和layout_collapseParallaxMultiplier(设置视差因子)搭配使用。
//app:layout_collapseParallaxMultiplier="0.7"
//设置视差滚动因子，值为：0~1。

app:layout_collapseMode="pin"
//pin设置为这个模式时，当CollapsingToolbarLayout完全收缩后，Toolbar还可以保留在屏幕上。
//(常用于toolbar)
```

注意：使用CollapsingToolbarLayout时必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上不会显示。

```
//当CollapsingToolbarLayout完全折叠后的背景颜色
collapsingToolbarLayout.setContentScrimColor(Color.RED);
//当CollapsingToolbarLayout完全折叠后状态栏颜色
collapsingToolbarLayout.setStatusBarScrimColor(Color.RED);
//默认contentScrim是colorPrimary的色值，statusBarScrim是colorPrimaryDark的色值。

//文字
collapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
//标题颜色
collapsingToolbarLayout.setExpandedTitleColor(Color.BLUE);//扩张颜色
collapsingToolbarLayout.setCollapsedTitleTextColor(Color.YELLOW);//折叠颜色
```

# FloatingActionButton及其Behavior的使用

https://blog.csdn.net/wei_smile/article/details/51375170

https://blog.csdn.net/gdutxiaoxu/article/details/53453958

# toolbar

```
//设置返回按钮
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
```

















