package com.hrw.njqwtest.base.wdiget.pullrefreshview.downview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.hrw.njqwtest.R;
import com.hrw.njqwtest.base.wdiget.pullrefreshview.BasePullDownView;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/02/19 16:26
 * @desc:
 */
public class DefaultPullDownView extends BasePullDownView {
    public DefaultPullDownView(Context context) {
        super(context);
    }


    @Override
    public void onPullDowning() {

    }

    @Override
    public void onLosePullDown() {

    }

    @Override
    public void onPullDown() {

    }

    @Override
    public void onPullComplete() {

    }

    @Override
    public View getViewLayout() {
        return LayoutInflater.from(mContext).inflate(R.layout.pull_down_layout, this, false);
    }


    @Override
    public void onStartAnimate() {

    }

    @Override
    public void onStopAnimate() {

    }
}
