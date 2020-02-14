package com.hrw.njqwtest.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 14:02
 * @desc:
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getDates();
    }

    protected abstract void init();

    protected abstract void getDates();

    protected void goTo(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
