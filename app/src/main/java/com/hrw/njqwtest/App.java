package com.hrw.njqwtest;

import android.app.Application;

import com.hrw.njqwtest.base.utils.SPUtil;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/19 11:53
 * @desc:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SPUtil.INSTANCE(this, "NJQW");
    }
}
