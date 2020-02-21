package com.hrw.njqwtest.base.wdiget.pullrefreshview;

import android.view.View;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/02/19 16:15
 * @desc:
 */
public interface BaseView {
    View getViewLayout();

    int getViewHeight();

    void onStartAnimate();

    void onStopAnimate();
}
