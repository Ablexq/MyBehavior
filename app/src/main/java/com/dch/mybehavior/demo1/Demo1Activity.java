package com.dch.mybehavior.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

import com.dch.mybehavior.BaseActivity;
import com.dch.mybehavior.R;

public class Demo1Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1_layout);

        final TextView observeble = (TextView) findViewById(R.id.observeble);
        observeble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(v, 5);
            }
        });
    }
}
