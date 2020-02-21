package com.hrw.njqwtest.base.wdiget.pullrefreshview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.hrw.njqwtest.R;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/02/19 16:26
 * @desc:
 */
public class DefaultPullUpView extends BasePullUpView {
    public DefaultPullUpView(Context context) {
        super(context);
    }


    @Override
    public void onPullUpping() {

    }

    @Override
    public void onLosePullUp() {

    }

    @Override
    public void onPullUp() {

    }

    @Override
    public void onPullComplete() {

    }

    @Override
    public View getViewLayout() {
        return LayoutInflater.from(mContext).inflate(R.layout.pull_up_layout, this, false);
    }


    @Override
    public void onStartAnimate() {

    }

    @Override
    public void onStopAnimate() {

    }
}
