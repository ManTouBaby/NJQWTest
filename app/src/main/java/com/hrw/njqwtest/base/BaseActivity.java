package com.hrw.njqwtest.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 14:02
 * @desc:
 */
public abstract class BaseActivity<VDB extends ViewDataBinding> extends AppCompatActivity {
    protected VDB mBindingUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingUtil = DataBindingUtil.setContentView(this, getResLayout());
        init();
        getDates();
    }


    protected abstract int getResLayout();

    protected abstract void init();

    protected abstract void getDates();

    protected Context getContext() {
        return this;
    }

    protected void goTo(Class<? extends AppCompatActivity> aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    protected void goTo(Class<? extends AppCompatActivity> aClass, boolean isFinish) {
        if (isFinish) finish();
        goTo(aClass);
    }

    protected void goTo(Class<? extends AppCompatActivity> aClass, Bundle bundle, boolean isFinish) {
        if (isFinish) finish();
        goTo(aClass, bundle);
    }

    protected void goTo(Class<? extends AppCompatActivity> aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
